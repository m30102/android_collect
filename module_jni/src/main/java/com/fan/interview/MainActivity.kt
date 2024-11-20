package com.fan.interview

import android.util.Log
import com.fan.collect.base.BaseVBActivity
import com.fan.interview.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : BaseVBActivity<ActivityMainBinding>() {

    val helloJni:HelloJni = HelloJni()
    override fun initData() {
    }

    override fun initView() {
        binding.btnStr2cpp.setOnClickListener {

            helloJni.printMsg("123qwe是")
        }
        binding.btnIntArr2cpp.setOnClickListener {
//            val arr:Array<Int> = arrayOf(1,2,3,4,5)
            val arr:IntArray = intArrayOf(10,20,30,40,50)
            val res = JniDemo.intArray(arr)
            Log.e(TAG,"arr = " + arr.contentToString())
            Log.e(TAG,"res = " + res.contentToString())

        }
        binding.btnStrArr2cpp.setOnClickListener{
            var arr = arrayOf("q","w ","1","是")
            val res = JniDemo.strArray(arr)
            Log.e(TAG,"arr = " + arr.contentToString())
            Log.e(TAG,"res = " + res.contentToString())

        }
        binding.btnCppCallJavaStatic.setOnClickListener {
            try {
                JniDemo.callStaticMethod()
                Math.random()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        binding.btnCppGetJavaStatic.setOnClickListener {
            Log.e(TAG,User.getMsg())
            JniDemo.testStaticField()
            Log.e(TAG,User.getMsg())

        }
        binding.btnCppGetJavaObj.setOnClickListener {
            var u = JniDemo.createUser()
            Log.e(TAG, "createUser:$u");
        }
    }


    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}