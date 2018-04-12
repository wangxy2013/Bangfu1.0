package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.holder.FamilyHolder;
import com.bangfu.holder.FarmerInformationBaseHolder;
import com.bangfu.holder.HouseHolder;
import com.bangfu.holder.MemberHolder;
import com.bangfu.listener.MyItemClickListener;


import java.util.List;


/**
 * User: 王先云
 * Date: 2015-09-01 15:28
 * DESC:农户基本信息
 */
public class FarmerInfomationAdapter extends RecyclerView.Adapter<FarmerInformationBaseHolder>
{

    private List<UserInfo> list;
    private Context              mContext;
    private MyItemClickListener  myItemClickListener1;
    private MyItemClickListener  myItemClickListener2;
    public FarmerInfomationAdapter(List<UserInfo> list, Context mContext,MyItemClickListener  myItemClickListener1,MyItemClickListener myItemClickListener2)
    {
        this.list = list;
        this.mContext = mContext;
        this.myItemClickListener1 = myItemClickListener1;
        this.myItemClickListener2 = myItemClickListener2;
    }
    public FarmerInfomationAdapter(List<UserInfo> list, Context mContext,MyItemClickListener  myItemClickListener1)
    {
        this.list = list;
        this.mContext = mContext;
        this.myItemClickListener1 = myItemClickListener1;
    }

    @Override
    public FarmerInformationBaseHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        FarmerInformationBaseHolder mBaseHolder = null;
        switch (viewType)
        {

            case 1:
                mBaseHolder = new HouseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_householder_info, parent, false),myItemClickListener1);
                break;

            case 2:
                mBaseHolder = new MemberHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_info, parent, false),myItemClickListener1);
                break;

            case 3:
                mBaseHolder = new FamilyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_family_info, parent, false),myItemClickListener1,myItemClickListener2);
                break;

        }

        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(FarmerInformationBaseHolder holder, int position)
    {
        UserInfo mUserInfo = list.get(position);
        holder.setFarmerDescInfo(mUserInfo, position);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


    @Override
    public int getItemViewType(int position)
    {
        UserInfo mUserInfo = list.get(position);
        int type=mUserInfo.getType();
        return type;

    }


}
