package com.test.uicustomviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by qiaoda.zqd on 2016/5/3.
 */
public class SecondActivity extends Activity {

    private TitleLayout mTitleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);

        mTitleLayout = (TitleLayout)findViewById(R.id.second_title);

        mTitleLayout.setOnBackClickListener(new TitleLayout.OnBackClickListener() {
            @Override
            public void onBackClick() {
                Toast.makeText(SecondActivity.this, "You clicked Back button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
