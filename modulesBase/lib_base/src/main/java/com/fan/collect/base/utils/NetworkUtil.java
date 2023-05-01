package com.fan.collect.base.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * 网络相关工具类
*      // https://xuxiaoshi.gitee.io/Android%E7%BD%91%E7%BB%9C-%E7%BD%91%E7%BB%9C%E7%8A%B6%E6%80%81%E5%A4%84%E7%90%86/
*     // https://www.jianshu.com/p/40fe79d65781
*     // https://www.codenong.com/cs106329664/   https://blog.csdn.net/android_cai_niao/article/details/106329664 踩坑
 */
public class NetworkUtil {
    private static final String TAG = "NetworkUtil";

    public static class NetworkProxy {
        public final String host;
        public final int port;

        private NetworkProxy(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }

    public static NetworkProxy getProxy(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return null;
        }
        // ACCESS_NETWORK_STATE
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (NetworkUtil.isMobileNetwork(activeNetworkInfo)) {
            String proxyHost = getProxyHost(context);
            int proxyPort = getProxyPort(context);
            if (!TextUtils.isEmpty(proxyHost) && proxyPort >= 0) {
                return new NetworkProxy(proxyHost, proxyPort);
            }
        }
        return null;
    }

    public static String getProxyHost(Context context) {
        String host = null;
        if (Build.VERSION.SDK_INT < 11) {
            if (context != null) {
                host = android.net.Proxy.getHost(context);
                if (TextUtils.isEmpty(host)) {
                    host = android.net.Proxy.getDefaultHost();
                }
            } else {
                host = android.net.Proxy.getDefaultHost();
            }
        } else {
            host = System.getProperty("http.proxyHost");
        }
        return host;
    }

    public static int getProxyPort(Context context) {
        int port = -1;
        if (Build.VERSION.SDK_INT < 11) {
            if (context != null) {
                port = android.net.Proxy.getPort(context);
                if (port < 0) {
                    port = android.net.Proxy.getDefaultPort();
                }
            } else {
                port = android.net.Proxy.getDefaultPort();
            }
        } else {
            String portStr = System.getProperty("http.proxyPort");
            if (!TextUtils.isEmpty(portStr)) {
                try {
                    port = Integer.parseInt(portStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return port;
    }

    /**
     * 判断此网络是否为mobile
     * 注：适应三星新定义的双卡双待mobile类型
     */
    public static boolean isMobileNetwork(final NetworkInfo netInfo) {
        if (null == netInfo) {
            return false;
        }
        if (ConnectivityManager.TYPE_MOBILE == netInfo.getType()
                || ConnectivityManager.TYPE_MOBILE + 50 == netInfo.getType()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否需要重定向
     *
     * @param resposeCode
     * @return
     */
    public static boolean isRedirect(final int resposeCode) {
        if (resposeCode >= HttpURLConnection.HTTP_MULT_CHOICE
                && resposeCode <= HttpURLConnection.HTTP_SEE_OTHER) {
            return true;
        }

        //  HTTP_TEMP_REDIRECT = 307, HTTP_PERM_REDIRECT = 308;
        if (resposeCode == 307 || resposeCode == 308) {
            return true;
        }

        return false;
    }



    /**
     * 获取网络连接服务
     *
     * @param context
     * @return
     */
    public static ConnectivityManager getConnectivityManager(Context context) {
        ConnectivityManager connectivityManager = null;
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        } catch (Exception e) {
            // 权限限制可能会抛出异常
            e.printStackTrace();
        }
        return connectivityManager;
    }





    // 判断本机网络是否连接，但不确定真正能连接互联网
    public static boolean isNetworkConnected(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            // 实际上23就有这个类，但Q版本才标记过时, 保守做法
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                // https://www.codenong.com/cs106329664/  NET_CAPABILITY_VALIDATED 连接无网的wifi 返回true 并不能保证 ? NET_CAPABILITY_VALIDATED比NET_CAPABILITY_INTERNET可靠
                result = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            } else {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                result = activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            Log.e(TAG, "isNetworkConnected error:" + e.getMessage());
        }
        return result;
    }


    // 当前是否是wifi, wifi打开就会返回true
    public static boolean isWifiConnected(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            // 实际上23就有这个类，但Q版本才标记过时, 保守做法
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                Network activeNetwork = connectivityManager.getActiveNetwork();
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                result = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            } else {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                result  = networkInfo.isConnected();
            }
        } catch (Exception e) {
            Log.e(TAG, "isWifiEnable error:" + e.getMessage());
        }
        return result;
    }


    // 当前是否是流量, wifi和流量同时打开会返回false,wifi关闭流量打开会返回true
    public static boolean isModileConnected(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            // 实际上23就有这个类，但Q版本才标记过时, 保守做法
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                Network activeNetwork = connectivityManager.getActiveNetwork();
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                result = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            } else {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                result  = networkInfo.isConnected();
            }
        } catch (Exception e) {
            Log.e(TAG, "isWifiEnable error:" + e.getMessage());
        }
        return result;
    }


    // 流量是否打开，此时wifi可能打开可能关闭
    public static boolean isMobileNetOpenedRef(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            Method getMobileDataEnabled = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
            getMobileDataEnabled.setAccessible(true);
            result = (boolean) getMobileDataEnabled.invoke(connectivityManager);
        } catch (Exception e) {
            Log.e(TAG, "reflect MobileDataEnable error");
        }
        return result;
    }


    public static int gotoSystemNetworkSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        ComponentName cName = new ComponentName("com.android.phone", "com.android.phone.Settings");
        intent.setComponent(cName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "gotoSystemNetworkSetting, err: " + e.getMessage());
        }
        return -1;
    }

    /**
     * 获得当前联网是否已连接
     *
     * @return
     */
    public static boolean isNetworkAvaliable(Context context) {
        try {
            ConnectivityManager manager = getConnectivityManager(context);
            if (manager != null) {
                NetworkInfo[] info = manager.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 获取当前网络连接类型
     *
     * @return 网络连接类型
     */
    public static int getNetworkType(Context context) {
        NetworkInfo info = null;
        try {
            info = getActiveNetworkInfo(context);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (info == null) {
            return ConnectType.CT_NONE;
        }
        if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            return ConnectType.CT_WIFI;
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            String host = getProxyHost(context);
            if (null != host && host.length() > 0 && getProxyPort(context) > 0) {
                return ConnectType.CT_GPRS_WAP;
            } else {
                return ConnectType.CT_GPRS_NET;
            }
        }
        return ConnectType.CT_GPRS_NET;
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        NetworkInfo networkInfo = null;
        try {
            ConnectivityManager mg = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = mg.getActiveNetworkInfo();
        } catch (Throwable e) {
            // ignore
            LogHelper.i(TAG, "getActiveNetworkInfo Exception : " + e.getMessage());
        }
        return networkInfo;
    }
    /**
     * 网络是否已经连接了
     *
     * @return
     */
    public static boolean isNetworkConnectedOld(Context context) {
        NetworkInfo netinfo = getActiveNetworkInfo(context);
        if (null == netinfo) {
            return false;
        }

        boolean isConnected = netinfo.isConnected();
        return isConnected;
    }


    /**
     * 检查是否可联网
     *
     * @return
     */
    public static boolean detectConnection(String from) {
        boolean isConnect = false;
        String host = "www.qq.com";
        LogHelper.i(TAG, "detectConnection, host: " + host + " from: " + from);
        long time = System.currentTimeMillis();
        Socket socket = null;
        try {
            InetAddress serverAddr = InetAddress.getByName(host);
            InetSocketAddress addr = new InetSocketAddress(serverAddr, 80);
            socket = new Socket();
            socket.setSoLinger(false, 0);
            socket.connect(addr, 5000);
            isConnect = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            LogHelper.e(TAG, "detectConnection, Throwable: " + e.getMessage());
        } finally {
            try {
                if (socket != null && socket.isConnected()) {
                    socket.close();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        LogHelper.d(TAG, "detectConnection end, isConnect: " + isConnect
                + " time cost: " + (System.currentTimeMillis() - time));

        return isConnect;
    }

    public interface ConnectType {
        int CT_NONE = 0;
        int CT_GPRS = 1;
        int CT_WIFI = 2;
        int CT_GPRS_WAP = 3;
        int CT_GPRS_NET = 4;
        int CT_3G_NET = 5;

    }

}
