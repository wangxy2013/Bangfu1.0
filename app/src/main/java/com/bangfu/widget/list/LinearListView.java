package com.bangfu.widget.list;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.bangfu.R;


/**
 * 基于LinearLayout的列表，主要为了解决和ScrollView的冲突事件,当数据比较少时可以用此布局。当数据比较大时建议用ListView
 */
public class LinearListView extends LinearLayout implements View.OnClickListener {

    private ListAdapter mAdapter;
    private OnItemClick onItemClick;

    private DataSetObserver mObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            refreshLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    public LinearListView(Context context) {
        super(context);
        init();
    }

    public LinearListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init() {
    }

    public void setAdapter(ListAdapter adapter) {

        unregisterDataSetObserverSafe();

        this.mAdapter = adapter;

        registerDataSetObserverSafe();
        refreshLayout();

    }

    void registerDataSetObserverSafe(){
        if (mAdapter != null) {
            try {
                mAdapter.registerDataSetObserver(mObserver);
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerDataSetObserverSafe();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private void refreshLayout() {

        if (mAdapter == null) {
            return;
        }

        int count = mAdapter.getCount();
        int childCount = getChildCount();

        if (count < childCount) {
            //移除多余的child
            while (childCount > count) {
                removeViewAt(0);
                childCount--;
            }
        }

        for (int i = 0; i < count; i++) {
            View oldChild = getChildAt(i);
            /**
             * oldChild:null，不为null
             * getView：返回null,返回新View,返回老View
             */
            View newChild = mAdapter.getView(i, oldChild, this);

            /**
             * 如果oldChild为空，就新增一个View。
             * 如果oldChild不为空，检查Adapter返回的是否为oldChild，如果不等于，就替换掉oldChild
             */
            if (oldChild == null) {
                newChild.setTag(R.id.listChildTag, "" + i);
                addView(newChild);
                newChild.setOnClickListener(this);
            } else if (newChild != oldChild) {
                removeView(oldChild);
                newChild.setTag(R.id.listChildTag, "" + i);
                addView(newChild);
                newChild.setOnClickListener(this);
            }


        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterDataSetObserverSafe();
    }

    void unregisterDataSetObserverSafe() {
        if (mAdapter != null) {
            try {
                mAdapter.unregisterDataSetObserver(mObserver);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (onItemClick != null) {
            int position = Integer.parseInt((String) v.getTag(R.id.listChildTag));
            onItemClick.onItemClick(this, v, position, mAdapter.getItemId(position));
        }
    }

    public interface OnItemClick {
        public void onItemClick(LinearListView parent, View view, int position, long id);
    }
}
