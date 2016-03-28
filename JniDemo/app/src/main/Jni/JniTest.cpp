//
// Created by qiaoda.zqd on 2016/3/28.
//

#include "com_example_jnidemo_JniTest.h"
#include <android/log.h>

#ifndef LOG_TAG
#define LOG_TAG "JNI_TEST"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#endif

JNIEXPORT jstring JNICALL Java_com_example_jnidemo_JniTest_getStringFromNative
        (JNIEnv *env, jobject obj) {

    const char* str = "Hello JNI!";

    LOGE("log string from ndk.");
    return env->NewStringUTF(str); // C++要使用env->
}
