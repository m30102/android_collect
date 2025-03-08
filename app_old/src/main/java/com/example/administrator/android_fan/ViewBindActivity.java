package com.example.administrator.android_fan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.android_fan.databinding.ActivityViewbindBinding;


public class ViewBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewbindBinding binding = ActivityViewbindBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.include.tvTitle.setText("简单标题");
        binding.include.tvDes.setText("这是简单内容");
        binding.tvAc.setText("这是主页内容");
        binding.btnCh.setOnClickListener((v)->{
            binding.include.tvTitle.setText("简单标题1");
            binding.include.tvDes.setText("这是简单内容1");
            binding.tvAc.setText("这是主页内容1");
        });
    }
}
