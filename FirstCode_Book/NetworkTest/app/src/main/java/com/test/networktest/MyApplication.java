package com.test.networktest;

import android.app.Application;
import android.content.Context;

/**
 * Created by qiaoda.zqd on 2016/3/4.
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
