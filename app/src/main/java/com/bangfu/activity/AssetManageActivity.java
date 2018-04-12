package com.bangfu.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/12/7 13:09
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class AssetManageActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.iv_arrow1)
    ImageView      ivArrow1;
    @BindView(R.id.rl_item1)
    RelativeLayout rlItem1;
    @BindView(R.id.ll_zjgl)
    LinearLayout   llZjgl;
    @BindView(R.id.iv_arrow2)
    ImageView      ivArrow2;
    @BindView(R.id.rl_item2)
    RelativeLayout rlItem2;
    @BindView(R.id.ll_zjg2)
    LinearLayout   llZjg2;
    @BindView(R.id.iv_arrow3)
    ImageView      ivArrow3;
    @BindView(R.id.rl_item3)
    RelativeLayout rlItem3;
    @BindView(R.id.ll_zjg3)
    LinearLayout   llZjg3;
    @BindView(R.id.iv_arrow4)
    ImageView      ivArrow4;
    @BindView(R.id.rl_item4)
    RelativeLayout rlItem4;
    @BindView(R.id.ll_zjg4)
    LinearLayout   llZjg4;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_zcgl);
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
        tvTitle.setText("资产管理");
        rlItem1.setOnClickListener(this);
        rlItem2.setOnClickListener(this);
        rlItem3.setOnClickListener(this);
        rlItem4.setOnClickListener(this);
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

            case R.id.rl_item1:

                if(llZjgl.isShown())
                {
                    llZjgl.setVisibility(View.GONE);
                    ivArrow1.setImageResource(R.drawable.ic_down);
                }
                else
                {
                    llZjgl.setVisibility(View.VISIBLE);
                    ivArrow1.setImageResource(R.drawable.ic_up);
                }
                break;

            case R.id.rl_item2:

                if(llZjg2.isShown())
                {
                    llZjg2.setVisibility(View.GONE);
                    ivArrow2.setImageResource(R.drawable.ic_down);
                }
                else
                {
                    llZjg2.setVisibility(View.VISIBLE);
                    ivArrow2.setImageResource(R.drawable.ic_up);
                }
                break;

            case R.id.rl_item3:

                if(llZjg3.isShown())
                {
                    llZjg3.setVisibility(View.GONE);
                    ivArrow3.setImageResource(R.drawable.ic_down);
                }
                else
                {
                    llZjg3.setVisibility(View.VISIBLE);
                    ivArrow3.setImageResource(R.drawable.ic_up);
                }
                break;

            case R.id.rl_item4:

                if(llZjg4.isShown())
                {
                    llZjg4.setVisibility(View.GONE);
                    ivArrow4.setImageResource(R.drawable.ic_down);
                }
                else
                {
                    llZjg4.setVisibility(View.VISIBLE);
                    ivArrow4.setImageResource(R.drawable.ic_up);
                }
                break;
        }
    }
}
