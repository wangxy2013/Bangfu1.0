package com.bangfu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.TownInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 */
public class TownHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView       tv_userName;
    private RelativeLayout mItemLayout;

    private MyItemClickListener myItemClickListener1;

    public TownHolder(View rootView, MyItemClickListener myItemClickListener1)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        tv_userName = (TextView) rootView.findViewById(R.id.tv_name);
        mItemLayout = (RelativeLayout) rootView.findViewById(R.id.rl_item);
        mItemLayout.setOnClickListener(this);

    }

    public void setTownInfo(TownInfo mTownInfo)
    {
        tv_userName.setText(mTownInfo.getDname());

    }


    @Override
    public void onClick(View v)
    {
        if (v == mItemLayout)
        {
            myItemClickListener1.onItemClick(v, getAdapterPosition());
        }
    }
}
