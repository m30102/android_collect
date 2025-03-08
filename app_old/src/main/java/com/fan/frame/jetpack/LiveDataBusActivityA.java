package com.fan.frame.jetpack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ActivityUtils;
import com.fan.frame.utils.FLogger;
import com.jeremyliao.liveeventbus.LiveEventBus;

//https://zhuanlan.zhihu.com/p/40680568
//https://www.jianshu.com/p/f9f86bd928a1
public class LiveDataBusActivityA extends AppCompatActivity {
    public MutableLiveData<String> data = new MutableLiveData<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("跳转B");
        setContentView(button);
        LiveDataBusActivityB.liveDataBusActivityA = this;
        data.observe(this,s -> {
            FLogger.e("fff",s);
        });


        /**
         * true：整个生命周期（从onCreate到onDestroy）都可以实时收到消息
         * false：激活状态（Started）可以实时收到消息，非激活状态（Stoped）无法实时收到消息，需等到Activity重新变成激活状态，方可收到消息. 默认true
         */
        LiveEventBus.config().lifecycleObserverAlwaysActive(false);
        button.setOnClickListener(v ->{
            //
//            LiveDataBus.get().getChannel("test",String.class).setValue(" this is a liveData msg");



            LiveEventBus.get("test").post(" this is liveEventBus msg");
            ActivityUtils.startActivity(LiveDataBusActivityB.class);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FLogger.d("onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        FLogger.d("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        FLogger.d("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        FLogger.d("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FLogger.d("onRestart");
    }

}
