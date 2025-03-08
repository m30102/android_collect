package com.example.administrator.android_fan.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.code.common.utils.misc.FLogger;

import androidx.annotation.Nullable;

public class CallServiceActivity extends Activity {

    private ICall iCall;
    private ServiceConnection mServiceConn = new ServiceConnection(){


        // 1个或者多个客户端调用同一个bindService，Service只会走一遍onCreate和一遍onBind
        // 如果是同进程 service:onBind mBinder:541363015  onServiceConnected service:541363015 iCall:541363015
        // 如果是1或者多个不同进程:service: onBind mBinder:541363015  client:onServiceConnected service:35592194 iCall:874120467
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCall = ICall.Stub.asInterface(service);// 不用调用别人，直接返回CallService的mBinder
            FLogger.e("onServiceConnected service:"+service.hashCode()+" iCall:"+iCall.hashCode());


            try {
                int x = iCall.add(1,2);
                FLogger.e("result:"+x);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            FLogger.e("onServiceDisconnected");
            iCall = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);

        textView.setText("测试");
        setContentView(textView);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent();
                intent.setAction("sssss");
                intent.setPackage("com.example.administrator.android_fan");
                bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConn);
    }
}
