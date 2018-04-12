package com.bangfu.widget.list.head;

import android.view.View;
import android.widget.BaseExpandableListAdapter;

public abstract class FloatingHeaderAdapter extends BaseExpandableListAdapter
{
    /**
     * 在第一次调用时head为空，需要创建一个head。此后只需要重复利用这个head即可。
     *
     * @param firstVisibleGroup 第一个可见的组
     * @return
     */
    public abstract View updateHeader(View head, int firstVisibleGroup);

}
