//
// Created by qiaoda.zqd on 2016/3/28.
//

#include "com_example_jnidemo_JniTest.h"
#include <android/log.h>
#include <string.h>
#include <stdio.h>

#ifndef LOG_TAG
#define LOG_TAG "JNI_TEST"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#endif

// 静态注册方法
JNIEXPORT jstring JNICALL Java_com_example_jnidemo_JniTest_getStringFromNative(JNIEnv *env, jobject obj) {

    const char* str = "Hello JNI!";

    return env->NewStringUTF(str);
}

// 动态注册方法
JNIEXPORT jstring JNICALL native_getStringDynamic(JNIEnv *env, jobject obj, jstring name)
{
    char temp[216] = {0};

    //将java传入的jstring类型字符串转换为C++中的char*类型
    const char* pName = env->GetStringUTFChars(name, NULL);

//    //获取obj的Java类
//    jclass jniTestClass = env->GetObjectClass(obj);
//    //获取Java中的number字段的id(最后一个参数是number的签名)
//    jfieldID id_number = env->GetFieldID(jniTestClass, "number", "I");
//    //获取number的值
//    jint number = env->GetIntField(obj,id_number);
//
//    //获取Java中的max方法的id(最后一个参数是max方法的签名)
//    jmethodID id_max = env->GetMethodID(jniTestClass, "max", "(DD)D");
//    //调用max方法
//    jdouble doubles = env->CallDoubleMethod(obj,id_max,1.2,3.4);

    if (NULL != pName)
    {
        sprintf(temp, "Hello %s!", pName);
//        sprintf(temp, "Hello %d!", number);
//        sprintf(temp, "Hello %0.1f!", doubles);

        //java的name对象不需要再使用，通知虚拟机回收name
        env->ReleaseStringUTFChars(name, pName);
    }

    //将C++中的char*类型字符串转为jstring类型
    return env->NewStringUTF(temp);
}

//函数映射表
static JNINativeMethod methods[] = {
        {"getStringDynamic", "(Ljava/lang/String;)Ljava/lang/String;", (void*)native_getStringDynamic},
        //这里还可以添加很多其他映射函数
};


static int registerNatives(JNIEnv* env)
{
    const char* className = "com/example/jnidemo/JniTest";//指定要注册的类
    jclass jniTestClass = env->FindClass(className);

    //注册JNI函数
    env->RegisterNatives(jniTestClass, methods, sizeof(methods)/sizeof(methods[0]));

}

//JVM虚拟机加载完so库后，接着会在该库中查找JNI_OnLoad()函数，并调用。因此在这里进行动态注册。
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
{
    LOGE("---------------------------JNI_OnLoad---------------------------\n");
    JNIEnv* env;
    if ((vm->GetEnv((void**)&env, JNI_VERSION_1_4)) != JNI_OK)
    {
        return -1;
    }

    //进行动态注册
    registerNatives(env);

    return JNI_VERSION_1_4;
}