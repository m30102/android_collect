package com.fan.interview

// https://zhuanlan.zhihu.com/p/547250316 JNI 从入门到实践，爆肝万字详解
class HelloJni {

    external fun printMsg(msg:String):String


    companion object{

        init {
            System.loadLibrary("cccccc")
        }
    }
}