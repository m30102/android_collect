package com.fan.frame.materialdesign;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.BarUtils;
import com.example.administrator.android_fan.R;

public class TooBarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_ba);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");// 为了让标题居中
        setSupportActionBar(toolbar);

        // 第一种 用这个会导致toolbar的内容向上移动(要在根布局设置android:fitsSystemWindows="true"才行， 如果是颜色沉浸那么用第二种方法)
       BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
        // 第二种 修改状态栏颜色就OK，不用上面的工具类
//        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_LONG).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"settings",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }
}
