package com.bangfu.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.EduAdapter;
import com.bangfu.entity.EduAssistInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.EduListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.StringUtils;
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
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/12/2 01:50
 * 邮箱：wangxianyun1@163.com
 * 描述：教育救助信息
 */
public class SearchEduActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{
    @BindView(R.id.rl_back)
    RelativeLayout            rl_back;
    @BindView(R.id.tv_title)
    TextView                  tvTitle;
    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    @BindView(R.id.tv_submit)
    TextView                  tvSubmit;
    @BindView(R.id.et_key)
    EditText                  etKey;
    @BindView(R.id.btn_search)
    Button                    btnSearch;


    private RecyclerView    mRecyclerView;
    private EmptyDecoration emptyDecoration;
    private List<EduAssistInfo> mEduAssistInfoList = new ArrayList<>();
    private EduAdapter mEduAdapter;
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

                    EduListHandler mNewsListHandler = (EduListHandler) msg.obj;
                    mEduAssistInfoList.addAll(mNewsListHandler.getEduAssistInfoList());
                    mEduAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(SearchEduActivity.this, msg.obj.toString());
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
        setContentView(R.layout.activity_search_edu);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rl_back.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
        tvSubmit.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {

        tvTitle.setText("教育救助列表");
//        tvSubmit.setText("添加救助");
//        tvSubmit.setTextSize(14);

        if ("0".equals(ConfigManager.instance().getHolder()))
        {
            tvSubmit.setVisibility(View.VISIBLE);
        }
        else
        {
            tvSubmit.setVisibility(View.GONE);
        }


//        tvSubmit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        tvSubmit.getPaint().setAntiAlias(true);//抗锯齿
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
        emptyDecoration = new EmptyDecoration(SearchEduActivity.this, "");
        mRecyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(SearchEduActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mEduAdapter = new EduAdapter(mEduAssistInfoList, SearchEduActivity.this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                Intent intent = new Intent(SearchEduActivity.this, InputEducationActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("EduAssistInfo", mEduAssistInfoList.get(position));
                intent.putExtras(b);
                intent.putExtra("TYPE", 2);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mEduAdapter);

    }


    @Override
    protected void onResume()
    {
        super.onResume();
//        mEduAssistInfoList.clear();
//        page = 1;
//        mRefreshStatus = 0;
//        getHtyList();
    }

    private void getEduList()
    {


        if (StringUtils.stringIsEmpty(etKey.getText().toString()))
        {
            return;
        }
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", page + "");
        valuePairs.put("rows", "10");
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        valuePairs.put("searchhname", etKey.getText().toString());

        DataRequest.instance().request(SearchEduActivity.this, Urls.getSearchEduUrl(), this, HttpRequest.POST, GET_LIST, valuePairs,
                new EduListHandler());
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

            case R.id.tv_submit:
                startActivity(new Intent(SearchEduActivity.this, InputEducationActivity.class).putExtra("TYPE", "1"));
                break;

            case R.id.btn_search:
                if (StringUtils.stringIsEmpty(etKey.getText().toString()))
                {
                    ToastUtil.show(SearchEduActivity.this, "请输入搜索关键字");
                    return;
                }
                getEduList();
                break;
        }
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mEduAssistInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getEduList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        page += 1;
        mRefreshStatus = 1;
        getEduList();
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
