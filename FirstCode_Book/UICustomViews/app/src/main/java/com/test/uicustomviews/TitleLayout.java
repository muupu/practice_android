package com.test.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by qiaoda.zqd on 2016/3/2.
 */
public class TitleLayout extends LinearLayout {

    private OnBackClickListener mOnBackListener;

    /**
     * 返回按钮的回调函数
     * @param listener
     */
    public void setOnBackClickListener(OnBackClickListener listener) {
        this.mOnBackListener = listener;
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.titile, this);

        TextView titleBack = (TextView)findViewById(R.id.title_back);
        TextView titleEdit = (TextView)findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Edit button", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onBack() {
        if (mOnBackListener != null) {
            mOnBackListener.onBackClick();
        }
        ((Activity)getContext()).finish();
    }

    public interface OnBackClickListener {
        void onBackClick();
    }


}
