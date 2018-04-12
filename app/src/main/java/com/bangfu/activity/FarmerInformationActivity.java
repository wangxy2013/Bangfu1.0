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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.FarmerInfomationAdapter;
import com.bangfu.entity.FarmerDescInfo;
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
import com.bangfu.widget.EmptyDecoration;

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
public class FarmerInformationActivity extends BaseActivity implements IRequestListener
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView        tvTitle;
    @BindView(tv_submit)
    TextView        tvSubmit;
    @BindView(R.id.recyclerView)
    RecyclerView    recyclerView;
    private EmptyDecoration emptyDecoration;
    private List<UserInfo> mUserInfoList = new ArrayList<>();
    private FarmerInfomationAdapter mFarmerInfomationAdapter;


    private String pid;
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
                    mUserInfoList.clear();
                    HolderListHandler mHolderListHandler = (HolderListHandler) msg.obj;
                    mUserInfoList.addAll(mHolderListHandler.getUserInfoList());

                    for (int i = 0; i < mUserInfoList.size(); i++)
                    {
                        if("1".equals(mUserInfoList.get(i).getRelationType()))
                        {
                            mUserInfoList.get(i).setType(1);
                        }
                        else
                        {
                            mUserInfoList.get(i).setType(2);
                        }
                    }
                    mFarmerInfomationAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(FarmerInformationActivity.this, msg.obj.toString());
                    break;


            }
        }
    };
    @Override
    protected void initData()
    {
        pid = getIntent().getStringExtra("PID");

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_farmer_information);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("基本信息");
        tvSubmit.setText("添加成员");
        tvSubmit.setTextSize(14);

        if("0".equals(ConfigManager.instance().getHolder()))
        {
            tvSubmit.setVisibility(View.VISIBLE);
        }
        else
        {
                tvSubmit.setVisibility(View.GONE);
        }

        tvSubmit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvSubmit.getPaint().setAntiAlias(true);//抗锯齿

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                outRect.set(5, 0, 5, 0);
            }
        });
        // mRecyclerView.addItemDecoration(new AnswerChatHolder.QADecoration(getActivity()));
        emptyDecoration = new EmptyDecoration(FarmerInformationActivity.this, "");
        recyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(FarmerInformationActivity.this, LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mFarmerInfomationAdapter = new FarmerInfomationAdapter(mUserInfoList, FarmerInformationActivity.this, new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent(FarmerInformationActivity.this, AddHolderActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("UserInfo",mUserInfoList.get(position));
                intent.putExtras(b);
                intent.putExtra("TYPE","4");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mFarmerInfomationAdapter);





    }


    @Override
    protected void onResume()
    {
        super.onResume();
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", 1 + "");
        valuePairs.put("rows", "100");
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        valuePairs.put("holder", pid);
        DataRequest.instance().request(FarmerInformationActivity.this, Urls.getFamilyMemberInfoUrl(), this, HttpRequest.POST, GET_USERINFO_LIST, valuePairs,
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

            case tv_submit:
                startActivity(new Intent(FarmerInformationActivity.this, AddHolderActivity.class).putExtra("isHolder",pid).putExtra("TYPE","3"));
                break;
        }
    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
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
}
