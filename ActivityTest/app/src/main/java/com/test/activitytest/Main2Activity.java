package com.test.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main2Activity", this.toString());
        setContentView(R.layout.activity_main2);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Toast.makeText(Main2Activity.this, data, Toast.LENGTH_SHORT).show();
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //returnData();
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        returnData();
    }

    private void returnData()
    {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello MainActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
