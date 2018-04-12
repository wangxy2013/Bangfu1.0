package com.bangfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.TypeAdapter;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.widget.DividerDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/12/1 23:13
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class TypeActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView   recyclerView;

    private ArrayList<String> mList;

    private TypeAdapter mTypeAdapter;
    private String      title;

    @Override
    protected void initData()
    {
        mList = getIntent().getStringArrayListExtra("LIST");
        title = getIntent().getStringExtra("TITLE");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_type);
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
        tvTitle.setText(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerDecoration(this));



        mTypeAdapter = new TypeAdapter(mList, new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent();
                intent.putExtra("position",position);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        recyclerView.setAdapter(mTypeAdapter);
    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        switch (v.getId())
        {
            case  R.id.rl_back:
                finish();
                break;
        }
    }
}
