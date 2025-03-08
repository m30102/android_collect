package com.fan.frame.retrofit;


import android.util.Log;

import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.fan.frame.User;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUse {

    public void test2(){
    }

       static OkHttpClient okHttpClient  = new OkHttpClient.Builder().build();
    public static void test1(){
        //设置缓存时间为60秒
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(60, TimeUnit.SECONDS)
                .build();
//        CacheControl forceNetwork = CacheControl.FORCE_NETWORK;
        Request request = new Request.Builder().url("https://www.baidu.com/").build();
        Call call = okHttpClient.newCall(request);
        Log.e("序列化结果","id:"+Thread.currentThread().getId()+"");
        try {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("序列化结果","onFailure");
                    Log.e("序列化结果","onFailure id:"+Thread.currentThread().getId()+"");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("序列化结果","onResponse id:"+Thread.currentThread().getId()+"");
                    User user = GsonUtils.fromJson("asd", User.class);
                    if(user != null){
                        Log.e("序列化结果",user.toString());
                    }else{
                        Log.e("序列化结果","为空");
                    }


                /*    String string = response.body().string();
                    boolean successful = response.isSuccessful();
                    Response response2 = response.cacheResponse();

                    Response response1 = response.networkResponse();*/
                }

            });
        }catch (Exception e){
            Log.e("序列化结果","异常");
            e.printStackTrace();
        }

    }
}
