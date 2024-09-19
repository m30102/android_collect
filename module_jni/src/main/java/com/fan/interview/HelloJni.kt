package com.fan.interview

// https://zhuanlan.zhihu.com/p/547250316
class HelloJni {

    external fun printMsg(msg:String):String


    companion object{

        init {
            System.loadLibrary("cccccc")
        }
    }
}