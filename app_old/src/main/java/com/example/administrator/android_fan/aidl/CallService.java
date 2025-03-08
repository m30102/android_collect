package com.example.administrator.android_fan.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.fan.frame.utils.FLogger;
//https://www.jianshu.com/p/1c70d7306808
import androidx.annotation.Nullable;

public class CallService extends Service {



    public void onCreate()
    {
        FLogger.e("onCreate");
    }



    // 1个或者多个客户端调用bindService，只会走一遍onCreate和一遍onBind. 注册到ServiceManager
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        FLogger.e("onBind mBinder:"+mBinder.hashCode());
        return mBinder;
    }

    @Override
    public void onDestroy() {
        FLogger.e("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        FLogger.e("onUnbind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        FLogger.e("onUnbind");
        return super.onUnbind(intent);
    }

    ICall.Stub mBinder = new ICall.Stub(){

        @Override
        public int add(int x, int y) throws RemoteException {
            return x +y;
        }

        @Override
        public int min(int x, int y) throws RemoteException {
            return x - y;
        }
    };
}
