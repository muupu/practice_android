package com.example.imageloader;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mButton;
    private ImageView mImageView;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mImageView = (ImageView)findViewById(R.id.imageView);

    }

    @Override
    public void onClick(View v) {
        String url = "http://a.hiphotos.baidu.com/zhidao/pic/item/2f738bd4b31c87017158c526267f9e2f0608ff00.jpg";
        mImageLoader = new ImageLoader();
        mImageLoader.displayImage(url, mImageView);
    }
}
