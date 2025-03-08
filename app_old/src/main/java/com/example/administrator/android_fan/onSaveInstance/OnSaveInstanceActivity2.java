package com.example.administrator.android_fan.onSaveInstance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OnSaveInstanceActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("OnSaveInstanceActivity2");
        textView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("data","111222");
            setResult(3,intent);
            finish();
        });
        setContentView(textView);
    }
}
