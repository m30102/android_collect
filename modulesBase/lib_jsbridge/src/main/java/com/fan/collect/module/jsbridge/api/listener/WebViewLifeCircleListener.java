package com.fan.collect.module.jsbridge.api.listener;

import android.graphics.Bitmap;
import com.fan.collect.module.jsbridge.api.param.JsCallParam;
import com.fan.collect.module.jsbridge.api.param.JsEventParam;

public interface WebViewLifeCircleListener {


     void onJsEvent(JsEventParam param);

     void onJsCallNative(JsCallParam param);

     void onPageStarted(String url, Bitmap favicon);

     void onPageFinished(String url, int progress);

     void onLoadResource(String url);

     boolean shouldOverrideUrlLoading(String url);

     void shouldInterceptRequest(String url);

     void onProgressChanged(int newProgress);
}
