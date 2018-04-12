package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.EduAssistInfo;
import com.bangfu.entity.TownInfo;
import com.bangfu.holder.EduHolder;
import com.bangfu.holder.TownHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 */
public class TownAdapter extends RecyclerView.Adapter<TownHolder>
{

    private List<TownInfo>      list;
    private Context             mContext;
    private MyItemClickListener mListener;

    public TownAdapter(List<TownInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public TownHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        TownHolder mBaseHolder = null;

        mBaseHolder = new TownHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_town, parent, false), mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(TownHolder holder, int position)
    {
        TownInfo mTownInfo = list.get(position);
        holder.setTownInfo(mTownInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
