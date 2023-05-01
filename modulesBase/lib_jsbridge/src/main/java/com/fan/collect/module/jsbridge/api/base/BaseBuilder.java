package com.fan.collect.module.jsbridge.api.base;

import android.app.Activity;
import com.fan.collect.module.jsbridge.api.report.IWebContainerReporter;
import com.fan.collect.module.jsbridge.api.webclient.IWebChromeClient;
import com.fan.collect.module.jsbridge.api.webclient.IWebViewClient;
import com.fan.collect.module.jsbridge.api.webview.IWebView;

public class BaseBuilder {


    public Activity mActivity;
    public IWebView mWebView;
    public IWebViewClient mWebViewClient;
    public IWebChromeClient mWebChromeClient;
    public IWebContainerReporter mWebContainerReporter;

    public BaseBuilder(Activity activity) {
        this.mActivity = activity;
    }

    public BaseBuilder setWebView(IWebView mWebView){
        this.mWebView = mWebView;
        return this;
    }

    public BaseBuilder setWebViewClient(IWebViewClient mWebViewClient){
        this.mWebViewClient = mWebViewClient;
        return this;
    }

    public BaseBuilder setWebChromeClient(IWebChromeClient mWebChromeClient){
        this.mWebChromeClient = mWebChromeClient;
        return this;
    }

    public BaseBuilder setWebContainerReporter(IWebContainerReporter reporter) {
        this.mWebContainerReporter = reporter;
        return this;
    }


}
