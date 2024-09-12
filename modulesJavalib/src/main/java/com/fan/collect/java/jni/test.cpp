#include "com_fan_collect_java_jni_JniTest.h"
#include <stdio.h>

static char *str = NULL;
JNIEXPORT jstring JNICALL Java_com_fan_collect_java_jni_JniTest_get
        (JNIEnv *env, jobject thiz)
{
    printf("invoke get from C++\n");
    if(str != NULL){
        return env->NewStringUTF(str);
    }
    return env->NewStringUTF("default str from C++!");
}

JNIEXPORT void JNICALL Java_com_fan_collect_java_jni_JniTest_set
(JNIEnv *env,jobject thiz,
        jstring string){
    str = (char*)env->GetStringUTFChars(string,NULL);
    printf("invoke set from C++, %s\n ",str);
    env->ReleaseStringUTFChars(string,str);
}