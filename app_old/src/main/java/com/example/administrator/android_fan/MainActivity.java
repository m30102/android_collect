package com.example.administrator.android_fan;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.android_fan.aidl.CallServiceActivity;
import com.example.administrator.android_fan.onSaveInstance.OnSaveInstanceActivity1;
import com.fan.frame.CoordinatorLayout.CoordiActivity1;
import com.fan.frame.CoordinatorLayout.CoordiActivity4;
import com.fan.frame.User;
import com.fan.frame.anim.AnimActivity;
import com.fan.frame.camera2.CameraActivity;
import com.fan.frame.camera2.CameraXActivity;
import com.fan.frame.canvas.CanvasActivity;
import com.fan.frame.constraintlayout.ConstraintTestActivity;
import com.fan.frame.hook.HookStartActivity;
import com.fan.frame.jetpack.LifeActivity;
import com.fan.frame.litepal.LitepalActivity;
import com.fan.frame.materialdesign.MdActivity;
import com.fan.frame.resource.SkinActivity;
import com.fan.frame.retrofit.OkhttpUse;
import com.fan.frame.retrofit.RetroTest;
import com.fan.frame.utils.FLogger;
import com.fan.frame.view.ViewActivity;
import com.fan.frame.view.moonviewslide.ViewSlideActivity;
import com.fan.frame.viewpager2.ViewPager2Activity;
import com.fan.frame.zxing.ZxingActivity;
//import com.leon.channel.helper.ChannelReaderUtil;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_greendao)
    Button btn_greendao;
    @BindView(R.id.btn_eventbus)
    Button btn_eventbus;

    @BindView(R.id.anim)
    Button btn_anim;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.leak)
    public void  startLeak(View view){
        startActivity(new  Intent(this,LeakActivity.class));
    }
    @OnClick(R.id.zxing)
    public void  startzxing(View view){
        startActivity(new  Intent(this, ZxingActivity.class));
    }
    @OnClick(R.id.btn_litepal)
    public void startLitepal(View view){
        startActivity(new  Intent(this, LitepalActivity.class));
    }
    @OnClick(R.id.btn_greendao)
    public void  startGreendaoAc(View view){
        startActivity(new  Intent(this,GreenDaoActivity.class));
    }
    @OnClick(R.id.btn_eventbus)
    public void  startEventBus(View view){
        startActivity(new  Intent(this,EventBusActivity1.class));
    }
    @OnClick(R.id.okdownload)
    public void startOkDownload(View view){
        startActivity(new  Intent(this,OkdownLoadActivity.class));
    }
    @OnClick(R.id.anim)
    public void startAnim(View view){
        startActivity(new  Intent(this, AnimActivity.class));
    }

    @OnClick(R.id.fragment)
    public void startFragmentTest(View view){
        startActivity(new Intent(this,FragmentTest.class));
    }
    @OnClick(R.id.recyle)
    public void startRecycle(View view){

        startActivity(new Intent(this,RecycleActivity.class));
    }

    @OnClick(R.id.aidl)
    public void startAidl(View view){
        startActivity(new Intent(this, CallServiceActivity.class));
    }

    @OnClick(R.id.coordi)
    public void startCoord(View view){
        startActivity(new Intent(this, CoordiActivity1.class));
    }
    @OnClick(R.id.coordi4)
    public void startCoord4(View view){
        startActivity(new Intent(this, CoordiActivity4.class));
    }
    @OnClick(R.id.material)
    public void startMd(View view){
        startActivity(new Intent(this, MdActivity.class));
    }

    @OnClick(R.id.jetpack)
    public void startJetPack(View view){
        startActivity(new Intent(this, LifeActivity.class));
    }
    @OnClick(R.id.viewpager2)
    public void startViewPager(View view){
        startActivity(new Intent(this, ViewPager2Activity.class));
    }
    @OnClick(R.id.camera2)
    public void startCamera(View view){
        startActivity(new Intent(this, CameraActivity.class));
    }
    @OnClick(R.id.canvas)
    public void startCanvas(View view){
        startActivity(new Intent(this, CanvasActivity.class));
    }
    @OnClick(R.id.viewbinding)
    public void startViewBind(View view){
        startActivity(new Intent(this, CanvasActivity.class));
    }
    @OnClick(R.id.rgb)
    public void startRgb(View view){
        startActivity(new Intent(this, CanvasActivity.class));
    }
    @OnClick(R.id.camerax)
    public void startCamerax(View view){
        startActivity(new Intent(this, CameraXActivity.class));
    }
    @OnClick(R.id.hook)
    public void hookstart(View view){
        startActivity(new Intent(this, HookStartActivity.class));
    }
    @OnClick(R.id.view_scroller)
    public void view_scroller(View view){
        startActivity(new Intent(this, ViewSlideActivity.class));
    }
    @OnClick(R.id.skin)
    public void skin(View view){
        startActivity(new Intent(this, SkinActivity.class));
    }
    @OnClick(R.id.mnview)
    public void mnview(View view){
        startActivity(new Intent(this, ViewActivity.class));
    }

    @OnClick(R.id.constraintLayout)
    public void ConstraintLayout(View view){
        startActivity(new Intent(this, ConstraintTestActivity.class));
    }

    @OnClick(R.id.saveInstance)
    public void saveInstance(View view){
        startActivity(new Intent(this, OnSaveInstanceActivity1.class));
    }


}
