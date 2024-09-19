package com.fan.collect.main.study.image

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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
        binding.btnSaveImgGallery.setOnClickListener {
             var i = 1/0;
        /*    val suc = ImageUtil.saveToGallery(this, bitmap)
            Log.e(TAG, "保存图片到相册...suc:$suc");*/
        }

        binding.btnSaveImgSanbox.setOnClickListener {
            ImageUtil.saveToShareDir(this,bitmap)
        }
        binding.btnShareImg.setOnClickListener {
                ImageUtil.share(this)
        }

        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),0);
    }

    override fun getViewBinding(): ActivityImageOptBinding =
        ActivityImageOptBinding.inflate(layoutInflater)


    /**
     * 保存Bitmap到相册的Pictures文件夹
     *
     * @param context 上下文
     * @param fileName 文件名。 需要携带后缀
     * @param relativePath 相对于Pictures的路径
     * @param quality 质量
     *
     */


    /*fun Bitmap.saveToAlbum(
        context: Context,
        fileName: String,
        relativePath: String? = null,
        quality: Int = 75
    ): Uri? {
        val resolver = context.contentResolver
        val outputFile = OutputFileTaker()
        // 插入图片信息
        val imageUri:Uri = resolver.insertMediaImage(fileName, relativePath, outputFile)
        if (imageUri == null) {
            Log.w(TAG, "insert: error: uri == null")
            return null
        }

        // 通过Uri打开输出流
        (imageUri.outputStream(resolver) ?: return null).use {
            val format = fileName.getBitmapFormat()
            // 保存图片
            this@saveToAlbum.compress(format, quality, it)
            // 更新 IS_PENDING 状态
            imageUri.finishPending(context, resolver, outputFile.file)
        }
        return imageUri
    }*/


}