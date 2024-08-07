package com.fan.collect.main.study.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.blankj.utilcode.util.ImageUtils
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.module.main.R
import com.fan.collect.module.main.databinding.ActivityImageOptBinding

/**
 * https://zhuanlan.zhihu.com/p/568501496
 * https://blog.csdn.net/qq_43167042/article/details/122958981
 * https://blog.51cto.com/u_12226/8844078
 * https://www.jianshu.com/p/ee44b89a9ba7
 *
 * https://developer.android.com/training/data-storage/shared/photopicker?hl=zh-cn
 */
class ImageoptActivity: BaseVBActivity<ActivityImageOptBinding>() {

    var bitmap:Bitmap? = null

    override fun initData() {
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.test)
    }

    override fun initView() {
        binding.btnSaveImg.setOnClickListener {
            val suc = ImageUtil.fileSaveToPublic(this, bitmap)
            Log.e(TAG, "保存图片到相册...suc:$suc");
        }
    }

    override fun getViewBinding(): ActivityImageOptBinding =
        ActivityImageOptBinding.inflate(layoutInflater)

}