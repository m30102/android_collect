package com.fan.collect.module.jsbridge.api.webview;

import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;
import com.fan.collect.module.jsbridge.api.webclient.IWebChromeClient;
import com.fan.collect.module.jsbridge.api.webclient.IWebViewClient;
import java.util.Map;

public interface IWebView<T extends View,S> {

     T getWebView();

     S getWebSettings();

     Handler getHandler();

     void loadUrl(String url);

     void loadUrl(String url, Map<String,String> header);

     void reload();

     void stopLoading();

     void postUrl(String url,byte[] postData);

     void loadData(String data,String mimeType,String encoding);

     void loadDataWithBaseUrl(String baseUrl,String data,String mimeType, String encoding, String historyUrl);

     void setWebViewClient(IWebViewClient webViewClient);

     void setWebChromeClinet(IWebChromeClient webChromeClinet);

     void onResume();

     void onPause();

     void onDestory();

     boolean goBack();

     int getHeight();

     int getContentWidth();

     int getContentHeight();

     float getScale();

     void scrollTo(int x,int y);

     void scrollBy(int x,int y);

     String getUrl();

     void setWebContentsDebuggingEnabled();

     void snapshotVisible(Canvas canvas);

     void snapshotWholePage(Canvas canvas);

     void evaluateJavascript(String javascript);

     void addJavascriptInterface(Object o,String s);



}
