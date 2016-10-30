package com.test.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by qiaoda.zqd on 2016/10/30.
 */

public class FruitView {
    private Fruit mFruit;

    public FruitView(Fruit fruit) {
        this.mFruit = fruit;
    }

    public Fruit getFruit() {
        return this.mFruit;
    }

    public int getViewType() {
        return this.mFruit.getType();
    }

    public View getConvertView(Context context) {
        IViewHolder holder = null;
        View convertView = null;
        int type = this.getViewType();
        switch (type) {
            case Fruit.TYPE_1:
                ViewHolder viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.fruit_item, null);
                viewHolder.fruitImage = (ImageView)convertView.findViewById(R.id.fruit_image);
                viewHolder.fruitName = (TextView)convertView.findViewById(R.id.fruit_name);
                holder = viewHolder;
                break;
            case Fruit.TYPE_2:
                ViewHolder2 viewHolder2 = new ViewHolder2();
                convertView = LayoutInflater.from(context).inflate(R.layout.fruit_item2, null);
                viewHolder2.fruitImage = (ImageView)convertView.findViewById(R.id.fruit_image);
                viewHolder2.fruitName = (TextView)convertView.findViewById(R.id.fruit_name);
                holder = viewHolder2;
                break;
            default:
                break;
        }
        convertView.setTag(holder);
        return convertView;
    }

//    public IViewHolder getViewHolder(Context context) {
//        IViewHolder holder = null;
//        View view = null;
//        int type = this.getViewType();
//        switch (type) {
//            case Fruit.TYPE_1:
//                ViewHolder viewHolder = new ViewHolder();
//                view = LayoutInflater.from(context).inflate(R.layout.fruit_item, null);
//                viewHolder.fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
//                viewHolder.fruitName = (TextView)view.findViewById(R.id.fruit_name);
//                holder = viewHolder;
//                break;
//            case Fruit.TYPE_2:
//                ViewHolder2 viewHolder2 = new ViewHolder2();
//                view = LayoutInflater.from(context).inflate(R.layout.fruit_item2, null);
//                viewHolder2.fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
//                viewHolder2.fruitName = (TextView)view.findViewById(R.id.fruit_name);
//                holder = viewHolder2;
//                break;
//            default:
//                break;
//        }
//        return holder;
//    }

    public void refreashView(View convertView) {
        int type = this.getViewType();
        switch (type) {
            case Fruit.TYPE_1:
                ViewHolder holder1 = (ViewHolder)convertView.getTag();
                holder1.fruitImage.setImageResource(this.getFruit().getImageId());
                holder1.fruitName.setText(this.getFruit().getName());
                break;
            case Fruit.TYPE_2:
                ViewHolder2 holder2 = (ViewHolder2)convertView.getTag();
                holder2.fruitImage.setImageResource(this.getFruit().getImageId());
                holder2.fruitName.setText(this.getFruit().getName());
                break;
            default:
                break;
        }
    }
}
