package com.fan.collect.study.view

import com.fan.collect.base.BaseVBActivity
import com.fan.collect.databinding.ActivityCustomviewBinding

class CustomViewActivity:BaseVBActivity<ActivityCustomviewBinding>() {

    override fun initData() {
    }

    override fun initView() {
    }

    override fun getViewBinding(): ActivityCustomviewBinding {
        return ActivityCustomviewBinding.inflate(layoutInflater)
    }

}