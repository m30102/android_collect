package com.fan.collect.main.study.network

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsControllerCompat
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.base.utils.BlackToast
import com.fan.collect.base.utils.NetworkUtil
import com.fan.collect.module.main.databinding.ActivityNetworkCheckBinding
import com.hjq.toast.Toaster
import com.hjq.toast.style.BlackToastStyle


class NetworkCheckActivity: BaseVBActivity<ActivityNetworkCheckBinding>() {


    override fun initData() {
        Log.d(TAG,"initData...")
        Log.e(TAG,"initData...")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        val params = window.attributes
        params.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = params

        BarUtils.getStatusBarHeight()
    }

    @SuppressLint("MissingPermission")
    override fun initView() {


        binding.btnCheck1.setOnClickListener{
            val networkConnected = NetworkUtil.isNetworkConnected(this);
            val wifiConnected = NetworkUtil.isWifiConnected(this);
            val modileConnected = NetworkUtil.isModileConnected(this);
            val mobileNetOpenedRef = NetworkUtil.isMobileNetOpenedRef(this);
            binding.tvResult1.text = "networkConnected:" + networkConnected + "\nwifiConnected:" + wifiConnected + "\nmodileConnected:" +modileConnected+"\nmobileNetOpenedRef:"+mobileNetOpenedRef
            ToastUtils.showShort("少时诵诗书");

        }


        binding.btnCheck2.setOnClickListener {
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
    }

    override fun getViewBinding(): ActivityNetworkCheckBinding {
        return ActivityNetworkCheckBinding.inflate(layoutInflater)
    }
}