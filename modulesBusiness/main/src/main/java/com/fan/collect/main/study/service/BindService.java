package com.fan.collect.main.study.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class BindService extends Service {


    private  LocalBinder binder = new LocalBinder();
    public class LocalBinder extends Binder{

        BindService getService(){
            return BindService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
