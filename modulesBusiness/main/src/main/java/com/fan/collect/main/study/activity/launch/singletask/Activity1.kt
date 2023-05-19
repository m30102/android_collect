package com.fan.collect.main.study.activity.launch.singletask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity1:Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "activity1"

        
        setContentView(textView)

        textView.setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
        }

    }
}