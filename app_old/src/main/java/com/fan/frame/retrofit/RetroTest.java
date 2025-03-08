package com.fan.frame.retrofit;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroTest {
//https://www.jianshu.com/p/661c0459b375
    public static final String TAG  = "MyTest";

    public void testGet(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")// 基本url
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResponseBody> repos = service.listRepos("octocat");// https://api.github.com/users/octocat/repos
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG,"get:"+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"onFailure");
                t.printStackTrace();
            }
        });

    }

    public void testPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService apiService = retrofit.create(GitHubService.class);
        Call<GitHubService.PostQueryInfo> call = apiService.search("yuantong","500379523313");//http://www.kuaidi100.com/query?type=快递公司代号&postid=快递单号
        call.enqueue(new Callback<GitHubService.PostQueryInfo>(){
            @Override
            public void onResponse(Call<GitHubService.PostQueryInfo> call, Response<GitHubService.PostQueryInfo> response){
                try {
                    String string = response.raw().body().string();
                    Log.i(TAG,"post原:"+string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"post转:"+response.body().toString());
            }
            @Override
            public void onFailure(Call<GitHubService.PostQueryInfo> call, Throwable t){
                Log.i(TAG,"onFailure");
                t.printStackTrace();
            }
        });
     /*   Call.aidl<ResponseBody> call = apiService.search("yuantong","500379523313");
        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call.aidl<ResponseBody> call, Response<ResponseBody> response){

                try {
                    Log.i(TAG,"post:"+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call.aidl<ResponseBody> call, Throwable t){
                Log.i(TAG,"onFailure");
                t.printStackTrace();
            }
        });*/

    }



    private void okhttpTest(){
        // 简易get
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        okhttp3.Call call = okHttpClient.newCall(request);
//        okhttp3.Response execute = call.execute();
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if(!response.isSuccessful()){

                }

                Headers headers = response.headers();
                headers.name(1);
                headers.value(1);
                response.body().string();
                response.body().byteStream();
                response.body().bytes();
            }
        });



        // 普通post
        File sdcache = Environment.getExternalStorageDirectory();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        OkHttpClient httpClient = builder.build();




        String jsonStr = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonStr);
        Headers headers = new Headers.Builder().add("test","12")
                .add("test1","456").build();

//        FormBody formBody = new FormBody.Builder().add("key", "value").build();
//        Request request1 = new Request.Builder().url("https://www.baidu.com").post(formBody).headers(headers).build();

        CacheControl cache = new CacheControl.Builder().maxAge(100, TimeUnit.MILLISECONDS).build();// 设置缓存的最大有效时间为100毫秒
        Request request1 = new Request.Builder()
                .url("https://www.baidu.com")
                .post(requestBody)
                .headers(headers)
                .cacheControl(cache)//
                .build();
        httpClient.newCall(request1).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

            }
        });

    }
}
