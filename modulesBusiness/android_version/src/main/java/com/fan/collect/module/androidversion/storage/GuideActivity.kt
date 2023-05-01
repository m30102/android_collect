package com.fan.collect.module.androidversion.storage

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.fan.collect.module.androidversion.databinding.ActivityGuideBinding
import com.fan.collect.module.androidversion.fileprovider.FileProviderActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
// https://www.jianshu.com/p/cf6111e497cf
class GuideActivity: AppCompatActivity() {
    private lateinit var binding:ActivityGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        XXPermissions.with(this).permission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).request { permissions, all ->
            if (all) {
                Log.d("GuideActivity","granted all")
            }
        }
    }

    private fun initView() {
        binding.sstorage.setOnClickListener {
            startActivity(Intent(this,StorageActivity1::class.java))
        }
        binding.fileproviderid.setOnClickListener {
            startActivity(Intent(this,FileProviderActivity::class.java))
        }
    }
}