package com.example.toutiao;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bytedance.applog.AppLog;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.InitConfig;

public class ToutiaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        InitConfig initConfig = new InitConfig();
//        initConfig.setImeiEnable(false);
//        initConfig.setAppImei();
        AppLog.setOaidObserver(new IOaidObserver() {
            @Override
            public void onOaidLoaded(@NonNull Oaid oaid) {

            }
        });
        new com.bytedance.bdtracker.z4(this).a();
        new com.bytedance.bdtracker.z4(this).a(new IOaidObserver() {
            @Override
            public void onOaidLoaded(@NonNull Oaid oaid) {

            }
        });




    }
}
