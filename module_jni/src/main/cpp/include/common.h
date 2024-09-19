//
// Created by fan on 2024/9/19.
//

#ifndef ANDROID_COLLECT_COMMON_H
#define ANDROID_COLLECT_COMMON_H
#include <jni.h>
#include <string>
#include <android/log.h>
#include <iostream>
#define  LOG_TAG "native"
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__) // 定义LOGI类型

#endif //ANDROID_COLLECT_COMMON_H
