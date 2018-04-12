package com.bangfu.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.utils.ToastUtil;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/11/16 09:36
 * 邮箱：wangxianyun1@163.com
 * 描述：loading
 */
public class LoadActivity extends BaseActivity
{


    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case 1:
                    startActivity(new Intent(LoadActivity.this, IndexActivity.class));
                    finish();
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
        setContentView(R.layout.activity_loading);
        setTranslucentStatus();
    }

    @Override
    protected void initEvent()
    {


    }

    @Override
    protected void initViewData()
    {
        mHandler.sendEmptyMessageDelayed(1, 3 * 1000);
    }


}
