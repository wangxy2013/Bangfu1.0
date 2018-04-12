package com.bangfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.holder.FamilyHolder;
import com.bangfu.holder.FarmerInformationBaseHolder;
import com.bangfu.holder.HouseHolder;
import com.bangfu.holder.MemberHolder;
import com.bangfu.holder.MyQuestionHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.List;


/**
 * User: 王先云
 * Date: 2017-11-17 15:28
 * DESC:农户基本信息
 */
public class MyQuestionAdapter extends RecyclerView.Adapter<MyQuestionHolder>
{

    private List<QuestionInfo>  list;
    private Context             mContext;
    private MyItemClickListener mListener;

    public MyQuestionAdapter(List<QuestionInfo> list, Context mContext, MyItemClickListener mListener)
    {
        this.list = list;
        this.mContext = mContext;
        this.mListener = mListener;
    }


    @Override
    public MyQuestionHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyQuestionHolder mBaseHolder = null;

        mBaseHolder = new MyQuestionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_question, parent, false),mContext, mListener);


        return mBaseHolder;
    }


    @Override
    public void onBindViewHolder(MyQuestionHolder holder, int position)
    {
        QuestionInfo mQuestionInfo = list.get(position);
        holder.setQuestionInfo(mQuestionInfo);
    }


    @Override
    public int getItemCount()
    {

        return list.size();

    }


}
