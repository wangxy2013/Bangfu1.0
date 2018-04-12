package com.bangfu.activity;

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
import com.bangfu.adapter.ProgramPolicyAdapter;
import com.bangfu.entity.NewsInfo;
import com.bangfu.entity.PolicyInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.HolderListHandler;
import com.bangfu.json.NewsListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.Urls;
import com.bangfu.widget.EmptyDecoration;
import com.bangfu.widget.list.refresh.PullToRefreshBase;
import com.bangfu.widget.list.refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * 作者：王先云 on 2017/11/16 16:56
 * 邮箱：wangxianyun1@163.com
 * 描述：扶贫政策
 */
public class ProgramPolicyActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout            rl_back;
    @BindView(R.id.tv_title)
    TextView                  tvTitle;
    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;

    private RecyclerView    mRecyclerView;
    private EmptyDecoration emptyDecoration;
    private List<NewsInfo> mNewsList = new ArrayList<>();
    private ProgramPolicyAdapter mProgramPolicyAdapter;
    private int                  mRefreshStatus;

    private String type;//1:农耕养殖技术 2.就业创业信息 3.惠农政策宣传 4.新闻时事报道 5.扶贫政策 6.信息宣傳
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

                    NewsListHandler mNewsListHandler = (NewsListHandler) msg.obj;
                    mNewsList.addAll(mNewsListHandler.getNewsInfoList());
                    mProgramPolicyAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(ProgramPolicyActivity.this, msg.obj.toString());
                    break;


            }
        }
    };

    @Override
    protected void initData()
    {

        type = getIntent().getStringExtra("TYPE");


    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_program_policy);
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
        //1:农耕养殖技术 2.就业创业信息 3.惠农政策宣传 4.新闻时事报道 5.扶贫政策
        if ("1".equals(type))
        {
            tvTitle.setText("农耕养殖技术");
        }
        else if ("2".equals(type))
        {
            tvTitle.setText("就业创业信息");
        }
        else if ("3".equals(type))
        {
            tvTitle.setText("惠农政策宣传");
        }
        else if ("4".equals(type))
        {
            tvTitle.setText("新闻时事报道");
        }
        else if ("5".equals(type))
        {
            tvTitle.setText("扶贫政策");
        }
        else if ("6".equals(type))
        {
            tvTitle.setText("信息宣传");
        }
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
        emptyDecoration = new EmptyDecoration(ProgramPolicyActivity.this, "");
        mRecyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ProgramPolicyActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mProgramPolicyAdapter = new ProgramPolicyAdapter(mNewsList, ProgramPolicyActivity.this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
               Intent intent = new Intent(ProgramPolicyActivity.this,NewsDetailActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("NewsInfo",mNewsList.get(position));
                intent.putExtras(b);
                intent.putExtra("TYPE",type);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mProgramPolicyAdapter);
        getNewsList();
    }


    private void getNewsList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", page + "");
        valuePairs.put("rows", "15");
        valuePairs.put("typeId", type);
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        DataRequest.instance().request(ProgramPolicyActivity.this, Urls.getPublishInfoListUrl(), this, HttpRequest.POST, GET_LIST, valuePairs,
                new NewsListHandler());
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
        mNewsList.clear();
        page = 1;
        mRefreshStatus = 0;
        getNewsList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        page += 1;
        mRefreshStatus = 1;
        getNewsList();
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
}
