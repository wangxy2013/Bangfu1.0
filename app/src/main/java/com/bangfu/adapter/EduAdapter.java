package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.EduAssistInfo;
import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.holder.EduHolder;
import com.bangfu.holder.HtyHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 * DESC:教育救助
 */
public class EduAdapter extends RecyclerView.Adapter<EduHolder>
{

    private List<EduAssistInfo> list;
    private Context             mContext;
    private MyItemClickListener mListener;

    public EduAdapter(List<EduAssistInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public EduHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        EduHolder mBaseHolder = null;

        mBaseHolder = new EduHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edu, parent, false), mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(EduHolder holder, int position)
    {
        EduAssistInfo mHtyPresentInfo = list.get(position);
        holder.setEduAssistInfo(mHtyPresentInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
