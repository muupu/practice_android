package com.test.uicustomviews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TitleLayout mTitleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mTitleLayout = (TitleLayout)findViewById(R.id.main_title);
        mTitleLayout.setOnBackClickListener(new TitleLayout.OnBackClickListener(){
            @Override
            public void onBackClick() {
                Toast.makeText(MainActivity.this, "You clicked Back button", Toast.LENGTH_SHORT).show();
            }
        });
        mTitleLayout.setOnEditListener(new TitleLayout.OnEditClickListener() {
            @Override
            public void onEditClick() {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
