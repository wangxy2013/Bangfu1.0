package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.TownInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.holder.TownHolder;
import com.bangfu.holder.VillageHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 */
public class VillageAdapter extends RecyclerView.Adapter<VillageHolder>
{

    private List<UserInfo>      list;
    private Context             mContext;
    private MyItemClickListener mListener;

    public VillageAdapter(List<UserInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public VillageHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        VillageHolder mBaseHolder = null;

        mBaseHolder = new VillageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_village, parent, false), mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(VillageHolder holder, int position)
    {
        UserInfo mUserInfo = list.get(position);
        holder.setUserInfo(mUserInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
