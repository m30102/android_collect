package com.fan.collect.main.study.activity.launch.singletask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import androidx.annotation.Nullable;
import com.fan.collect.module.main.R;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_theme);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }
}
