package com.test.uiwidgettest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button button;
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        imageView = (ImageView)findViewById(R.id.image_view);
        editText = (EditText)findViewById(R.id.edit_text);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
//                        String inputText = editText.getText().toString();
//                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

//                        imageView.setImageResource(R.drawable.wt_9348);

//                        if (progressBar.getVisibility() == View.GONE) {
//                            progressBar.setVisibility(View.VISIBLE);
//                        } else {
//                            progressBar.setVisibility(View.GONE);
//                        }
                        int progress = progressBar.getProgress();
                        progress += 10;
                        progressBar.setProgress(progress);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
