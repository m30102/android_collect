/*
#include <jni.h>
#include <string>
#include <android/log.h>
#include <iostream>
#define  LOG_TAG "HelloJni"
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__) // 定义LOGI类型
*/

#include "common.h"



extern "C"
JNIEXPORT jstring JNICALL
Java_com_fan_interview_HelloJni_printMsg(JNIEnv *env, jobject thiz, jstring msg) {

   /*
    const char *str_null = (char*) env->GetStringUTFChars(msg, nullptr);
    LOGI("printMsg %p %s",str_null,str_null);//  printMsg 0x7fff6a57cb78 123qwe
    const char *str_noCopy =  (char*) env->GetStringUTFChars(msg, JNI_FALSE);
    LOGI("printMsg %p %s",str_noCopy,str_noCopy);//  printMsg 0x7fff6a57cb90 123qwe

    */


    jboolean isCopy;
    LOGI("isCopy1: %d",isCopy);// isCopy1: 0
//    const char *str_copy = (char*) env->GetStringUTFChars(msg, &isCopy);
    const char *str_copy =  env->GetStringUTFChars(msg, &isCopy);//utf-8
    jsize size = env->GetStringLength(msg);//
    LOGI("printMsg 指针:%p 内容:%s 长度:%d ",str_copy,str_copy,size);
    // GetStringUTFLength 指针:0x7fff6a77cac8 内容:123qwe 长度:6   字节长度
    // GetStringUTFLength 指针:0x7fff6a700cf0 内容:123qwe是 长度:9  字节长度
    // GetStringLength 指针:0x7fff6a700d60 内容:123qwe是 长度:7
    LOGI("isCopy2: %d",isCopy);// isCopy2: 1
    env->ReleaseStringUTFChars(msg,str_copy);
    return env->NewStringUTF("在 Native 层构造 Java String");
}




extern "C"
JNIEXPORT void JNICALL
Java_com_fan_interview_JniDemo_haha(JNIEnv *env, jclass clazz) {
    LOGI("hahaahhahhaha\n");
}
