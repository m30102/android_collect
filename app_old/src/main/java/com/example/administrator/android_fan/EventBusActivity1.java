package com.example.administrator.android_fan;

import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fan.frame.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.ArrayBlockingQueue;

//
public class EventBusActivity1 extends Activity {

    private TextView jumpView;
    private TextView resultView;

    HttpURLConnection mHttpURLConnection;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(initView());
        initOclick();


        try {
            OutputStream outputStream = mHttpURLConnection.getOutputStream();
            outputStream.write(1);
            outputStream.close();
            InputStream inputStream = mHttpURLConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initOclick() {
        jumpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EventBusActivity1.this,EventBusActivity2.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        resultView.setText(messageEvent.getMessage());
    }

    private View initView() {

        LinearLayout linearLayout = new LinearLayout(this);
        jumpView = new TextView(this);
        jumpView.setText("to second");
        linearLayout.addView(jumpView);
        resultView = new TextView(this);
        resultView.setText("result");
        linearLayout.addView(resultView);
        return linearLayout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
