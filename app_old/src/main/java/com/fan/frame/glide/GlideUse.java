package com.fan.frame.glide;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

public class GlideUse extends AppCompatActivity {


    private void request(){
        RequestBuilder<Drawable> load = Glide.with(this).load("");

        Glide.with(this).load("").into(new ImageView(this));
//        getSupportFragmentManager().beginTransaction().add().commit();
    }
}
