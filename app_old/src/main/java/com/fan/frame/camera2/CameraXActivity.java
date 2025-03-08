package com.fan.frame.camera2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.android_fan.R;

public class CameraXActivity extends AppCompatActivity {


    private Button btn_start;
    private Button btn_end;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerax);
        btn_start = findViewById(R.id.btnstart);
        btn_end = findViewById(R.id.btnend);

    }


}
