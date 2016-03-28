package com.example.jnidemo;

/**
 * Created by qiaoda.zqd on 2016/3/28.
 */
public class JniTest {

    static {
        System.loadLibrary("JniTest");
    }

    public native String getStringFromNative();
}
