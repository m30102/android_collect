package com.fan.collect.study.activity.launch.singletask

import android.content.Intent
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.databinding.ActivityComponentsBinding

class ComponentsDemo:BaseVBActivity<ActivityComponentsBinding>() {

    override fun initData() {
    }

    override fun initView() {
        binding.btnActivitySingletask.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }
    }

    override fun getViewBinding(): ActivityComponentsBinding {
        return ActivityComponentsBinding.inflate(layoutInflater)
    }
}