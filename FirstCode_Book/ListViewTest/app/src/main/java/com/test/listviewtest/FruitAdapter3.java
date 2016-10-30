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

    private List<Fruit> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public FruitAdapter3(Context context, List<Fruit> list) {
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
        return mList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return Fruit.TYPE_MAX_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = (Fruit) getItem(position); // 获取当前项的Fruit实例
        int type = fruit.getType();
        ViewHolder viewHolder = null;
        ViewHolder2 viewHolder2 = null;
        if (convertView == null) {
            switch (type) {
                case Fruit.TYPE_1:
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, null);
                    viewHolder.fruitImage = (ImageView)convertView.findViewById(R.id.fruit_image);
                    viewHolder.fruitName = (TextView)convertView.findViewById(R.id.fruit_name);
                    convertView.setTag(viewHolder); // 将ViewHolder存储在View中
                    break;
                case Fruit.TYPE_2:
                    viewHolder2 = new ViewHolder2();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.fruit_item2, null);
                    viewHolder2.fruitImage = (ImageView)convertView.findViewById(R.id.fruit_image);
                    viewHolder2.fruitName = (TextView)convertView.findViewById(R.id.fruit_name);
                    convertView.setTag(viewHolder2); // 将ViewHolder2存储在View中
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case Fruit.TYPE_1:
                    viewHolder = (ViewHolder) convertView.getTag();// 重新获取ViewHolder
                    break;
                case Fruit.TYPE_2:
                    viewHolder2 = (ViewHolder2)convertView.getTag();
                    break;
                default:
                    break;
            }
        }
        switch (type) {
            case Fruit.TYPE_1:
                viewHolder.fruitImage.setImageResource(fruit.getImageId());
                viewHolder.fruitName.setText(fruit.getName());
                break;
            case Fruit.TYPE_2:
                viewHolder2.fruitImage.setImageResource(fruit.getImageId());
                viewHolder2.fruitName.setText(fruit.getName());
                break;
            default:
                break;
        }
        return convertView;
    }
}
