package com.example.administrator.android_fan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fan.frame.recycle.base.RecycleBaseActivity;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecycleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.recycle_base)
    public void startRecycleBase(View view){
        startActivity(new Intent(this, RecycleBaseActivity.class));
    }
}
