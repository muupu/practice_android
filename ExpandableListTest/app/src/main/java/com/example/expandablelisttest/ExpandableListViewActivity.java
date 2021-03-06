package com.example.expandablelisttest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 参考资料：
 * http://www.blogjava.net/jjshcc/archive/2012/02/29/371030.html
 */
public class ExpandableListViewActivity extends Activity {
    private int lastVisibleItemPosition;// 上次滑动位置
    private boolean isScrolling = false;// 是否滑动
    private static final int SCROLL_UP = 1; // 向上滑动
    private static final int SCROLL_DOWN = 1; // 向上滑动
    private int mScrollAction = 0;
    private SparseIntArray expandGroups = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
    }

    public void btn_click(View view) {
        test2();
    }

    private void test2() {
        List<GroupItem> listData = new ArrayList<GroupItem>();
        for (int i = 0; i < 20; i++) {
            GroupItem groupItem = new GroupItem();
            groupItem.groupName = "AAA" + i;
            groupItem.subNames = new ArrayList<>();
            for (int j = 0; j < 4 ; j++) {
                groupItem.subNames.add("BBB" + j);
            }
            listData.add(groupItem);
        }

        DataListAdapter listAdapter = new DataListAdapter(ExpandableListViewActivity.this, listData);
        final ExpandableListView expandListView = (ExpandableListView) this.findViewById(R.id.ecpandable);
        expandListView.setAdapter(listAdapter);
//        // 点击group item展开的时候，其他已展开的group收起
//        expandListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int lastPosition = -1;
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if((lastPosition != -1) && (groupPosition != lastPosition) )
//                    expandListView.collapseGroup(lastPosition );
//                lastPosition = groupPosition;
//            }
//        });
        expandListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                expandGroups.put(groupPosition, groupPosition);
                Log.d("elistview", "onGroupExpand groupPosition=" + groupPosition + ",expandsize=" + expandGroups.size());
                printExpandGroups(expandGroups);

            }
        });
        expandListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expandGroups.delete(groupPosition);
                Log.d("elistview", "onGroupCollapse groupPosition=" + groupPosition + ",expandsize=" + expandGroups.size());
                printExpandGroups(expandGroups);
            }
        });

        // 监听listview滚动
        expandListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("elistview", "onScrollStateChanged: scrollState=" + scrollState);
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                } else {
                    isScrolling = false;
                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                        Log.d("elistview", "onScrollStateChanged: scrollState=SCROLL_STATE_IDLE");
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            //TODO
                            Log.d("elistview", "onScrollStateChanged: 滚动到底部");
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Log.d("elistview", "onScroll: firstVisibleItem=" + firstVisibleItem + ",visibleItemCount=" + visibleItemCount + ",totalItemCount=" + totalItemCount);
//                testOnScroll1(expandListView, view, firstVisibleItem, visibleItemCount, totalItemCount);
                testOnScroll2(expandListView, view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        });
    }

    private void printExpandGroups(SparseIntArray expandGroups) {
        for (int i = 0; i < expandGroups.size(); i++) {
            Log.d("elistview", "expandGroups:" + expandGroups.valueAt(i));
        }
    }

    private void testOnScroll1(ExpandableListView expandListView, AbsListView view, int firstVisibleItem,
                               int visibleItemCount, int totalItemCount) {
        int npos = view.pointToPosition(0, 0); // 其实就是firstVisibleItem
        Log.d("elistview", "onScroll: pointToPosition=" + npos);
        if (npos != AdapterView.INVALID_POSITION) {
            long pos = expandListView.getExpandableListPosition(npos);
            int childPos = ExpandableListView.getPackedPositionChild(pos);// 获取第一行child的id
            int groupPos = ExpandableListView.getPackedPositionGroup(pos);// 获取第一行group的id
            if (childPos == AdapterView.INVALID_POSITION) {// 滚动到第一行是group
                View groupView = expandListView.getChildAt(npos - expandListView.getFirstVisiblePosition());// 第一行的view
//                        indicatorGroupHeight = groupView.getHeight();// 获取group的高度
//                        indicatorGroup.setVisibility(View.GONE);// 隐藏指示器
            } else {// 滚动到第一行是child
//                        indicatorGroup.setVisibility(View.VISIBLE);// 滚动到第一行是child，就显示指示器
            }
        }
    }

    // 滚动过程中将可视区域以外已展开的group收起
    private void testOnScroll2(ExpandableListView expandListView, AbsListView view, int firstVisibleItem,
                               int visibleItemCount, int totalItemCount) {
        if (isScrolling) {
            if (firstVisibleItem > lastVisibleItemPosition) {
                mScrollAction = SCROLL_UP;
                Log.d("elistview_visible", "上滑");
            } else if (firstVisibleItem < lastVisibleItemPosition) {
                mScrollAction = SCROLL_DOWN;
                Log.d("elistview_visible", "下滑");
            }
            lastVisibleItemPosition = firstVisibleItem;
        }
        if (expandGroups.size() > 0) {
            printExpandGroups(expandGroups);
            SparseIntArray expandGroupClone = expandGroups.clone();
            int firstVis  = expandListView.getFirstVisiblePosition();
            int lastVis = expandListView.getLastVisiblePosition();
            int count = firstVis;

            while (count <= lastVis)
            {
                long longposition = expandListView.getExpandableListPosition(count);
                int type = ExpandableListView.getPackedPositionType(longposition);
                int groupPosition = ExpandableListView.getPackedPositionGroup(longposition);
                int childPosition = ExpandableListView.getPackedPositionChild(longposition);
                if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    int childreCount = expandListView.getExpandableListAdapter().getChildrenCount(groupPosition);
                    Log.d("elistview_visible","TYPE_CHILD group: " + groupPosition + " and child: " + childPosition + ",childreCount=" + childreCount);
                    if (mScrollAction == SCROLL_DOWN && childPosition == childreCount - 1) {
                        expandGroupClone.delete(groupPosition);
                    }
                } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                    Log.d("elistview_visible","expandGroupClone.get: " + expandGroupClone.get(groupPosition, -1));
                    if (mScrollAction == SCROLL_UP && expandGroupClone.get(groupPosition, -1) > 0) {
                        expandGroupClone.delete(groupPosition);
                    }
                    Log.d("elistview_visible","TYPE_GROU group: " + groupPosition + " and child: " + childPosition );
                }
                count++;
            }
            Log.d("elistview_visible","cloneSize: " + expandGroupClone.size());
            if (expandGroupClone.size() > 0) {
                for (int i = 0; i < expandGroupClone.size(); i++) {
                    int groupPosition = expandGroupClone.valueAt(i);
                    Log.d("elistview_visible","expandGroupClone groupPosition: " + groupPosition + ",isGroupExpanded:" + expandListView.isGroupExpanded(groupPosition));
                    if (expandListView.isGroupExpanded(groupPosition)) {
                        expandListView.collapseGroup(groupPosition);
                    }
                }
            }
        }
    }

    // 打印可视区域内的item
    public void listVisibleGroup(ExpandableListView expandListView)
    {
        int firstVis  = expandListView.getFirstVisiblePosition();
        int lastVis = expandListView.getLastVisiblePosition();

        int count = firstVis;

        while (count <= lastVis)
        {
            long longposition = expandListView.getExpandableListPosition(count);
            int type = ExpandableListView.getPackedPositionType(longposition);
            int groupPosition = ExpandableListView.getPackedPositionGroup(longposition);
            int childPosition = ExpandableListView.getPackedPositionChild(longposition);
            if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                Log.d("elistview_visible","TYPE_CHILD group: " + groupPosition + " and child: " + childPosition );
            } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                Log.d("elistview_visible","TYPE_GROU group: " + groupPosition + " and child: " + childPosition );
            }
            count++;

        }
    }

    private void test1() {
        /**BaseExpandableListAdapter实现了ExpandableListAdapter*/
        ExpandableListAdapter adapter = new BaseExpandableListAdapter(){

            /**----------定义数组-------------------------------------------------------------------*/
            private int[] images = new int[]{
                    R.drawable.mine_offlinearrow_download,
                    R.drawable.mine_offlinearrow_stop,
                    R.drawable.mine_offlinearrow_start
            };
            private String[] armTypes = new String[]{
                    "神族","虫族","人族"
            };
            private String[][] arms = new String[][]{
                    {"狂战士","龙骑士","黑暗圣堂"},
                    {"小狗","飞龙","自爆妃子"},
                    {"步兵","伞兵","护士mm"}
            };

/*===========组元素表示可折叠的列表项，子元素表示列表项展开后看到的多个子元素项=============*/

            /**----------得到armTypes和arms中每一个元素的ID-------------------------------------------*/

            //获取组在给定的位置编号，即armTypes中元素的ID
            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            //获取在给定的组的儿童的ID，就是arms中元素的ID
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            /**----------根据上面得到的ID的值，来得到armTypes和arms中元素的个数 ------------------------*/

            //获取的群体数量，得到armTypes里元素的个数
            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            //取得指定组中的儿童人数，就是armTypes中每一个种族它军种的个数
            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            /**----------利用上面getGroupId得到ID，从而根据ID得到armTypes中的数据，并填到TextView中 -----*/

            //获取与给定的组相关的数据，得到数组armTypes中元素的数据
            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }

            //获取一个视图显示给定组，存放armTypes
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                TextView textView = getTextView();//调用定义的getTextView()方法
                textView.setText(getGroup(groupPosition).toString());//添加数据
                return textView;
            }

            /**----------利用上面getChildId得到ID，从而根据ID得到arms中的数据，并填到TextView中---------*/

            //获取与孩子在给定的组相关的数据,得到数组arms中元素的数据
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            //获取一个视图显示在给定的组 的儿童的数据，就是存放arms
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                     View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableListViewActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);//定义为横向排列
                ImageView logo = new ImageView(ExpandableListViewActivity.this);
                logo.setImageResource(images[groupPosition]);//添加图片
                ll.addView(logo);
                TextView textView = getTextView();//调用定义的getTextView()方法
                textView.setText(getChild(groupPosition,childPosition).toString());//添加数据
                ll.addView(textView);
                return ll;
            }

            /**------------------自定义一个设定TextView属性的方法----------------------------------------------*/

            //定义一个TextView
            private TextView getTextView(){
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(ExpandableListViewActivity.this);
                textView.setLayoutParams(lp);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }

            /**-------------------其他设置-------------------------------------------------------------------*/

            //孩子在指定的位置是可选的，即：arms中的元素是可点击的
            @Override
            public boolean isChildSelectable(int groupPosition,
                                             int childPosition) {
                return true;
            }

            //表示孩子是否和组ID是跨基础数据的更改稳定
            public boolean hasStableIds() {
                return true;
            }
        };

        /**使用适配器*/
        ExpandableListView expandListView = (ExpandableListView) this.findViewById(R.id.ecpandable);
        expandListView.setAdapter(adapter);
    }
}
