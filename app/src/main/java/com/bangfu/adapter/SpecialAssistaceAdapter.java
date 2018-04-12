package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.holder.MyQuestionHolder;
import com.bangfu.holder.SpecialAssistanceHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 * DESC: 特别援助
 */
public class SpecialAssistaceAdapter extends RecyclerView.Adapter<SpecialAssistanceHolder>
{

    private List<QuestionInfo>  list;
    private Context             mContext;
    private String              type;
    private MyItemClickListener mListener;

    public SpecialAssistaceAdapter(List<QuestionInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.mListener = mListener;
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public SpecialAssistanceHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        SpecialAssistanceHolder mBaseHolder = null;
        mBaseHolder = new SpecialAssistanceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_assistance, parent, false), mListener);

        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(SpecialAssistanceHolder holder, int position)
    {
        QuestionInfo mQuestionInfo = list.get(position);
        holder.setQuestionInfo(mContext, mQuestionInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
