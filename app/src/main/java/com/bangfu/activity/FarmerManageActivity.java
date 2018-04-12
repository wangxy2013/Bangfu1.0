package com.bangfu.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.utils.ConfigManager;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/11/16 16:42
 * 邮箱：wangxianyun1@163.com
 * 描述：农户->扶贫管理
 */
public class FarmerManageActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_submit)
    TextView       tvSubmit;
    @BindView(R.id.rl_information)
    RelativeLayout rlInformation;
    @BindView(R.id.rl_policy)
    RelativeLayout rlPolicy;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;
    @BindView(R.id.rl_education)
    RelativeLayout rlEducation;
    @BindView(R.id.rl_disease)
    RelativeLayout rlDisease;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_farmer_manage);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        rlPolicy.setOnClickListener(this);
        rlDisease.setOnClickListener(this);
        rlEducation.setOnClickListener(this);
        rlHelp.setOnClickListener(this);
        rlInformation.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("扶贫管理");
//        tvSubmit.setText("●●●");
//        tvSubmit.setTextSize(8);
//        tvSubmit.setVisibility(View.VISIBLE);

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

            case R.id.rl_information://基本信息

                startActivity(new Intent(FarmerManageActivity.this, FarmerInformationActivity.class).putExtra("PID", ConfigManager.instance().getUserID()));

                break;
            case R.id.rl_policy://扶贫政策
                startActivity(new Intent(FarmerManageActivity.this, ProgramPolicyActivity.class).putExtra("TYPE","5"));
                break;

            case R.id.rl_help://咨询或求助
                startActivity(new Intent(FarmerManageActivity.this, MyQuestionActivity.class));
                break;

            case R.id.rl_education://教育

                startActivity(new Intent(FarmerManageActivity.this, EduListActivity.class));
                break;
            case R.id.rl_disease://大病
                startActivity(new Intent(FarmerManageActivity.this, HtyListActivity.class));
                break;
        }
    }
}
