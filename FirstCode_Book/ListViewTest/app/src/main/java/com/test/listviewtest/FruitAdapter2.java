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
 * 继承自BaseAdapter
 * Created by qiaoda.zqd on 2016/10/30.
 */

public class FruitAdapter2 extends BaseAdapter {

    private List<Fruit> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public FruitAdapter2(Context context, List<Fruit> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = (Fruit) getItem(position); // 获取当前项的Fruit实例
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, null);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView)view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }


}
