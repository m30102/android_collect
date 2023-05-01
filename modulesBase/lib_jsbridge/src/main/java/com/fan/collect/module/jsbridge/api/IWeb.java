package com.fan.collect.module.jsbridge.api;

import com.fan.collect.module.jsbridge.api.webview.IWebView;
import org.json.JSONObject;

public interface IWeb {

    IWebView getWebViewHolder();

    void addJsApi(Class<? extends JsApi> jsApi);

    void addJsApi(JsApi jsApi);

    JsApi findJsApi(String method);

    void nativeCallJs(String functionName, JSONObject jsonObject);

    void onResume();

    void onPause();

    void onStop();

    void onDestoryFragment();

    void onDestory();

    void setPageInfo(String reportkey);

}
