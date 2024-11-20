package com.fan.collect.study.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fan.collect.study.service.BindService.LocalBinder;

public class BinderActivity extends AppCompatActivity {
    BindService bindService;
    ServiceConnection conn =   new ServiceConnection() {
        @Override

        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder localBinder = (LocalBinder)service;
             bindService = localBinder.getService();
        }

        // 意外销毁才调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindService = null;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bindService(new Intent(this, BindService.class),conn, Service.BIND_AUTO_CREATE);

        unbindService(conn);
    }
}
