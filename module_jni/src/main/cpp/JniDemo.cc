#include "common.h"

//
// Created by fan on 2024/9/19.
//

extern "C" JNIEXPORT jintArray JNICALL
Java_com_fan_interview_JniDemo_intArray(JNIEnv *env,
                                        jclass clazz,
                                        jintArray arr) {
    jboolean isCopy;
    jint *p = env->GetIntArrayElements(arr, &isCopy); // 通过此种方式获取指针需要ReleaseIntArrayElements
    jsize len = env->GetArrayLength(arr);// 无论什么类型的数组
    LOGI("isCopy = %d", isCopy); // isCopy = 1
    for (size_t i = 0; i < len; i++) {
        LOGI("index:%d is %d", i, p[i]);
    }
    p[0] = 999;
//      env->ReleaseIntArrayElements(arr,p,0);//若ReleaseIntArrayElements传0,把修改后的数组拷贝覆盖给原数组(java数组，也就是jintArray)，然后释放现在的数组指针p
//      env->ReleaseIntArrayElements(arr,p,JNI_COMMIT);// 把修改后的数组拷贝覆盖给原数组，然后不释放现在的数组指针p, 记得最后一定要释放
    env->ReleaseIntArrayElements(arr, p, JNI_ABORT); // 把修改后的数组拷贝不覆盖给原数组,释放现在的数组指针p


    jintArray arr2 = env->NewIntArray(5);

    // 修改数组方式1
    jint *p2 = env->GetIntArrayElements(arr2, nullptr);
    p2[0] = 11;
    p2[1] = 12;
    env->ReleaseIntArrayElements(arr2, p2, 0);// JNI_ABORT p2 能打印出11,12. 但返回java层是0,0 所以arr2没有被修改成功. 若传0，那么java层也能打印出11,12
    jsize len2 = env->GetArrayLength(arr);
    for (size_t i = 0; i < len2; i++) {
        LOGI("index:%d is %d", i, p2[i]);
    }

    // 修改数组方式2
//    jint a[5] = {111, 222, 333, 444, 555};
//  env->SetIntArrayRegion(arr2, 0, 5, a);

    return arr2;
}

void call_valist(JNIEnv *, jclass, jmethodID, ...);

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_fan_interview_JniDemo_strArray(JNIEnv *env,
                                        jclass clazz,
                                        jobjectArray arr) {

    jsize len = env->GetArrayLength(arr);
    for (size_t i = 0; i < len; i++) {
        jobject obj = env->GetObjectArrayElement(arr, i);
        const char *p = env->GetStringUTFChars((jstring) obj, nullptr);
        LOGI("index:%d  str:%s", i, p);
        env->ReleaseStringUTFChars(static_cast<jstring>(obj), p);
    }

    jclass cls = env->FindClass("java/lang/String");
//  jclass  cls = env->FindClass("Ljava/lang/String;");
    if (cls == nullptr) {
        LOGI("没有找到类");
        return nullptr;
    }

    const char *cstr[] = {
            "asd", "zx", "阿萨德", "专心1"
    };
    jobjectArray obj_arr = env->NewObjectArray(4, cls, nullptr);

    for (size_t i = 0; i < 4; i++) {
        jstring str = env->NewStringUTF(cstr[i]);
        env->SetObjectArrayElement(obj_arr, i, str);
    }
    return obj_arr;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_fan_interview_JniDemo_callStaticMethod(JNIEnv *env, jclass clazz) {

    /*
    jmethodID  mid = env->GetStaticMethodID(clazz,"testFun1","()V");
    if(mid == nullptr){
      LOGI("没有找到方法");
      return;
    }
    env->CallStaticVoidMethod(clazz,mid);
  */
/*
  jmethodID  mid = env->GetStaticMethodID(clazz,"testFun2","()Ljava/lang/String;");
  if(mid == nullptr){
    LOGI("没有找到方法");
    return;
  }
  jobject obj = env->CallStaticObjectMethod(clazz, mid);
  const char
      *string = env->GetStringUTFChars(static_cast<jstring>(obj), nullptr);
  LOGI("JAVA: %s",string);
  env->ReleaseStringUTFChars((jstring)obj, string);
*/

    jmethodID mid = env->GetStaticMethodID(clazz, "testFun3",
                                           "(Ljava/lang/String;ID)V");// I int D double
    if (mid == nullptr) {
        LOGI("没有找到方法");
        return;
    }
    jstring name = env->NewStringUTF("qqq");
    jint age = 100;
    jdouble height = 180.0;


    env->CallStaticVoidMethod(clazz, mid, name, age, height);// 传参调用方式1

    jvalue v[3];
    v[0].l = name;
    v[1].i = 11;
    v[2].d = height;
    env->CallStaticVoidMethodA(clazz, mid, v);// 传参调用方式2

    call_valist(env, clazz, mid, name, 13, 14.0);// 传参调用方式3



    // 调用其他类
    jclass cls = env->FindClass("java/lang/Math");
    if (cls == nullptr) {
        LOGI("没有找到类");
        return;// 返回会抛异常 ClassNotFoundException, java需要try
    }

    jmethodID randomMid = env->GetStaticMethodID(cls, "random", "()D");// I int D double
    jdouble d = env->CallStaticDoubleMethod(cls, randomMid);
    LOGI("random %f", d);

}

void call_valist(JNIEnv *env, jclass clazz, jmethodID mid, ...) {
    va_list vls;
    va_start(vls, clazz);
    env->CallStaticVoidMethodV(clazz, mid, vls);
    va_end(vls);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_fan_interview_JniDemo_testStaticField(JNIEnv *env, jclass clazz) {
    jclass cls = env->FindClass("java/lang/Math");
    jfieldID fid = env->GetStaticFieldID(cls, "PI", "D");
    jdouble pi = env->GetStaticDoubleField(cls, fid);
    LOGI("pi %f", pi);

    jclass ucls = env->FindClass("com/fan/interview/User");
    jfieldID msgId = env->GetStaticFieldID(ucls, "msg", "Ljava/lang/String;");
    jstring msgnew = env->NewStringUTF("new user");
    env->SetStaticObjectField(ucls, msgId, msgnew);

}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_fan_interview_JniDemo_createUser(JNIEnv *env, jclass clazz) {
    jclass ucls = env->FindClass("com/fan/interview/User");

    jmethodID consId = env->GetMethodID(ucls, "<init>", "()V");

    jmethodID consId2 = env->GetMethodID(ucls, "<init>", "(JLjava/lang/String;I)V");

    jobject obj = env->NewObject(ucls, consId);

    jlong id = 1000;
    jobject obj2 = env->NewObject(ucls, consId2, id, env->NewStringUTF("哈哈"), 10);
//  jobject obj = env->AllocObject(ucls);
    return obj2;


}