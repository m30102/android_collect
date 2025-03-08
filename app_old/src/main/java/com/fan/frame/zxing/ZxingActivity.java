package com.fan.frame.zxing;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.android_fan.R;
import com.google.zxing.client.android.CaptureActivity;

public class ZxingActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxinga);
        findViewById(R.id.btn_start).setOnClickListener((v)->{
            Intent intent=new Intent(ZxingActivity.this, CaptureActivity.class);
            startActivityForResult(intent,1001);
        });
        requestPermissions(new String[]{Manifest.permission.CAMERA},0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1001 && resultCode == Activity.RESULT_OK){
//            String result=data.getStringExtra(CaptureActivity.KEY_DATA);
        }
    }
}
