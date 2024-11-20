package com.fan.collect.study.activity.launch.singletask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView

class Activity1:Activity() {

    private val TAG:String = "Activity1"
    private val hander:Handler = Handler();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "activity1"
        Log.e(TAG,"onCreate")
        setContentView(textView)

        textView.setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            hander.postDelayed(Runnable {
//                startActivity(Intent(this,Activity2::class.java))
            },3000);
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