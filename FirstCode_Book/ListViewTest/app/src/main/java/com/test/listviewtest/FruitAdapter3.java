package com.test.listviewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *  getViewTypeCount和getItemViewType的使用
 * Created by qiaoda.zqd on 2016/10/30.
 */

public class FruitAdapter3 extends BaseAdapter {

    private List<FruitView> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public FruitAdapter3(Context context, List<FruitView> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        Log.d("list", "mList.size()" + mList.size());
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    @Override
    public int getViewTypeCount() {
        return Fruit.TYPE_MAX_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FruitView fruit = (FruitView) getItem(position); // 获取当前项的Fruit实例
        if (convertView == null) {
            convertView = fruit.getConvertView(mContext);
        }
        fruit.refreashView(convertView);
        return convertView;
    }
}
