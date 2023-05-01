package com.fan.collect

import android.app.Application
import android.util.LruCache
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ScreenUtils
import com.fan.collect.base.utils.Logger
import com.hjq.toast.Toaster
import java.util.HashMap
import java.util.LinkedHashMap

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        initArouter()
        Toaster.init(this)
    }

    private fun initArouter() {
        Logger.d("initArouter")
        ARouter.init(this)
        ARouter.openDebug()
        ARouter.openLog()
    }


}