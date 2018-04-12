package com.bangfu.activity;

import android.content.Intent;
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
import com.bangfu.adapter.VillageAdapter;
import com.bangfu.entity.TownInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.TownListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.ToastUtil;
import com.bangfu.widget.DividerDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：王先云 on 2018/4/9 14:16
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class UserInfoListActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<UserInfo> userInfoListAll = new ArrayList<>();
    private VillageAdapter mVillageAdapter;

    @Override
    protected void initData()
    {
        TownInfo mTownInfo = (TownInfo) getIntent().getSerializableExtra("TownInfo");

        if (null != mTownInfo)
        {
            userInfoListAll.addAll(mTownInfo.getUserInfoList());
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_user_list);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {

        tvTitle.setText("农户列表");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));

        mVillageAdapter = new VillageAdapter(userInfoListAll, this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int p)
            {

                UserInfo mUserInfo = userInfoListAll.get(p);

                if (null != mUserInfo && !StringUtils.stringIsEmpty(mUserInfo.getPid()))
                {
                    Intent intent = new Intent(UserInfoListActivity.this, FarmerInformationActivity.class);
                    intent.putExtra("PID", mUserInfo.getPid());
                    startActivity(intent);

                }

            }
        });
        mRecyclerView.setAdapter(mVillageAdapter);
    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        if (v == rlBack)
        {
            finish();
        }
    }

}
