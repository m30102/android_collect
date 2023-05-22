package com.fan.collect.base.http;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class EasyHttp {

    String mUrl;
    String mPara = "";
    private static final String TAG = "EasyHttp";

    public EasyHttp(String url, String para) {
        this.mUrl = url;
        this.mPara = para;
    }

    public EasyHttp(String url) {
        this(url, "");
    }

    public final static String DEFAUTL_CHARSET = "UTF-8";

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getPara() {
        return mPara;
    }

    public void setPara(String param) {
        mPara = param;
    }

    public void access() {
        access(HttpAccessMode.GET);
    }

    public void access(HttpAccessMode accessMode) {
        accessInternet(accessMode);
    }


    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_TIMEOUT = 10 * 1000;

    private void accessInternet(HttpAccessMode httpAccessMode) {
        if (TextUtils.isEmpty(getUrl())) {
            Log.e(TAG, "url is empty,failed to accessInternet");
            return;
        }
        String urlStr = getUrl();

        if (!TextUtils.isEmpty(getPara()) && httpAccessMode == HttpAccessMode.GET) {
            StringBuffer paramStr = new StringBuffer(getPara());
            if (paramStr.charAt(0) == '&') {
                paramStr = paramStr.replace(0, 1, "");
            }
            urlStr += "?" + paramStr;
        }
        long timeBeforeRequest = System.currentTimeMillis();

        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        OutputStream os = null;
        StringBuilder sb;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setAllowUserInteraction(true);
            HttpURLConnection.setFollowRedirects(true);
            conn.setRequestProperty("Accept-Charset", DEFAUTL_CHARSET);
            conn.setRequestProperty("contentType", DEFAUTL_CHARSET);
            conn.setConnectTimeout(DEFAULT_TIMEOUT);
            conn.setReadTimeout(DEFAULT_TIMEOUT);
            conn.setRequestMethod(httpAccessMode.toString());
            setHeadersToConnection(conn);
            if (httpAccessMode == HttpAccessMode.POST) {
                conn.setDoOutput(true);
                os = conn.getOutputStream();
                os.write(getPara().getBytes());
                os.flush();
            } else if (httpAccessMode == HttpAccessMode.GET) {
                conn.setDoOutput(false);
            }
            int respCode = conn.getResponseCode();
            setHttpCode(respCode);
            if (respCode != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, MessageFormat.format("连接url【{0}】httpCode【{1}】，请求失败！", urlStr,
                        respCode));
            }
            int count = 0;
            byte[] buffer = new byte[1024];
            sb = new StringBuilder();

            is = conn.getInputStream();
            while ((count = is.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, count, DEFAUTL_CHARSET));
            }
            String receivedStr = sb.toString();
            setResult(receivedStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = MessageFormat.format(
                    "failed to connect to the url [{0}], the reasons are follows: {1}",
                    url, e.getMessage());

            setHttpAccessErrMsg(e.toString());
        } finally {
            setElapsedTime(System.currentTimeMillis() - timeBeforeRequest);
            if (null != is) {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (Exception ignored) {
                }
            }
            if (null != conn) {
                conn.disconnect();
            }
        }
    }

    public String getResponseStr() {
        if (null == getResult()) {
            return "";
        }
        return new String(getResult(), Charset.forName(DEFAULT_CHARSET));
    }

    public boolean success(){
        return HttpURLConnection.HTTP_OK == getHttpCode();
    }

    long mElapsedTime;

    public long getElapsedTime() {
        return mElapsedTime;
    }

    public void setElapsedTime(long v) {
        mElapsedTime = v;
    }

    private String mErrMsg = "";

    public void setHttpAccessErrMsg(String msg) {
        mErrMsg = msg;
    }

    public String getHttpAccessErrMsg() {
        return mErrMsg;
    }

    byte[] mResult;

    public byte[] getResult() {
        return mResult;
    }

    public void setResult(byte[] v) {
        mResult = v;
    }


    public static final int HTTP_NO_TACCESS = 0;
    int mHttpCode = HTTP_NO_TACCESS;

    public int getHttpCode() {
        return mHttpCode;
    }

    public void setHttpCode(int v) {
        mHttpCode = v;
    }


    private void setHeadersToConnection(HttpURLConnection conn) {

        if (null == getHeaderMap() || getHeaderMap().isEmpty()) {
            return;
        }
        if (null == conn) {
            return;
        }
        for (Map.Entry<String, String> entry : getHeaderMap().entrySet()) {
            String headerName = entry.getKey();
            String headerValue = entry.getValue();
            if (TextUtils.isEmpty(headerName) || TextUtils.isEmpty(headerValue)) {
                continue;
            }
            conn.setRequestProperty(headerName, headerValue);
        }
    }


    private Map<String, String> mHeaders;

    private Map<String, String> getHeaderMap() {
        return mHeaders;
    }

    public void addHeader(String headerName, String headerValue) {
        if (TextUtils.isEmpty(headerName)) {
            Log.e(TAG, "header name is empty, failed to add http header.");
            return;
        }
        if (TextUtils.isEmpty(headerValue)) {
            Log.e(TAG, "header value is empty, failed to add http header.");
            return;
        }

        if (null == getHeaderMap()) {
            mHeaders = new HashMap<>();
        }
        getHeaderMap().put(headerName, headerValue);
    }

    public static enum HttpAccessMode {
        GET(1), POST(2);
        private int code;

        HttpAccessMode(int code) {
            this.code = code;
        }
        @NonNull
        @Override
        public String toString() {
            if (code == GET.code) {
                return "GET";
            }
            return "POST";
        }
    }


}
