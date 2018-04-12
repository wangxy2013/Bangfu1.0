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
import com.bangfu.fragment.AssistanceFragment2;
import com.bangfu.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/11/21 17:07
 * 邮箱：wangxianyun1@163.com
 * 描述：特别援助
 */
public class SpecialAssistanceActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_submit)
    TextView       tvSubmit;
    @BindView(R.id.iv_search)
    ImageView      ivSearch;
    @BindView(R.id.et_key)
    EditText       etKey;
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
        tvTitle.setText("特别援助");
        AssistanceFragment1 mAssistanceFragment1 = AssistanceFragment1.newInstance("1");
        mAssistanceFragment1.setTitle("待处理");
        AssistanceFragment1 mAssistanceFragment2 = AssistanceFragment1.newInstance("2");
        mAssistanceFragment2.setTitle("已处理");
        //
        fragments.add(mAssistanceFragment1);
        fragments.add(mAssistanceFragment2);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(1);
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
