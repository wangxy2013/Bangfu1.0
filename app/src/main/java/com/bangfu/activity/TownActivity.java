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
import com.bangfu.adapter.TownAdapter;
import com.bangfu.entity.TownInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.TownListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.Urls;
import com.bangfu.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：王先云 on 2018/4/9 14:16
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class TownActivity extends BaseActivity implements IRequestListener
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView        tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView    mRecyclerView;
    private ArrayList<TownInfo> mTownInfoList = new ArrayList<>();
    private TownAdapter mTownAdapter;


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

                    TownListHandler mTownListHandler = (TownListHandler) msg.obj;
                    mTownInfoList.addAll(mTownListHandler.getTownInfoList());
                    mTownAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(TownActivity.this, msg.obj.toString());
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
        setContentView(R.layout.activity_twon);
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

        tvTitle.setText("区域列表");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));

        mTownAdapter = new TownAdapter(mTownInfoList, this, new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                Bundle b = new Bundle();
                b.putSerializable("TownInfo", mTownInfoList.get(position));
                startActivity(new Intent(TownActivity.this, VillageActivity.class).putExtras(b));
            }
        });
        mRecyclerView.setAdapter(mTownAdapter);


        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(TownActivity.this, Urls.getDeptListUrl(), this, HttpRequest.GET, GET_LIST, valuePairs,
                new TownListHandler());

    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        if(v== rlBack)
        {
            finish();
        }
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
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
