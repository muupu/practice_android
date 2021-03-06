package com.test.listviewtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private String[] data = {
            "Apple",
            "Banana",
            "Orange",
            "Watermelon",
            "Pear",
            "Grape",
            "Pineapple",
            "Strawberry",
            "Cherry",
            "Mango"
    };

    private List<FruitView> fruitList = new ArrayList<FruitView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);

        initFruits();

    }

    public void butClick(View view) {
        // FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
//        FruitAdapter2 adapter = new FruitAdapter2(MainActivity.this, fruitList);
        FruitAdapter3 adapter = new FruitAdapter3(MainActivity.this, fruitList);

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position).getFruit();
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.apple_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(apple));
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(banana));
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic, Fruit.TYPE_2);
        fruitList.add(new FruitView(orange));
        Fruit watermelon = new Fruit("WaterMelon", R.drawable.watermelon_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(watermelon));
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(pear));
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic, Fruit.TYPE_2);
        fruitList.add(new FruitView(grape));
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(pineapple));
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic, Fruit.TYPE_2);
        fruitList.add(new FruitView(strawberry));
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(cherry));
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic, Fruit.TYPE_1);
        fruitList.add(new FruitView(mango));
    }
}
