package com.example.auto.viewpagerdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {


    ViewPager pager = null;
    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();
    ArrayList<String> titleContainer = new ArrayList<String>();

    public static final int TAB_1 = 0;
    public static final int TAB_2 = 1;
    public static final int TAB_3 = 2;
    public static final int TAB_4 = 3;
    private static final int TAB_MAX = 4;
    private int mCurrentScreen = TAB_1;
    private View mViewTab1;
    private TextView mTextViewTab1;
    private View mTabBottomLine1;
    private View mViewTab2;
    private TextView mTextViewTab2;
    private View mTabBottomLine2;
    private View mViewTab3;
    private TextView mTextViewTab3;
    private View mTabBottomLine3;
    private View mViewTab4;
    private TextView mTextViewTab4;
    private View mTabBottomLine4;

    // demo3
    private ImageButton small_ibt;
    private ImageButton big_ibt;
    private ListView small_ls;
    private ListView big_ls;
    private String[]smallstrs=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n"};
    private String[]bigstrs=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
    private ArrayAdapter<String> smallAdapter;
    private ArrayAdapter<String>bigAdapter;
    private LinearLayout small_ll;
    private LinearLayout big_ll;
    private boolean isopen_small;
    private boolean isopen_big;

    public String TAG = "zqd";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        demo1();
//        demo2();
        demo3();
    }

    /**
     * Android ListView 4 一个页面显示两个ListView
     * http://blog.csdn.net/sunguanyong/article/details/40956099
     */
    private void demo3() {
        setContentView(R.layout.demo3);

        small_ibt=(ImageButton) this.findViewById(R.id.ibt_small);
        small_ibt.setBackgroundResource(R.drawable.list_close_arrow);
        big_ibt=(ImageButton) this.findViewById(R.id.ibt_big);
        big_ibt.setBackgroundResource(R.drawable.list_close_arrow);

        small_ls=(ListView) this.findViewById(R.id.ls_small);
        big_ls=(ListView) this.findViewById(R.id.ls_big);
        small_ll=(LinearLayout) this.findViewById(R.id.ll_small);
        big_ll=(LinearLayout) this.findViewById(R.id.ll_big);

        smallAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,smallstrs);
        bigAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, bigstrs);

        small_ls.setAdapter(smallAdapter);
        setListViewHeight(small_ls);

        big_ls.setAdapter(bigAdapter);
        setListViewHeight(big_ls);//必须跟在setAdapter()后面，不然无效

        small_ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(isopen_small){
                    isopen_small=false;
                    small_ibt.setBackgroundResource(R.drawable.list_close_arrow);
                    small_ls.setVisibility(View.GONE);
                }else{
                    isopen_small=true;
                    small_ibt.setBackgroundResource(R.drawable.list_open_arrow);
                    small_ls.setVisibility(View.VISIBLE);
                }
            }
        });
        big_ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(isopen_big){
                    isopen_big=false;
                    big_ibt.setBackgroundResource(R.drawable.list_close_arrow);
                    big_ls.setVisibility(View.GONE);
                }else{
                    isopen_big=true;
                    big_ibt.setBackgroundResource(R.drawable.list_open_arrow);
                    big_ls.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 设置listview高度的方法
     * @param listView
     */
    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);   //获得每个子item的视图
            listItem.measure(0, 0);   //先判断写入的widthMeasureSpec和heightMeasureSpec是否和当前的值相等，如果不等，重新调用onMeasure()
            totalHeight += listItem.getMeasuredHeight();   //累加不解释
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));   //加上每个item之间的距离
        listView.setLayoutParams(params);
    }

    private void demo2() {
        setContentView(R.layout.demo2);

        initSwitchBtn();
        initTab();
        pager = (ViewPager) this.findViewById(R.id.viewpager);
        DemoPagerAdapter adapter = new DemoPagerAdapter(viewContainter);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(TAB_MAX);
        pager.setCurrentItem(mCurrentScreen, true);
        updateButtonStatus(mCurrentScreen == TAB_1, mCurrentScreen == TAB_2, mCurrentScreen == TAB_3, mCurrentScreen == TAB_4);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "initSwitchBtn onPageScrollStateChanged : arg0" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
                updateButtonStatus(arg0 == TAB_1, arg0 == TAB_2, arg0 == TAB_3, arg0 == TAB_4);
            }

        });
    }

    private class DemoPagerAdapter extends PagerAdapter {
        public ArrayList<View> mViewList;

        public DemoPagerAdapter(ArrayList<View> views) {
            mViewList = views;
        }

        public void setListViews(ArrayList<View> views) {
            mViewList = views;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeViewAt(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }
    }

    /**
     * 更新上方的(四个)按钮状态
     *
     * @param tab1 按钮是否被选中
     * @param tab2 按钮是否被选中
     * @param tab3 按钮是否被选中
     * @param tab4 按钮是否被选中
     */
    private void updateButtonStatus(boolean tab1, boolean tab2, boolean tab3, boolean tab4) {
        mTextViewTab1.setSelected(tab1);
        mTextViewTab2.setSelected(tab2);
        mTextViewTab3.setSelected(tab3);
        mTextViewTab4.setSelected(tab4);
        mTabBottomLine1.setVisibility(tab1 ? View.VISIBLE : View.GONE);
        mTabBottomLine2.setVisibility(tab2 ? View.VISIBLE : View.GONE);
        mTabBottomLine3.setVisibility(tab3 ? View.VISIBLE : View.GONE);
        mTabBottomLine4.setVisibility(tab4 ? View.VISIBLE : View.GONE);
    }
    
    private void initSwitchBtn() {
        mTextViewTab1 = (TextView) this.findViewById(R.id.tab_1_tv);
        mTabBottomLine1 = this.findViewById(R.id.tab_bottom_line_1);
        mViewTab1 = this.findViewById(R.id.tab_1);
        mViewTab1.setOnClickListener(mTabClickListener);

        mTextViewTab2 = (TextView) this.findViewById(R.id.tab_2_tv);
        mTabBottomLine2 = this.findViewById(R.id.tab_bottom_line_2);
        mViewTab2 = this.findViewById(R.id.tab_2);

        mViewTab2.setOnClickListener(mTabClickListener);

        mTextViewTab3 = (TextView) this.findViewById(R.id.tab_3_tv);
        mTabBottomLine3 = this.findViewById(R.id.tab_bottom_line_3);
        mViewTab3 = this.findViewById(R.id.tab_3);
        mViewTab3.setOnClickListener(mTabClickListener);

        mTextViewTab4 = (TextView) this.findViewById(R.id.tab_4_tv);
        mTabBottomLine4 = this.findViewById(R.id.tab_bottom_line_4);
        mViewTab4 = this.findViewById(R.id.tab_4);
        mViewTab4.setOnClickListener(mTabClickListener);
    }

    /**
     * tab按钮切换事件监听
     */
    private View.OnClickListener mTabClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == mViewTab1) {
                onCategoryClick(TAB_1);
            } else if (v == mViewTab2) {
                onCategoryClick(TAB_2);
            } else if (v == mViewTab3) {
                onCategoryClick(TAB_3);
            } else if (v == mViewTab4) {
                onCategoryClick(TAB_4);
            }
        }
    };

    /**
     * 类别切换：分类
     *
     * @param category
     */
    public void onCategoryClick(int category) {
        pager.setCurrentItem(category);
    }

    private void initTab() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.tab1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.tab2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.tab3, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.tab4, null);
        //viewpager开始添加view
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);
    }

    private void demo1() {
        setContentView(R.layout.demo1);
        
        pager = (ViewPager) this.findViewById(R.id.viewpager);
//        tabStrip = (PagerTabStrip) this.findViewById(R.id.tabstrip);
//        //取消tab下面的长横线
//        tabStrip.setDrawFullUnderline(false);
//        //设置tab的背景色
//        tabStrip.setBackgroundColor(this.getResources().getColor(android.R.color.holo_blue_bright));
//        //设置当前tab页签的下划线颜色
//        tabStrip.setTabIndicatorColor(this.getResources().getColor(android.R.color.holo_red_dark));
//        tabStrip.setTextSpacing(200);

        View view1 = LayoutInflater.from(this).inflate(R.layout.tab1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.tab2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.tab3, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.tab4, null);
        //viewpager开始添加view
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);
        //页签项
        titleContainer.add("网易新闻");
        titleContainer.add("网易体育");
        titleContainer.add("网易财经");
        titleContainer.add("网易女人");

        pager.setAdapter(new PagerAdapter() {

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }
            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }
            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
//                return position;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }
        });

    }
}
