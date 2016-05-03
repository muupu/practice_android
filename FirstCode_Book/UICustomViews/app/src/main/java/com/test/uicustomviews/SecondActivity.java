package com.test.uicustomviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by qiaoda.zqd on 2016/5/3.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);
    }
}
