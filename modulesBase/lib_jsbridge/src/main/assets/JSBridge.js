var gLogType = "console";

function _log(msg) {
    if ("none" === gLogType) {

    } else {
        console.log(msg);
    }
}

String.prototype.byteLength = function() {
    var b = 0; length = this.length;
    if(length) {
        for(var i = 0; i < length; i ++) {
            if(this.charCodeAt(i) > 255) {
                b += 2;
            }else {
                b ++;
            }
        }
        return b;
    } else {
        return 0;
    }
};

var gSessionId = "WebSession_" + new Date().getTime();
var gCallbackId = 0;
var gCallbackMap = new TMFMap();
var gEventMap = new TMFMap();
var gFuncMap = new TMFMap();
var TMFJSBridge = new TMFJSBridgeImpl();

function setLogType(logType) {
    gLogType = logType;
}

function setSessionId(sessionId) {
    gSessionId = sessionId;
}

function TMFJSBridgeImpl() {
    this.invoke = function (funcName, paramObj, callback) {
        try {
            if (!funcName || typeof funcName != "string" || funcName == "") {
                throw new Error("'funcName' must be a non empty string: " + funcName);
            }

            var paramStr = null;
            if (paramObj) {
                if (typeof paramObj == "object") {
                    paramStr = JSON.stringify(paramObj, function (key, value) {
                        if (typeof value == "function") {
                            var localFuncId = ++gCallbackId;
                            var args = "";
                            for (var index = 0; index < value.length; index++) {
                                if (index == 0) {
                                    args = args + "%s";
                                } else {
                                    args = args + ", %s";
                                }
                            }
                            gFuncMap.put(localFuncId, value);
                            return genInvokeInnerFuncStr(localFuncId, args);
                        } else {
                            return value;
                        }
                    });
                } else {
                    throw new Error("unsupported param type for 'paramObj': " + paramObj);
                }
            }
            if (!callback) {
                _log("Warning: callback of " + funcName + " func is undefined or null: " + callback);
            } else {
                if (typeof callback != "function") {
                    throw new Error("'callback' must be a function: " + callback);
                }
            }
            var localCallbackId = ++gCallbackId;
            gCallbackMap.put(localCallbackId, callback);
            var data = genInvokeInfoStr(localCallbackId, funcName, paramStr);

            JsInterface.onInvoke(data)
        } catch (err) {
            _log("invoke err: " + err.message);
            throw err;
        }
    };
    this.on = function (eventName, callback) {
        try {
            if (!eventName || typeof eventName != "string" || eventName == "") {
                throw new Error("'eventName' must be a non empty string: " + eventName);
            }
            if (!callback) {
                throw new Error("'callback' is unDefined or null: " + callback);
            } else {
                if (typeof callback != "function") {
                    throw new Error("'callback' must be a function: " + callback);
                }
            }
            var listenerList = gEventMap.get(eventName);
            var isNew = false;
            if (listenerList == null) {
                listenerList = [];
                gEventMap.put(eventName, listenerList);
                isNew = true;
            }
            listenerList.push(callback);
            if (isNew) {
                var data = genOnInfoStr(eventName);

                if ("none" !== gLogType) {
                    var txt = data.replace(/[\u4e00-\u9fa5]/g, "aa");
                    _log(txt.length);
                }

                JsInterface.onInvoke(data)
            }
        } catch (err) {
            _log("on err: " + err.message);
            throw err;
        }
    };

    function genInvokeInfoStr(callbackId, funcName, paramStr) {
        var invokeInfoObj = {
            "sessionId": gSessionId,
            "callbackId": callbackId,
            "funcName": funcName,
            "paramStr": paramStr
        };
        var invokeInfoStr = "#js_invoke#" + JSON.stringify(invokeInfoObj);
        return invokeInfoStr;
    }

    function genInvokeInnerFuncStr(funcId, args_format) {
        var innerFuncObj = {
            "sessionId": gSessionId,
            "funcId": funcId,
            "args_format": args_format
        };
        var innerFuncStr = JSON.stringify(innerFuncObj);
        return innerFuncStr;
    }

    function genOnInfoStr(eventName) {
        var onInfoObj = {
            "sessionId": gSessionId,
            "eventName": eventName
        };
        var onInfoStr = "#js_on#" + JSON.stringify(onInfoObj);
        return onInfoStr;
    }
}

function onInit(res) {
    try {
        if (res) {
            if (res.logType) {
                setLogType(res.logType);
            }
        }
    } catch (err) {
        _log("onInit1 err: " + err.message);
    }
    try {
        if (window.TMFJSBridge._hasInit) {
            _log("hasInit, no need to init again");
            return;
        }
        window.TMFJSBridge._hasInit = true;
        var readyEvent = document.createEvent("Events");
        readyEvent.initEvent("TMFJSBridgeReady");
        document.dispatchEvent(readyEvent);
    } catch (err) {
        _log("onInit2 err: " + err.message);
    }
}

function onCallback(res) {
    try {
        if (res.sessionId && res.sessionId != gSessionId) {
            _log("gSessionId not match: gSessionId=" + gSessionId + " res.sessionId=" + res.sessionId);
            return;
        }
        var callbackId = res.callbackId;
        var funcName = res.funcName;
        var callback = gCallbackMap.get(callbackId);
        if (callback != null) {
            gCallbackMap.remove(callbackId);
            callback(res);
            return;
        } else {
            _log("gCallbackMap: " + JSON.stringify(gCallbackMap));
            _log("not find callbackId on gCallbackMap,  callbackId = " + callbackId + " funcName = " + funcName);
        }

        callbackId = res.funcId;
        callback = gFuncMap.get(callbackId);
        if (callback) {
            callback(res);
        } else {
            _log("gFuncMap: " + JSON.stringify(gFuncMap));
            _log("not find funcId on gFuncMap,  funcId= " + callbackId + callbackId + " funcName = " + funcName);
        }
    } catch (err) {
        _log("onCallback err: " + err.message);
    }
}

function onReceive(res) {
    try {
        if (res.sessionId && res.sessionId != gSessionId) {
            return;
        }
        var eventName = res.eventName;
        var listenerList = gEventMap.get(eventName);
        if (listenerList != null) {
            var len = listenerList.length;
            for (var c = 0; c < len; c++) {
                listenerList[c](res);
            }
        }
    } catch (err) {
        _log("onReceive err: " + err.message);
    }
}

function handleTmfEvent(funcName, params) {
    try {
        var tmfEvent = document.createEvent('Events');
        tmfEvent.initEvent(funcName);
        tmfEvent.tmf = params;
        document.dispatchEvent(tmfEvent);
    } catch (err) {
        _log("handleEvent err: " + err.message);
    }
}

function handleMessageFromTcs(messageName, paramStr) {
    if (messageName == "sys:init") {
        onInit(paramStr);
    } else {
        if (messageName == "onCallback") {
            onCallback(paramStr);
        } else {
            if (messageName == "event:broadcast") {
                onReceive(paramStr);
            }
        }
    }
}

function TMFMap() {
    this.map = {};
    this.length = 0;
    this.size = function () {
        return this.length;
    };
    this.put = function (a, b) {
        if (!this.map["_" + a]) {
            ++this.length;
        }
        this.map["_" + a] = b;
    };
    this.remove = function (a) {
        if (this.map["_" + a]) {
            --this.length;
            return delete this.map["_" + a];
        } else {
            return false;
        }
    };
    this.containsKey = function (a) {
        return this.map["_" + a] ? true : false;
    };
    this.get = function (a) {
        return this.map["_" + a] ? this.map["_" + a] : null;
    };
    this.toString = function () {
        var b = "";
        for (var a in this.map) {
            b += "\n" + a + "-" + this.map[a];
        }
        return b;
    };
}