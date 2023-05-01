package com.fan.collect.main.study.activity.launch.singletask

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "activity2"
        setContentView(textView)

        textView.setOnClickListener {
            startActivity(Intent(this,Activity3::class.java))
        }
    }
}