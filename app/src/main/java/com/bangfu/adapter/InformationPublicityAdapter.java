package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.InformationInfo;
import com.bangfu.entity.PolicyInfo;
import com.bangfu.holder.InformationPublicityHolder;
import com.bangfu.holder.ProgramPolicyHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 * DESC: 信息宣传
 */
public class InformationPublicityAdapter extends RecyclerView.Adapter<InformationPublicityHolder>
{

    private List<InformationInfo> list;
    private Context               mContext;
    private MyItemClickListener   mListener;

    public InformationPublicityAdapter(List<InformationInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public InformationPublicityHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        InformationPublicityHolder mBaseHolder = null;

        mBaseHolder = new InformationPublicityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_information_publicity, parent, false), mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(InformationPublicityHolder holder, int position)
    {
        InformationInfo mInformationInfo = list.get(position);
        holder.setInformationInfo(mInformationInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
