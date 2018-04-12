package com.bangfu.widget.list.head;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

/**
 * ExpandableListView的拓展，列表的上方漂着一个head
 */
public class FloatingHeaderListView extends ExpandableListView implements AbsListView.OnScrollListener {
    private static String TAG = "FloatingHeaderListView";
    /**
     *
     */
    private View mFloatingHead;
    /**
     * 漂浮的组
     */
    int mFloatGroup;
    private int              mHeadHeight;
    private int              mHeadWidth;
    private OnScrollListener mSupportScrollListener;
    FloatingHeaderAdapter mAdapter;

    public FloatingHeaderListView(Context context) {
        super(context);
        init(context);
    }

    public FloatingHeaderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatingHeaderListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        //调用父类的方法，本类的已被重写
        super.setOnScrollListener(this);
    }

    public int getFloatGroup() {
        return mFloatGroup;
    }

    public View getFloatHead() {
        return mFloatingHead;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        throw new RuntimeException(
                "For ExpandableListView, use setAdapter(FloatingHeaderAdapter) instead of " +
                        "setAdapter(ListAdapter)");
    }

    @Override
    public void setAdapter(ExpandableListAdapter adapter) {

        if (adapter instanceof FloatingHeaderAdapter) {
            setAdapter((FloatingHeaderAdapter) adapter);
        } else {
            throw new RuntimeException(
                    "For ExpandableListView, use setAdapter(FloatingHeaderAdapter) instead of " +
                            "setAdapter(ExpandableListAdapter)");
        }

    }

    public void setAdapter(FloatingHeaderAdapter adapter) {
        mAdapter = adapter;
        mFloatingHead = mAdapter.updateHeader(null, 0);

        super.setAdapter(adapter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mFloatingHead != null) {
            ViewGroup.LayoutParams lp = mFloatingHead.getLayoutParams();
            if (lp == null) {
                mFloatingHead.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            measureChild(mFloatingHead, widthMeasureSpec, heightMeasureSpec);
            mHeadWidth = mFloatingHead.getMeasuredWidth();
            mHeadHeight = mFloatingHead.getMeasuredHeight();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (mFloatingHead != null) {
            drawChild(canvas, mFloatingHead, getDrawingTime());
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mFloatingHead != null) {
            int delta = mFloatingHead.getTop();
            mFloatingHead.layout(0, delta, mFloatingHead.getWidth(), mFloatingHead.getHeight() + delta);

        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mSupportScrollListener != null) {
            mSupportScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        refreshHead();
        if (mSupportScrollListener != null) {
            mSupportScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void refreshHead() {
        if (mFloatingHead == null) {
            return;
        }
        int firstVisible = getFirstVisiblePosition();
        int secondVisible = firstVisible + 1;

        //第一个可见item所属的group
        int group1 = getPackedPositionGroup(getExpandableListPosition(firstVisible));
        //第二个可见item所属的group
        int group2 = getPackedPositionGroup(getExpandableListPosition(firstVisible + 1));
        mFloatGroup = group1;

        if (group1 == group2) {
            mFloatingHead.layout(0, 0, mHeadWidth, mHeadHeight);
        } else {
            //第一个可见的和第二个可见的不在同一个组，出现了临界点
            View child = getChildAt(1);
            int childTop = child.getTop();

            if (childTop <= mHeadHeight) {

                mFloatingHead.layout(0, childTop - mHeadHeight, mHeadWidth, childTop);
            } else {
                mFloatingHead.layout(0, 0, mHeadWidth, mHeadHeight);

            }

        }
        mAdapter.updateHeader(mFloatingHead, mFloatGroup);

    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mSupportScrollListener = l;
    }

    boolean intercept;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //处理漂浮head点击事件
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float y = ev.getY();
                if (mFloatingHead.getBottom() > y) {
                    intercept = true;
                    return true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (intercept) {
                    mFloatingHead.dispatchTouchEvent(ev);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (intercept) {
                    mFloatingHead.performClick();
                    return true;
                }

                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
