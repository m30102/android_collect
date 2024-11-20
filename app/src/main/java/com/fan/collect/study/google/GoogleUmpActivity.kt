package com.fan.collect.study.google;

import com.fan.collect.base.BaseVBActivity
import com.fan.collect.base.utils.LogHelper
import com.fan.collect.databinding.ActivityGoogleUmpBinding
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.ConsentType
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.initialize
import java.util.EnumMap


/**
 * https://blog.csdn.net/u010207898/article/details/132181803
 * https://developers.google.com/interactive-media-ads/docs/sdks/android/client-side/privacy?hl=zh-cn
 * https://juejin.cn/post/7307146455536648219
 *
 * http://www.yunbu.me/sdk/overseas/doc/Android_SDK.html
 * https://docs.tradplusad.com/docs/tradplussdk_android_doc_v6/privacy_policy/google_ump
 *
 * 管理用户意见征求设置（应用）
 * https://developers.google.com/tag-platform/security/guides/app-consent?hl=zh-cn&consentmode=basic&platform=android
 * https://developers.google.com/admob/android/quick-start?hl=zh-cn
 * 官方测试ca-app-pub-3940256099942544~3347511713
 */
class GoogleUmpActivity : BaseVBActivity<ActivityGoogleUmpBinding>() {

    private lateinit var consentInformation: ConsentInformation;

    override fun initData() {
       /*
        需要google-services.json
        FirebaseApp.initializeApp(this)
        Firebase.analytics.setConsent(
            EnumMap<ConsentType, FirebaseAnalytics.ConsentStatus>(
                ConsentType::class.java
            ).apply {
                put(ConsentType.ANALYTICS_STORAGE, FirebaseAnalytics.ConsentStatus.GRANTED)
                put(ConsentType.AD_STORAGE, FirebaseAnalytics.ConsentStatus.GRANTED)
                put(ConsentType.AD_USER_DATA, FirebaseAnalytics.ConsentStatus.GRANTED)
                put(ConsentType.AD_PERSONALIZATION, FirebaseAnalytics.ConsentStatus.GRANTED)
            })*/
    }

    override fun initView() {


        initUmp()
    }

    private fun initUmp() {
        //添加测试设备
        val debugSettings = ConsentDebugSettings.Builder(this)
            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA) //DebugGeography.DEBUG_GEOGRAPHY_EEA 表示模拟欧洲用户，DEBUG_GEOGRAPHY_NOT_EEA 表示模拟非欧洲用户
            .addTestDeviceHashedId("设备ID") //设备Id请参考2.5.2中获取
            .build()
        //配置信息
        val params = ConsentRequestParameters.Builder()
            // 指示用户是否低于同意年龄; true 低于同意年龄
            // 未满同意年龄的用户不会收到 GDPR 消息表单
            .setTagForUnderAgeOfConsent(false)
//            .setConsentDebugSettings(debugSettings)
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        consentInformation.reset()
        consentInformation.requestConsentInfoUpdate(this, params,
            {
                // 同意信息状态已更新，可以开始加载协议内容
                LogHelper.e(
                    TAG,
                    "onConsentInfoUpdateSuccess consentFormAvailable:" + consentInformation.isConsentFormAvailable
                )
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(this) { err ->
                    LogHelper.e(
                        TAG,
                        "onConsentFormDismissed err:" + err?.errorCode + " msg:" + err?.message
                    )
                    LogHelper.e(TAG, "canRequestAds:" + consentInformation.canRequestAds())
                    if (consentInformation.canRequestAds()) {
                        // Consent has been gathered
                        // 授权完成,初始化SDK
                    }
                }
            },
            { err ->
                // Consent gathering failed. 请求失败，处理异常
                LogHelper.e(
                    TAG,
                    "onConsentInfoUpdateFailure err:" + err.errorCode + " msg:" + err.message
                )
            })

        if (consentInformation.canRequestAds()) {
            // 授权完成,初始化SDK
        }
    }

    override fun getViewBinding(): ActivityGoogleUmpBinding {
        return ActivityGoogleUmpBinding.inflate(layoutInflater)
    }

}
