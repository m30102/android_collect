package com.fan.collect.module.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.fan.collect.module.widget.databinding.ActivityWidgetBinding;


public class WidgetMainActivity extends AppCompatActivity {

    private ActivityWidgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWidgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCst.setOnClickListener(v -> {

        });
    }
}