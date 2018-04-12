package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.NewsInfo;
import com.bangfu.entity.PolicyInfo;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.holder.MyQuestionHolder;
import com.bangfu.holder.ProgramPolicyHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 * DESC:扶贫政策
 */
public class ProgramPolicyAdapter extends RecyclerView.Adapter<ProgramPolicyHolder>
{

    private List<NewsInfo>      list;
    private Context             mContext;
    private MyItemClickListener mListener;

    public ProgramPolicyAdapter(List<NewsInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public ProgramPolicyHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ProgramPolicyHolder mBaseHolder = null;

        mBaseHolder = new ProgramPolicyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program_program, parent, false), mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(ProgramPolicyHolder holder, int position)
    {
        NewsInfo mNewsInfo = list.get(position);
        holder.setPolicyInfo(mNewsInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
