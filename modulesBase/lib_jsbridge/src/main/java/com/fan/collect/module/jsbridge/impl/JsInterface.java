package com.fan.collect.module.jsbridge.impl;

import android.app.Activity;
import com.fan.collect.module.jsbridge.api.listener.WebViewLifeCircleListener;
import java.lang.ref.WeakReference;

public class JsInterface {

    public static final String JS_INTERFACE_NAME = "JsInterface";//JS调用类名
    private WeakReference<Activity> mActivityRef = null;
    private WebViewLifeCircleListener mWebViewLifeCircle;

    public JsInterface(Activity activity,WebViewLifeCircleListener mWebViewLifeCircle){
        mActivityRef = new WeakReference<>(activity);
        this.mWebViewLifeCircle = mWebViewLifeCircle;
    }

    public void onInvoke(String message){

    }

}
