package com.bangfu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.TownInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.StringUtils;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 */
public class VillageHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView       tv_userName;
    private RelativeLayout mItemLayout;
    private TextView       tv_detail;
    private ImageView      iv_arrow;

    private MyItemClickListener myItemClickListener1;

    public VillageHolder(View rootView, MyItemClickListener myItemClickListener1)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        tv_userName = (TextView) rootView.findViewById(R.id.tv_name);
        mItemLayout = (RelativeLayout) rootView.findViewById(R.id.rl_item);
        tv_detail = (TextView) rootView.findViewById(R.id.tv_detail);
        iv_arrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
        mItemLayout.setOnClickListener(this);

    }

    public void setUserInfo(UserInfo mUserInfo)
    {

        if (StringUtils.stringIsEmpty(mUserInfo.getPid()))
        {
            tv_userName.setText(mUserInfo.getDname());
            iv_arrow.setVisibility(View.VISIBLE);
            tv_detail.setVisibility(View.GONE);
        }
        else
        {
            tv_userName.setText(mUserInfo.getPname());
            iv_arrow.setVisibility(View.INVISIBLE);
            tv_detail.setVisibility(View.VISIBLE);
        }


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
