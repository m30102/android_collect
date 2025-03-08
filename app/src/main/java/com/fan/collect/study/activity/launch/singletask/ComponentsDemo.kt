package com.fan.collect.study.activity.launch.singletask

import android.content.Intent
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.databinding.ActivityComponentsBinding
import com.fan.collect.study.view.RecycleViewActivity

class ComponentsDemo:BaseVBActivity<ActivityComponentsBinding>() {

    override fun initData() {
    }

    override fun initView() {
        binding.btnActivitySingletask.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }
        binding.btnRecycle.setOnClickListener {
            startActivity(Intent(this,RecycleViewActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityComponentsBinding {
        return ActivityComponentsBinding.inflate(layoutInflater)
    }
}