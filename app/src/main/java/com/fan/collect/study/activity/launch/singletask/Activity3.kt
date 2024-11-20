package com.fan.collect.study.activity.launch.singletask

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity3:AppCompatActivity() {

    // 在启动模式为Standard下，单独使用TaskAffinity属性是无效的, 需要 launchMode="singleTask"
    /*
     *  android:taskAffinity="com.fan.collect2"
        android:launchMode="singleTask"
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "activity3"
        setContentView(textView)
    }
}