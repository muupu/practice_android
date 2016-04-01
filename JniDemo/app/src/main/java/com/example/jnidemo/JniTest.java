package com.example.jnidemo;

/**
 * Created by qiaoda.zqd on 2016/3/28.
 */
public class JniTest {

    static {
        System.loadLibrary("JniTest");
    }

    // 静态注册方法
    public native String getStringFromNative();
    // 动态注册方法
    public native String getStringDynamic(String name);
}
