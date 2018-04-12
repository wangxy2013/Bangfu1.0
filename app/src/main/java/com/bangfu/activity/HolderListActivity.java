package com.bangfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.HuzhuAdapter;
import com.bangfu.entity.UserInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.HolderListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.Urls;
import com.bangfu.widget.DividerDecoration;
import com.bangfu.widget.EmptyDecoration;
import com.bangfu.widget.list.refresh.PullToRefreshBase;
import com.bangfu.widget.list.refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/12/1 23:13
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class HolderListActivity  extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{
    @BindView(R.id.rl_back)
    RelativeLayout            rl_back;
    @BindView(R.id.tv_title)
    TextView                  tvTitle;
    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    @BindView(R.id.tv_submit)
    TextView                  tvSubmit;


    private RecyclerView      mRecyclerView;
    private DividerDecoration mDividerDecoration;
    private ArrayList<UserInfo> mUserInfoList = new ArrayList<>();
    private HuzhuAdapter mHuzhuAdapter;
    private int        mRefreshStatus;

    private int page = 1;

    private static final int    REQUEST_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL    = 0x02;
    private static final String GET_LIST        = "GET_LIST";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_SUCCESS:

                    HolderListHandler mHolderListHandler = (HolderListHandler) msg.obj;
                    mUserInfoList.addAll(mHolderListHandler.getUserInfoList());
                    mHuzhuAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(HolderListActivity.this, msg.obj.toString());
                    break;


            }
        }
    };

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_huzhu_list);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rl_back.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {

        tvTitle.setText("戶主列表");
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
        mDividerDecoration = new DividerDecoration(HolderListActivity.this);
        mRecyclerView.addItemDecoration(mDividerDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(HolderListActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mHuzhuAdapter = new HuzhuAdapter(mUserInfoList,  new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                startActivityForResult(new Intent(HolderListActivity.this,MemberActivity.class).putExtra("holder",mUserInfoList.get(position).getPid()),1);
            }
        });
        mRecyclerView.setAdapter(mHuzhuAdapter);
        getHtyList();
    }


    private void getHtyList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", page + "");
        valuePairs.put("rows", "10");
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        DataRequest.instance().request(HolderListActivity.this, Urls.getHolderInfoListUrl(), this, HttpRequest.POST, GET_LIST, valuePairs,
                new HolderListHandler());
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
        mUserInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getHtyList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        page += 1;
        mRefreshStatus = 1;
        getHtyList();
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (mRefreshStatus == 1)
        {
            mPullToRefreshRecyclerView.onPullUpRefreshComplete();
        }
        else
        {
            mPullToRefreshRecyclerView.onPullDownRefreshComplete();
        }
        if (GET_LIST.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            setResult( Activity.RESULT_OK,data);
            finish();
        }
    }
}
