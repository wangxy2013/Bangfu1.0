package com.bangfu.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.InformationPublicityAdapter;
import com.bangfu.entity.InformationInfo;
import com.bangfu.http.IRequestListener;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.widget.EmptyDecoration;
import com.bangfu.widget.list.refresh.PullToRefreshBase;
import com.bangfu.widget.list.refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 作者：王先云 on 2017/11/16 16:56
 * 邮箱：wangxianyun1@163.com
 * 描述：信息宣传
 */
public class InformationPublicityActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout            rl_back;
    @BindView(R.id.tv_title)
    TextView                  tvTitle;
    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;

    private RecyclerView mRecyclerView;

    private EmptyDecoration emptyDecoration;

    private List<InformationInfo> mInformationInfo = new ArrayList<>();

    private InformationPublicityAdapter mInformationPublicityAdapter;
    private int                         mRefreshStatus;

    @Override
    protected void initData()
    {

        InformationInfo mPolicyInfo = new InformationInfo();
        mPolicyInfo.setTitle("本周继续开展了旨在终止不安全注射的大型公众信息宣传活动。");
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);
        mInformationInfo.add(mPolicyInfo);

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_information_publicity);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rl_back.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("扶贫政策");

        mPullToRefreshRecyclerView.setPullLoadEnabled(true);
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                outRect.set(5, 0, 5, 0);
            }
        });
        // mRecyclerView.addItemDecoration(new AnswerChatHolder.QADecoration(getActivity()));
        emptyDecoration = new EmptyDecoration(InformationPublicityActivity.this, "");
        mRecyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(InformationPublicityActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mInformationPublicityAdapter = new InformationPublicityAdapter(mInformationInfo, InformationPublicityActivity.this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

            }
        });
        mRecyclerView.setAdapter(mInformationPublicityAdapter);

    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.rl_back:
                finish();
                break;

        }
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mRefreshStatus = 0;
        if (mRefreshStatus == 1)
        {
            mPullToRefreshRecyclerView.onPullUpRefreshComplete();
        }
        else
        {
            mPullToRefreshRecyclerView.onPullDownRefreshComplete();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mRefreshStatus = 1;
        if (mRefreshStatus == 1)
        {
            mPullToRefreshRecyclerView.onPullUpRefreshComplete();
        }
        else
        {
            mPullToRefreshRecyclerView.onPullDownRefreshComplete();
        }
    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {

    }


}
