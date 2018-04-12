package com.bangfu.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.ViewPagerAdapter;
import com.bangfu.fragment.AssistanceFragment1;
import com.bangfu.fragment.BaseFragment;
import com.bangfu.fragment.SjsbFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/12/7 11:56
 * 邮箱：wangxianyun1@163.com
 * 描述：三进三帮
 */
public class SjsbActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout      tabLayout;
    @BindView(R.id.ll_viewPage)
    LinearLayout   llViewPage;
    @BindView(R.id.viewPager)
    ViewPager      viewPager;
    private List<BaseFragment> fragments = new ArrayList<>();


    @Override
    protected void initData()
    {
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_special_assistance);
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
        tvTitle.setText("三进三帮");
        SjsbFragment SjsbFragmen1 =new SjsbFragment();
        SjsbFragmen1.setTitle("数据分析");
        SjsbFragment SjsbFragmen2 =new SjsbFragment();
        SjsbFragmen2.setTitle("问题提交");
        SjsbFragment SjsbFragmen3 =new SjsbFragment();
        SjsbFragmen3.setTitle("信息发送");
        //
        fragments.add(SjsbFragmen1);
        fragments.add(SjsbFragmen2);
        fragments.add(SjsbFragmen3);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
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
}