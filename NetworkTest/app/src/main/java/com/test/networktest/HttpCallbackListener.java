package com.test.networktest;

/**
 * Created by qiaoda.zqd on 2016/3/4.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}

