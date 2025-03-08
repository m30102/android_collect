package com.fan.frame.view.mooncustomview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.android_fan.R;

public class MainActivity extends AppCompatActivity {
    private InvalidTextView iv_text;
    private  RectView rv_rect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mooncustomview_layoutactivity_main);
        iv_text= (InvalidTextView) this.findViewById(R.id.iv_text);
        rv_rect= (RectView) this.findViewById(R.id.rv_rect);
        iv_text.setText("皇家马德里 C罗");
    }
}
