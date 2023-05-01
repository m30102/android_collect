package com.fan.collect.module.jsbridge.api;

public class Constants {


    public static final String JS_MSG_CALLBACK = "javascript:handleMessageFromC('onCallback', %s)";
    public static final String JS_MSG_BROADCAST = "javascript:handleMessageFromC('event:broadcast', %s)";
    public static final String CALLBACK_ARG_NAME_EVENTNAME = "eventName";
}
