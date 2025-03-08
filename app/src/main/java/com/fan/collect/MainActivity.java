package com.fan.collect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fan.collect.study.activity.launch.singletask.ComponentsDemo;
import com.fan.collect.study.appsflyer.AppsflyerActivity;
import com.fan.collect.study.coroutines.CoroutinesActivity;
import com.fan.collect.study.facebook.FaceBookLoginActivity;
import com.fan.collect.study.google.GoogleLoginActivity;
import com.fan.collect.study.image.ImageoptActivity;
import com.fan.collect.study.language.MulLanguage;
import com.fan.collect.study.network.NetworkCheckActivity;
import com.fan.collect.study.view.CustomViewActivity;
import com.fan.collect.databinding.ActivityMainMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("MainActivityTAG", "onCreate");
        super.onCreate(savedInstanceState);
        binding = ActivityMainMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnComponent.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ComponentsDemo.class));
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
        binding.btnLoginFacebook.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FaceBookLoginActivity.class));
        });
        binding.btnGallery.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ImageoptActivity.class));
        });

        binding.btnCoroutines.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CoroutinesActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivityTAG", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivityTAG", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LaunchActivity", "oMainActivityroyonDestroyonDestroy");
    }
}
