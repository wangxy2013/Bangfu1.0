package com.bangfu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.UserInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：成员列表
 */
public class ChengyuanHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private LinearLayout        itemLyaout;
    private TextView            tv_userName;
    private TextView            tv_pid;
    private TextView            tv_cid;
    private MyItemClickListener myItemClickListener1;


    public ChengyuanHolder(View rootView, MyItemClickListener myItemClickListener1)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        itemLyaout= (LinearLayout) rootView.findViewById(R.id.ll_item);
        tv_userName = (TextView) rootView.findViewById(R.id.tv_userName);
        tv_pid = (TextView) rootView.findViewById(R.id.tv_pid);
        tv_cid = (TextView) rootView.findViewById(R.id.tv_cid);
        itemLyaout.setOnClickListener(this);

    }

    public void setUserInfo(UserInfo mUserInfo)
    {
        tv_userName.setText(mUserInfo.getPname());
        tv_pid.setText(mUserInfo.getPid());
        tv_cid.setText(mUserInfo.getCid());
    }

    @Override
    public void onClick(View v)
    {
        if (v == itemLyaout)
        {
            myItemClickListener1.onItemClick(v, getAdapterPosition());
        }

    }
}
