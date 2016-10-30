package com.test.listviewtest;

/**
 * Created by qiaoda.zqd on 2016/3/3.
 */
public class Fruit {
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_MAX_COUNT = TYPE_2 + 1;

    private String name;
    private int imageId;
    private int type;

    public Fruit(String name, int imageId, int type) {
        this.name = name;
        this.imageId = imageId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getType() {
        return type;
    }


}
