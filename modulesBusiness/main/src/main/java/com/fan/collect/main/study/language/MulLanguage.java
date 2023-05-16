package com.fan.collect.main.study.language;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fan.collect.base.BaseVBActivity;
import com.fan.collect.base.utils.LogHelper;
import com.fan.collect.base.utils.Logger;
import com.fan.collect.module.main.databinding.ActivityMulLanguageBinding;

import java.util.Locale;

public class MulLanguage extends BaseVBActivity<ActivityMulLanguageBinding> {
// https://blog.csdn.net/u012999651/article/details/122199260
// https://www.jianshu.com/p/a6d090234d25?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes

    /**
     * android:configChanges="locale|layoutDirection"
     * 　　　　在清单文件中每个activity中添加该属性，可以避免app处于后台时，切换了系统语言后，将app唤醒到前台时，应用崩溃的bug。
     */
    @Override
    public void initBeforeSetContentView() {
//        setLanguage("en");
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(getLanguageContext(newBase));
    }


    private  Context getLanguageContext(Context context){
        Resources resources = context.getResources(); //  Resources.getSystem() 前者获取的是应用内部的语言和地区设置，后者获取的是系统的语言地区设置，默认情况下，前者跟随系统设置。
        Configuration configuration = resources.getConfiguration();
        LocaleList locales = configuration.getLocales();
        LogHelper.i(getTAG(),"locales:"+locales);//当前设备安装的语言包[zh_CN,en_US,zh_TW_#Hant]
        configuration.setLocale(Locale.getDefault());
        Context configurationContext = context.createConfigurationContext(configuration);
        return configurationContext;
    }

    // 整个应用全局生效  values-语言代号-地区代号
    private void setLanguage(String language){
        Resources resources = getResources(); //  Resources.getSystem() 前者获取的是应用内部的语言和地区设置，后者获取的是系统的语言地区设置，默认情况下，前者跟随系统设置。
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        LocaleList locales = configuration.getLocales();
        LogHelper.i(getTAG(),"locales:"+locales);//当前设备安装的语言包[zh_CN,en_US,zh_TW_#Hant]
        if (TextUtils.isEmpty(language)){
            Locale localDefault = Locale.getDefault();// localDefault:zh_CN ,上面设备语言包的顺序
            LogHelper.i(getTAG(),"localDefault:"+localDefault);
            configuration.setLocale(Locale.getDefault());
        }else {
            if (language.equals("en")){
                configuration.setLocale(Locale.ENGLISH);
            }else if (language.equals("zh")){
                configuration.setLocale(Locale.CHINESE);
            }
        }
//        Context configurationContext = createConfigurationContext(configuration);
//        return configurationContext;
        // 过时
        resources.updateConfiguration(configuration,displayMetrics);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @NonNull
    @Override
    protected ActivityMulLanguageBinding getViewBinding() {
        return ActivityMulLanguageBinding.inflate(getLayoutInflater());
    }
}
