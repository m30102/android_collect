package com.fan.frame.materialdesign;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.blankj.utilcode.util.BarUtils;
import com.example.administrator.android_fan.R;
import com.fan.frame.utils.FLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

//https://blog.csdn.net/suyimin2010/article/details/81269881
public class DrawLayoutActivity extends AppCompatActivity {


    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.d_toolbar)
    protected Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dra_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // 支持点击左边图标 出现侧拉栏
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        // 沉浸式状态栏
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, android.R.color.transparent));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
