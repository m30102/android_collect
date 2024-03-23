package com.fan.collect.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fan.collect.base.constance.NaviConst;
import com.fan.collect.main.study.activity.launch.singletask.ComponentsDemo;
import com.fan.collect.main.study.google.GoogleLoginActivity;
import com.fan.collect.main.study.language.MulLanguage;
import com.fan.collect.main.study.network.NetworkCheckActivity;
import com.fan.collect.main.study.view.CustomViewActivity;
import com.fan.collect.module.main.databinding.ActivityMainMainBinding;
import java.util.HashMap;

@Route(path = NaviConst.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {


    ActivityMainMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("MainActivityTAG","onCreate");
        super.onCreate(savedInstanceState);
        binding = ActivityMainMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnComponent.setOnClickListener(v -> {
            int i = 1/0;
//            startActivity(new Intent(MainActivity.this, ComponentsDemo.class));

        });
        binding.btnCustomview.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
        });
        binding.btnNetwork.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NetworkCheckActivity.class));
        });

        binding.btnSwitchLanguage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MulLanguage.class));
        });
        binding.btnLoginGoogle.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GoogleLoginActivity.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivityTAG","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivityTAG","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LaunchActivity","oMainActivityroyonDestroyonDestroy");
    }
}
