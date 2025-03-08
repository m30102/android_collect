package com.fan.frame.mvp.model;

import android.os.Handler;
import android.os.Message;

import com.fan.frame.mvp.present.Callback;

public class HttpModel implements Imodle{

    private Callback callback;

    public HttpModel(Callback callback) {
        this.callback = callback;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            callback.onResult((String) msg.obj);
        }
    };
    @Override
    public void request() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = handler.obtainMessage();
                    msg.obj = "从网络获取到的数据";
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        handler.removeCallbacksAndMessages(null);
    }
}
