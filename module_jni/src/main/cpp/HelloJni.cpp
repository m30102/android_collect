#include <jni.h>
#include <string>
#include <android/log.h>
#include <iostream>
#define  LOG_TAG "HelloJni"
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__) // 定义LOGI类型





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
    const char *str_copy = (char*) env->GetStringUTFChars(msg, &isCopy);
    LOGI("printMsg %p %s",str_copy,str_copy);// printMsg 0x7fff6a57cba0 123qwe
    LOGI("isCopy2: %d",isCopy);// isCopy2: 1

    env->ReleaseStringUTFChars(msg,str_copy);




    return env->NewStringUTF("在 Native 层构造 Java String");
}