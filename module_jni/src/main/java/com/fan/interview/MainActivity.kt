package com.fan.interview

import com.fan.collect.base.BaseVBActivity
import com.fan.interview.databinding.ActivityMainBinding

class MainActivity : BaseVBActivity<ActivityMainBinding>() {

    val helloJni:HelloJni = HelloJni()
    override fun initData() {
    }

    override fun initView() {
        binding.btnJava2cpp.setOnClickListener {

            helloJni.printMsg("123qwe")
        }
    }


    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}