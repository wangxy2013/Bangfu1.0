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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.MyQuestionAdapter;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.QuestionListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.Urls;
import com.bangfu.widget.EmptyDecoration;
import com.bangfu.widget.list.refresh.PullToRefreshBase;
import com.bangfu.widget.list.refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.bangfu.R.id.tv_submit;

/**
 * 作者：王先云 on 2017/11/16 16:56
 * 邮箱：wangxianyun1@163.com
 * 描述：我的咨询提问
 */
public class MyQuestionActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(tv_submit)
    TextView       tvSubmit;
    @BindView(R.id.iv_search)
    ImageView      ivSearch;
    @BindView(R.id.et_key)
    EditText       etKey;

    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;

    private RecyclerView mRecyclerView;

    private int mRefreshStatus;

    private EmptyDecoration emptyDecoration;

    private List<QuestionInfo> mQuestionInfoList = new ArrayList<>();

    private MyQuestionAdapter mMyQuestionAdapter;
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

                    QuestionListHandler mQuestionListHandler = (QuestionListHandler) msg.obj;
                    mQuestionInfoList.addAll(mQuestionListHandler.getQuestionInfoList());
                    mMyQuestionAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
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
        setContentView(R.layout.activity_my_question);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rl_back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("我的咨询和求助");
        tvSubmit.setText("添加");
        tvSubmit.setTextSize(14);
        tvSubmit.setVisibility(View.VISIBLE);
        tvSubmit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvSubmit.getPaint().setAntiAlias(true);//抗锯齿
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
        emptyDecoration = new EmptyDecoration(MyQuestionActivity.this, "");
        mRecyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MyQuestionActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMyQuestionAdapter = new MyQuestionAdapter(mQuestionInfoList, MyQuestionActivity.this, new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent(MyQuestionActivity.this,EvaluationActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("QuestionInfo",mQuestionInfoList.get(position));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mMyQuestionAdapter);

    }
    @Override
    public void onResume()
    {
        super.onResume();
        mQuestionInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getQuestionList();
    }


    private void getQuestionList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", page + "");
        valuePairs.put("rows", "10");
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        valuePairs.put("isAns", "2");
        DataRequest.instance().request(MyQuestionActivity.this, Urls.getConsultInfoByPidUrl(), this, HttpRequest.POST, GET_LIST, valuePairs,
                new QuestionListHandler());
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

            case tv_submit:
                startActivity(new Intent(MyQuestionActivity.this, CreateQuestionActivity.class));

                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mQuestionInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getQuestionList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        page += 1;
        mRefreshStatus = 1;
        getQuestionList();
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
