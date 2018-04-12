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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.FarmerInfomationAdapter;
import com.bangfu.entity.UserInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.HolderListHandler;
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

import static com.bangfu.R.id.tv_submit;

/**
 * 作者：王先云 on 2017/11/16 16:56
 * 邮箱：wangxianyun1@163.com
 * 描述：农户->基本信息
 */
public class SearchHolderActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(tv_submit)
    TextView       tvSubmit;

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_key)
    EditText  etKey;


    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    @BindView(R.id.btn_search)
    Button                    btnSearch;


    private RecyclerView mRecyclerView;

    private int mRefreshStatus;

    private EmptyDecoration emptyDecoration;

    private List<UserInfo> mUserInfoList = new ArrayList<>();

    private FarmerInfomationAdapter mFarmerInfomationAdapter;


    private int page = 1;

    private static final int    REQUEST_LOGIN_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL          = 0x02;
    private static final String GET_USERINFO_LIST     = "GET_USERINFO_LIST";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_LOGIN_SUCCESS:

                    HolderListHandler mHolderListHandler = (HolderListHandler) msg.obj;
                    mUserInfoList.addAll(mHolderListHandler.getUserInfoList());
                    mFarmerInfomationAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(SearchHolderActivity.this, msg.obj.toString());
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
        setContentView(R.layout.activity_searche_holder);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rl_back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("基本信息");
//        tvSubmit.setText("添加戶主");
//        tvSubmit.setTextSize(14);
//        tvSubmit.setVisibility(View.GONE);
//        tvSubmit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        tvSubmit.getPaint().setAntiAlias(true);//抗锯齿



        //        mPullToRefreshRecyclerView.setPullLoadEnabled(true);
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
        emptyDecoration = new EmptyDecoration(SearchHolderActivity.this, "");
        mRecyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(SearchHolderActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mFarmerInfomationAdapter = new FarmerInfomationAdapter(mUserInfoList, SearchHolderActivity.this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                Intent intent = new Intent(SearchHolderActivity.this, FarmerInformationActivity.class);
                intent.putExtra("PID", mUserInfoList.get(position).getPid());
                startActivity(intent);
            }
        }, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                //修改操作
                Intent intent = new Intent(SearchHolderActivity.this, AddHolderActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("UserInfo", mUserInfoList.get(position));
                intent.putExtras(b);
                intent.putExtra("relationType", "1");
                intent.putExtra("TYPE", "2");
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mFarmerInfomationAdapter);


    }


    private void getUserInfoList()
    {

        if (StringUtils.stringIsEmpty(etKey.getText().toString()))
        {
            return;
        }
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", page + "");
        valuePairs.put("rows", "10");
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        valuePairs.put("searchPara", etKey.getText().toString());
        DataRequest.instance().request(SearchHolderActivity.this, Urls.getSearchHolderInfoUrl(), this, HttpRequest.POST, GET_USERINFO_LIST, valuePairs,
                new HolderListHandler());


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mUserInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getUserInfoList();
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
                startActivity(new Intent(SearchHolderActivity.this, AddHolderActivity.class).putExtra("relationType", "1").putExtra("TYPE", "1"));
                break;

            case R.id.btn_search:
                if (StringUtils.stringIsEmpty(etKey.getText().toString()))
                {
                    ToastUtil.show(SearchHolderActivity.this, "请输入户主姓名或手机");
                    return;
                }
                getUserInfoList();
                break;


        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mUserInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getUserInfoList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        page += 1;
        mRefreshStatus = 1;
        getUserInfoList();
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
        if (GET_USERINFO_LIST.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_LOGIN_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
