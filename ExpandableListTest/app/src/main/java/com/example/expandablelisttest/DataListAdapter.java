package com.example.expandablelisttest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by qiaoda.zqd on 2016/10/31.
 */

public class DataListAdapter extends BaseExpandableListAdapter {
    private List<GroupItem> mListData;
    private Context mContext;

    public DataListAdapter(Context context, List<GroupItem> listData) {
        mContext = context;
        mListData = listData;
    }

    @Override
    public int getGroupCount() {
        if (mListData != null) {
            return mListData.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mListData != null && mListData.size() > groupPosition && mListData.get(groupPosition) != null &&
                mListData.get(groupPosition).subNames != null) {
            return mListData.get(groupPosition).subNames.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (mListData != null && mListData.size() > groupPosition) {
            return mListData.get(groupPosition);
        } else {
            return null;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mListData != null && mListData.size() > groupPosition && mListData.get(groupPosition) != null &&
                mListData.get(groupPosition).subNames != null) {
            return mListData.get(groupPosition).subNames.get(childPosition);
        } else {
            return null;
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupItem groupItem = mListData.get(groupPosition);
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.group_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_group_item_text);
        textView.setText(groupItem.groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GroupItem groupItem = mListData.get(groupPosition);
        String subname = groupItem.subNames.get(childPosition);
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.child_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_child_item_text);
        textView.setText(subname);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
