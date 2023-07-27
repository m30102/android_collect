package com.fan.collect.main.study.network

import android.Manifest
import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.PermissionUtils
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.base.utils.BlackToast
import com.fan.collect.base.utils.NetworkUtil
import com.fan.collect.module.main.databinding.ActivityNetworkCheckBinding


class NetworkCheckActivity: BaseVBActivity<ActivityNetworkCheckBinding>() {


    override fun initData() {
        Log.e(TAG,"initData...")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun initView() {
        binding.btnCheck1.setOnClickListener{
            val networkConnected = NetworkUtil.isNetworkConnected(this);
            val wifiConnected = NetworkUtil.isWifiConnected(this);
            val modileConnected = NetworkUtil.isModileConnected(this);
            val mobileNetOpenedRef = NetworkUtil.isMobileNetOpenedRef(this);
            /*
            需要权限 READ_PHONE_STATE
            val subscriptionManager = SubscriptionManager.from(this)
            val activeSubscriptionInfoList = subscriptionManager.activeSubscriptionInfoList
            activeSubscriptionInfoList.forEach { subscriptionInfo -> {
                Log.e(TAG,"subscriptionInfo:"+subscriptionInfo)
            } }*/
            binding.tvResult1.text = "networkConnected:" + networkConnected + "\nwifiConnected:" + wifiConnected + "\nmodileConnected:" +
                    modileConnected+"\nmobileNetOpenedRef:"+mobileNetOpenedRef
        }

        requestPermissions(arrayOf( Manifest.permission.ACCESS_FINE_LOCATION),1)
        binding.btnCheck2.setOnClickListener {

//                NetworkUtil.requestByCell(this)
            NetworkUtil.getActive(this)
        }
    }

    fun createCustomView(){
        val scale = resources.configuration.fontScale
        binding.tvResult2.text = "scale:" + scale

        val toast = Toast.makeText(
            this,
            "直接改写原生的Toast布局",
            Toast.LENGTH_SHORT
        )
        toast.view = BlackToast.createView(this,"cccccc");
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show()
    }

    override fun getViewBinding(): ActivityNetworkCheckBinding {
        return ActivityNetworkCheckBinding.inflate(layoutInflater)
    }
}