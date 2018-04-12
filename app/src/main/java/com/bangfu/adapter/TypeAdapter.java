package com.bangfu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.holder.TypeHolder;
import com.bangfu.listener.MyItemClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 作者：王先云 on 2017/12/1 23:25
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class TypeAdapter  extends RecyclerView.Adapter<TypeHolder>
{

    private ArrayList<String> mNameList;
    private MyItemClickListener mListener;

    public TypeAdapter(ArrayList<String> mNameList, MyItemClickListener mListener)
    {
        this.mNameList = mNameList;
        this.mListener = mListener;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_name, parent, false);
        TypeHolder mTypeHolder = new TypeHolder(itemView,mListener);
        return mTypeHolder;
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position)
    {
        holder.setTypeName(mNameList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mNameList.size();
    }
}
