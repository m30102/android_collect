package com.fan.collect.module.jsbridge.api.base;

import android.app.Activity;
import com.fan.collect.module.jsbridge.api.IWeb;
import com.fan.collect.module.jsbridge.api.JsApi;
import com.fan.collect.module.jsbridge.api.param.JsEventParam;
import com.fan.collect.module.jsbridge.api.report.IWebContainerReporter;
import com.fan.collect.module.jsbridge.api.webclient.IWebChromeClient;
import com.fan.collect.module.jsbridge.api.webclient.IWebViewClient;
import com.fan.collect.module.jsbridge.api.webview.IWebView;
import com.fan.collect.module.jsbridge.impl.JsInterface;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseWeb implements IWeb {

    private static final String ON_PAGE_APPER = "pageAppear";
    private static final String ON_PAGE_DISAPPER = "pageDisappear";
    public static final String JS_BRIDGE_FILE_NAME = "JSBridge.js";

    private Map<String, JsApi> mJsApis = new HashMap<>();

    public int mId = -1;
    public Activity mActivity;
    public IWebView mWebView;
    public IWebViewClient mWebViewClient;
    public IWebChromeClient mWebChromeClient;
    public IWebContainerReporter mWebContainerReporter;

    public boolean mBridgeInited = false;
    public String mReportKey = "";

    public BaseWeb(BaseBuilder builder){
        this.mActivity = builder.mActivity;
        this.mWebView = builder.mWebView;
        this.mWebViewClient = builder.mWebViewClient;
        this.mWebChromeClient = builder.mWebChromeClient;
        this.mWebContainerReporter = builder.mWebContainerReporter;
        try {
//            mWebView.addJavascriptInterface(new JsInterface(mActivity,this));

        }catch (Exception e){

        }


    }


}
