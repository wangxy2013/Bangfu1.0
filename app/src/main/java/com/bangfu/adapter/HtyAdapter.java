package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.entity.NewsInfo;
import com.bangfu.holder.HtyHolder;
import com.bangfu.holder.ProgramPolicyHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 * DESC:大病救助
 */
public class HtyAdapter extends RecyclerView.Adapter<HtyHolder>
{

    private List<HtyPresentInfo> list;
    private Context              mContext;
    private MyItemClickListener  mListener;

    public HtyAdapter(List<HtyPresentInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public HtyHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        HtyHolder mBaseHolder = null;

        mBaseHolder = new HtyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hty, parent, false), mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(HtyHolder holder, int position)
    {
        HtyPresentInfo mHtyPresentInfo = list.get(position);
        holder.setHtyPresentInfo(mHtyPresentInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
