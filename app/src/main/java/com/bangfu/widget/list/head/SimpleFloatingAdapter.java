package com.bangfu.widget.list.head;

import android.view.View;

import java.util.List;

public abstract class SimpleFloatingAdapter extends FloatingHeaderAdapter {
    private List<? extends List> data;

    protected SimpleFloatingAdapter(List<? extends List> data) {
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).get(childPosition);
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
        return false;
    }

    @Override
    public View updateHeader(View head, int firstVisibleGroup) {
        return getGroupView(firstVisibleGroup,true,head,null);
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
