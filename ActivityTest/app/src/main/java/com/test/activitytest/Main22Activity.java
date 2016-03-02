package com.test.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Main22Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main22Activity", "Task id is " + getTaskId());
        setContentView(R.layout.activity_main22);
    }
}
