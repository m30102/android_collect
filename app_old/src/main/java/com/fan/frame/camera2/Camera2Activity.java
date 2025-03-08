package com.fan.frame.camera2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.android_fan.R;

public class Camera2Activity extends AppCompatActivity {

    private TextureView textureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        initView();
    }

    private void initView() {
        textureView = findViewById(R.id.textureview);
        findViewById(R.id.startcamera2).setOnClickListener((v)->{});
    }
}
