package com.fan.frame.jetpack;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fan.frame.utils.FLogger;
import com.jeremyliao.liveeventbus.LiveEventBus;

public class LiveDataBusActivityB extends AppCompatActivity {

    public static LiveDataBusActivityA liveDataBusActivityA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("跳转A");
        setContentView(button);
        button.setOnClickListener(v -> {
            liveDataBusActivityA.data.setValue("返回A");
            liveDataBusActivityA.data.setValue("返回A1");
            liveDataBusActivityA.data.setValue("返回A2");
            finish();
        });



        /*LiveEventBus.get("test").observe(this,o -> {
            FLogger.d("收到"+o);
        });*/
        LiveEventBus.get("test").observeSticky(this,o -> {
            FLogger.d("收到"+o);
        });


        /*LiveDataBus.get().getChannel("test",String.class).observe(this,s -> {
            FLogger.d("收到:"+s);
        });*/
    }
}
