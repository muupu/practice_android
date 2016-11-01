package com.example.expandablelisttest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考资料：
 * http://www.blogjava.net/jjshcc/archive/2012/02/29/371030.html
 */
public class ExpandableListViewActivity extends Activity {

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
        for (int i = 0; i < 10; i++) {
            GroupItem groupItem = new GroupItem();
            groupItem.groupName = "AAA" + i;
            groupItem.subNames = new ArrayList<>();
            for (int j = 0; j < 5 ; j++) {
                groupItem.subNames.add("BBB" + j);
            }
            listData.add(groupItem);
        }

        DataListAdapter listAdapter = new DataListAdapter(ExpandableListViewActivity.this, listData);
        final ExpandableListView expandListView = (ExpandableListView) this.findViewById(R.id.ecpandable);
        expandListView.setAdapter(listAdapter);
        // 点击group item展开的时候，其他已展开的group收起
        expandListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastPosition = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if((lastPosition != -1) && (groupPosition != lastPosition) )
                    expandListView.collapseGroup(lastPosition );
                lastPosition = groupPosition;
            }
        });
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
