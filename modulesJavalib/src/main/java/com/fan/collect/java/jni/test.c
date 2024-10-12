#include "com_fan_collect_java_jni_JniTest.h"
#include <stdio.h>
#include <time.h>
//static char *str = "default c str";
static char *str = NULL;
/**
 * 结构体函数指针调用说明
 *  void ToLower(char *s){
 *    printf("ToLower %s\n",s);
 *  }
 *
 *  struct A{
 *       void (*pf) (char*);// 函数指针
 *  }
 *  struct A a= {ToLower};
 *  struct A  *a1 = &a;
 *  (*a.pf)("qqq")// 结构体的函数指针*运算后成为函数然后调用
 *  a.pf("qqq")// 结构体直接 点访问函数指针, 函数指针和函数名等价 直接调用
 *  a1->pf("qqq")// 结构指针 通过->访问函数指针, 函数指针和函数名等价 然后调用
 *
 *
 *  jni 目的是为了调用JNINativeInterface结构体内部的的各个函数指针
 *  所以一般通过JNINativeInterface*  -> 箭头方式调用。
 *
 *  C 中JNIEnv 这样定义
 *  typedef const struct JNINativeInterface* JNIEnv;
 *  env是一个指向JNIEnv的一级指针，而JNIEnv又是一个JNINativeInterface的指针
 *  所以env是一个指向JNINativeInterface的二级指针
 *  env = JNIEnv *
 *  (*env) = JNIEnv
 *
 *  env  =  JNINativeInterface **
 *  (*env)  = JNINativeInterface *
 *
 *  所以 (*env)->函数指针名 等于 JNINativeInterface* ->函数指针名  这样就访问到JNINativeInterface内部的函数指针了
 *
 *
 *  c++中JNIEnv 这样定义 , 参考struct_test2.cpp的结构体说明。
 *  typedef _JNIEnv JNIEnv; _JNIEnv本身就是一个struct , 它内部封装了 JNINativeInterface*
 *  env是一个指向JNIEnv的一级指针, JNIEnv = _JNIEnv
 *  env = JNIEnv *
 *  因为env就是指向结构体的一个指针
 *  直接通过 env-> 函数指针名 等于 调用这个结构体的函数，等于间接 JNINativeInterface* ->
 *
 *  (*env) = JNIEnv
 *  (*env).函数名
 *  env->ReleaseStringUTFChars(msg,str_copy);
    (*env).ReleaseStringUTFChars(msg,str_copy)
 */



JNIEXPORT jstring JNICALL Java_com_fan_collect_java_jni_JniTest_get
        (JNIEnv *env, jobject thiz){

    time_t t;
    time(&t);
    printf("invoke get from C, %ld\n",t);
    if(str != NULL){
        return (*env)->NewStringUTF(env,str);
    }
    return (*env)->NewStringUTF(env,"default str from C!");
}

JNIEXPORT void JNICALL Java_com_fan_collect_java_jni_JniTest_set
(JNIEnv *env,jobject thiz,
        jstring string){
    str = (char*)(*env)->GetStringUTFChars(env,string,NULL);
    printf("invoke set from C, %s\n ",str);
    (*env)->ReleaseStringUTFChars(env,string,str);
}