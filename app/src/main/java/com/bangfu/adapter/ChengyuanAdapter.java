package com.bangfu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.entity.UserInfo;
import com.bangfu.holder.ChengyuanHolder;
import com.bangfu.holder.HuzhuHolder;
import com.bangfu.listener.MyItemClickListener;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2017/12/1 23:25
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class ChengyuanAdapter extends RecyclerView.Adapter<ChengyuanHolder>
{

    private ArrayList<UserInfo> mList;

    private MyItemClickListener mListener;

    public ChengyuanAdapter(ArrayList<UserInfo> mList, MyItemClickListener mListener)
    {
        this.mList = mList;
        this.mListener = mListener;
    }

    @Override
    public ChengyuanHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_name, parent, false);
        ChengyuanHolder mTypeHolder = new ChengyuanHolder(itemView,mListener);
        return mTypeHolder;
    }

    @Override
    public void onBindViewHolder(ChengyuanHolder holder, int position)
    {
        holder.setUserInfo(mList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }
}
