package com.fan.frame.mvp.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.android_fan.R;
import com.fan.frame.mvp.present.Ipresent;
import com.fan.frame.mvp.present.Presenter;

import androidx.appcompat.app.AppCompatActivity;

public class MVPActivity extends AppCompatActivity implements Iview {
    private Button button;
    private TextView textView;
    private Ipresent presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_activity_test);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);
        presenter = new Presenter(this);
        button.setOnClickListener( v -> presenter.request());
    }

    @Override
    public void updateTv(String text) {
        textView.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
