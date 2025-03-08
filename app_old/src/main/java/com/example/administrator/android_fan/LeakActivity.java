package com.example.administrator.android_fan;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

public class LeakActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LeakDemo().testLeak(this);
    }
}
