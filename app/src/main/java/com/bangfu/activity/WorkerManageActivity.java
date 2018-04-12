package com.bangfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/16 16:04
 * 邮箱：wangxianyun1@163.com
 * 描述：扶贫管理->工作人员
 */
public class WorkerManageActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_submit)
    TextView       tvSubmit;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_dsr)
    ImageView      ivDsr;
    @BindView(R.id.rl_dsrgl)
    RelativeLayout rlDsrgl;
    @BindView(R.id.iv_sos)
    ImageView      ivSos;
    @BindView(R.id.rl_sos)
    RelativeLayout rlSos;
    @BindView(R.id.iv_xxxc)
    ImageView      ivXxxc;
    @BindView(R.id.rl_xxxc)
    RelativeLayout rlXxxc;
    @BindView(R.id.rl_dbjz)
    RelativeLayout rlDbjz;
    @BindView(R.id.rl_jyjz)
    RelativeLayout rlJyjz;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_worker_manage);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));

    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        rlDsrgl.setOnClickListener(this);
        rlSos.setOnClickListener(this);
        rlXxxc.setOnClickListener(this);
        rlDbjz.setOnClickListener(this);
        rlJyjz.setOnClickListener(this);
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

            case R.id.rl_dsrgl:
                //startActivity(new Intent(WorkerManageActivity.this, FamilyListActivity.class));
                startActivity(new Intent(WorkerManageActivity.this, TownActivity.class));
                break;

            case R.id.rl_sos:
                startActivity(new Intent(WorkerManageActivity.this, SpecialAssistanceActivity.class));
                break;
            case R.id.rl_xxxc:
                startActivity(new Intent(WorkerManageActivity.this, ProgramPolicyActivity.class).putExtra("TYPE","6"));
                break;

            case R.id.rl_dbjz:
                startActivity(new Intent(WorkerManageActivity.this, HtyListActivity.class));
                break;

            case R.id.rl_jyjz:
                startActivity(new Intent(WorkerManageActivity.this, EduListActivity.class));
                break;
        }
    }

}
