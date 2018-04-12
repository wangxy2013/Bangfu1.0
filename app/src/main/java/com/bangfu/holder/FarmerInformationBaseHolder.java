package com.bangfu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.UserInfo;


/**
 * 作者：王先云 on 2016/3/22 08:49
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public abstract class FarmerInformationBaseHolder extends RecyclerView.ViewHolder
{
    public FarmerInformationBaseHolder(View itemView)
    {
        super(itemView);
    }

    public abstract void setFarmerDescInfo(UserInfo mUserInfo, int position);
}
