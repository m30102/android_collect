package com.fan.collect.study.appsflyer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.appsflyer.AppsFlyerConsent;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.fan.collect.base.BaseVBActivity;

import com.fan.collect.databinding.ActivityAppsflyerDemoBinding;
import java.util.Map;

// https://support.appsflyer.com/hc/zh-cn/articles/207032126-SDK%E5%AF%B9%E6%8E%A5%E6%A6%82%E8%A7%88
public class AppsflyerActivity extends BaseVBActivity<ActivityAppsflyerDemoBinding> {

    private static String TAG = "AppsflyerActivityTag";

    public void init(Context context) {

        AppsFlyerLib.getInstance().setDebugLog(true);
        // com.arkgames.sjzt.aptoide NzqMLQwdFNbKSXoortCXgN
        AppsFlyerLib.getInstance().init("NzqMLQwdFNbKSXoortCXgN", new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {
                Log.e(TAG, "onConversionDataSuccess map:" + map);
            }

            @Override
            public void onConversionDataFail(String s) {
                Log.e(TAG, "onConversionDataFail s:" + s);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {
                Log.e(TAG, "onAppOpenAttribution map:" + map);
            }

            @Override
            public void onAttributionFailure(String s) {
                Log.e(TAG, "onAttributionFailure s:" + s);
            }
        }, context);

        AppsFlyerConsent gdprUserConsent = AppsFlyerConsent.forGDPRUser(true, true);
        AppsFlyerLib.getInstance().setConsentData(gdprUserConsent);
        AppsFlyerLib.getInstance().start(context);

        AppsFlyerLib.getInstance().logEvent(context, "qwe", Map.of("11", "qq"), new AppsFlyerRequestListener() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess");
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.e(TAG, "onError code:" + i + " msg:" + s);
            }
        });

        String appsFlyerUID = AppsFlyerLib.getInstance().getAppsFlyerUID(context);

        Log.e(TAG, "appsFlyerUID:" + appsFlyerUID);
        AppsFlyerLib.getInstance().subscribeForDeepLink(deepLinkResult -> Log.e(TAG, "subscribeForDeepLink, deepLinkResult: " + deepLinkResult));


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        init(this);
    }

    @NonNull
    @Override
    protected ActivityAppsflyerDemoBinding getViewBinding() {
        return ActivityAppsflyerDemoBinding.inflate(getLayoutInflater());
    }
}
