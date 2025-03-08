package com.fan.firstcode3

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication :Application(){


    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context

//        const val TOKEN = "BNBRvBveaD2VfHVI"
        const val TOKEN = "oELioyuY9WYmAASo"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}