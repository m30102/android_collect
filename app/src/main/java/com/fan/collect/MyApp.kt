package com.fan.collect

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.os.Build.VERSION_CODES.N
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ThreadUtils
import com.fan.collect.base.utils.Logger
import com.hjq.toast.Toaster
import java.util.Locale


class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        initArouter()
        Toaster.init(this)
        ThreadUtils.isMainThread()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
    private fun initArouter() {
        Logger.d("initArouter")
        ARouter.init(this)
        ARouter.openDebug()
        ARouter.openLog()
    }


}