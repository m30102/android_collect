package com.fan.collect

import android.app.Application
import android.content.Context
import android.util.Log
import com.hjq.toast.Toaster
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy

//  极客时间 breakpad https://time.geekbang.org/column/article/70602
class MyApp:Application() {
    val TAG = "MyApp"
    override fun onCreate() {
        super.onCreate()
        Toaster.init(this)
        val u = UserStrategy (this)
        u.setCrashHandleCallback(object :CrashReport.CrashHandleCallback(){

            /**
             * Crash处理.
             *
             * @param crashType 错误类型：CRASHTYPE_JAVA，CRASHTYPE_NATIVE，CRASHTYPE_U3D ,CRASHTYPE_ANR
             * @param errorType 错误的类型名
             * @param errorMessage 错误的消息
             * @param errorStack 错误的堆栈
             * @return 返回额外的自定义信息上报
             */
            override fun onCrashHandleStart(
                crashType: Int,
                errorType: String?,
                errorMsg: String?,
                errorStack: String?
            ): MutableMap<String, String> {
                Log.e(TAG, "onCrashHandleStart crashType:$crashType errorType:$errorType errorMsg:$errorMsg errorStack:$errorStack")
                return super.onCrashHandleStart(crashType, errorType, errorMsg, errorStack)
            }

            /**
             * Crash处理.
             *
             * @param crashType 错误类型：CRASHTYPE_JAVA，CRASHTYPE_NATIVE，CRASHTYPE_U3D ,CRASHTYPE_ANR
             * @param errorType 错误的类型名
             * @param errorMessage 错误的消息
             * @param errorStack 错误的堆栈
             * @return byte[] 额外的2进制内容进行上报
             */
            override fun onCrashHandleStart2GetExtraDatas(
                crashType: Int,
                errorType: String?,
                errorMsg: String?,
                errorStack: String?
            ): ByteArray {
                Log.e(TAG, "onCrashHandleStart2GetExtraDatas crashType:$crashType errorType:$errorType errorMsg:$errorMsg errorStack:$errorStack")
                return super.onCrashHandleStart2GetExtraDatas(crashType, errorType, errorMsg, errorStack)
            }
        })
        Log.e(TAG,"initCrashReport")
        CrashReport.initCrashReport(this,"7213fc90a1",true, u)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

}