package com.fan.collect.main.study.activity.launch.singletask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2:Activity() {

    private val TAG:String = "Activity2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"onCreate")
        val textView = TextView(this)
        textView.text = "activity2"
        setContentView(textView)

        textView.setOnClickListener {
            startActivity(Intent(this,Activity3::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.e(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG,"onRestart")
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG,"onNewIntent")
    }
}