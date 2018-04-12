package com.bangfu.widget.list.refresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.addOnScrollListener(new OnScrollListener());
        return recyclerView;
    }

    //下拉刷新
    @Override
    protected boolean isReadyForPullDown() {
        RecyclerView refreshableView = getRefreshableView();
        RecyclerView.LayoutManager manager = refreshableView.getLayoutManager();
        if (manager == null) {
            return false;
        }
        if (manager instanceof LinearLayoutManager) {
            int lastPosition = ((LinearLayoutManager) manager).findFirstCompletelyVisibleItemPosition();
            return lastPosition == 0;
        }

        return false;
    }

    //上拉加载
    @Override
    protected boolean isReadyForPullUp() {
        RecyclerView refreshableView = getRefreshableView();
        RecyclerView.LayoutManager manager = refreshableView.getLayoutManager();
        if (manager == null) {
            return false;
        }
        if (manager instanceof LinearLayoutManager) {
            int lastPosition = ((LinearLayoutManager) manager).findLastCompletelyVisibleItemPosition();
            return lastPosition == manager.getItemCount()-1;
        }
        return false;
    }

    private class OnScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }
}
