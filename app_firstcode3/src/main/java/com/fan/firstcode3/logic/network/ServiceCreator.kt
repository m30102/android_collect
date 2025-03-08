package com.fan.firstcode3.logic.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val REWRITE_CACHE_CONTROL_INTERCEPTOR = { chain: Interceptor.Chain ->
        val request = chain.request().newBuilder().header("Content-Type", "application/json;charset=UTF-8").build()
        val t1 = System.currentTimeMillis()
        Log.d("ServiceCreator",String.format("Sending request %s\n", request.url()))
        val response = chain.proceed(request)
        val t2 = System.currentTimeMillis()
        val content = response.body()!!.string()
        Log.d("ServiceCreator",String.format("Received response for %s cost %sms  %s\n", response.request().url(), (t2 - t1).toString() ,  "httpCode:" + response.code()))
        Log.d("ServiceCreator", "response content: $content")
        response.newBuilder().body(ResponseBody.create(response.body()!!.contentType(), content)).build()
    }

    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().apply {
                addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            }.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}