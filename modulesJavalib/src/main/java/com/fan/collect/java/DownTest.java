package com.fan.collect.java;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class DownTest {

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    public int maxRetry = 10; //最大重试次数
                    private int retryNum = 0; //假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        while (!response.isSuccessful() && retryNum < maxRetry) {
                            retryNum++;
                            response = chain.proceed(request);
                        }
                        return response;
                    }
                })
                .callTimeout(Duration.ofSeconds(60))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
//                .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
        Request request = new Request.Builder().url("hta42_d20210423_m043412_c002_v0001155_t0017").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                long length = response.body().contentLength();
                Headers headers = response.headers();
                for (Pair<? extends String, ? extends String> header : headers) {

                    System.out.println("header:"+header.getFirst()+"   "+header.getSecond());
                }
               /* boolean redirect = response.isRedirect();
                System.out.println("redirect:"+redirect);
                InputStream is = response.body().byteStream();
                FileOutputStream fos = new FileOutputStream("d://aa.mp4");
                byte[] buf = new byte[100 *1024];
                int sum = 0, len = 0;
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    sum += len;
                    System.out.println("SUM:"+sum+" length:"+length);
                    double progress = (sum * 1.0f / length);
                    System.out.println("progress:"+progress);
                }*/
            }
        });
    }}
