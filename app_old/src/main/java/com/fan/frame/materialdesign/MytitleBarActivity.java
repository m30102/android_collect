package com.fan.frame.materialdesign;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.BarUtils;
import com.example.administrator.android_fan.R;

public class MytitleBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mytitle);
        View titleBar = findViewById(R.id.mytitle);

        // 让状态栏和titleBar的背景色保持一致。（加上android:fitsSystemWindows="true"，不然titleBar会使用状态栏空间）
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
        titleBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

    }
}
