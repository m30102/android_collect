#include "com_fan_collect_java_jni_JniTest.h"
#include <stdio.h>
#include <time.h>
//static char *str = "default c str";
static char *str = NULL;

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