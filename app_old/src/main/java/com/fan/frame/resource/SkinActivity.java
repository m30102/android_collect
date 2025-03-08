package com.fan.frame.resource;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import com.example.administrator.android_fan.R;

import java.lang.reflect.Field;


public class SkinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutInflaterFactoru(getLayoutInflater());
        LayoutInflaterCompat.setFactory2(getLayoutInflater(),new SkinFactory());
        setContentView(R.layout.activity_skin);
    }
    public void setLayoutInflaterFactoru(LayoutInflater original){
        LayoutInflater layoutInflater = original;
        try {
            Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
            mFactorySet.setAccessible(true);
            mFactorySet.set(layoutInflater,false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


}
