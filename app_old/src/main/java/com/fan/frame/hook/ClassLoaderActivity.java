package com.fan.frame.hook;

import android.app.Instrumentation;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ConcurrentHashMap;

public class ClassLoaderActivity extends AppCompatActivity {


    /**
     * DexClassLoader：能够加载未安装的jar/apk/dex
     * PathClassLoader：只能加载系统中已经安装过的apk
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
