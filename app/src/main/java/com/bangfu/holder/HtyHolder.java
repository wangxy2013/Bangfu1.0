package com.bangfu.holder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：大病求助
 */
public class HtyHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView            tv_userName;
    private TextView            tv_number;
    private TextView            tv_grbh;
    private TextView            tv_grxm;
    private TextView            tv_illnessName;
    private TextView            tv_hospital;
    private TextView            tv_cost;
    private TextView    tv_relationTypeName;
    private TextView            tv_detail;
    private TextView            tv_desc;
    private MyItemClickListener myItemClickListener1;
    public HtyHolder(View rootView, MyItemClickListener myItemClickListener1)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        tv_userName = (TextView) rootView.findViewById(R.id.tv_userName);
        tv_number = (TextView) rootView.findViewById(R.id.tv_number);
        tv_grbh = (TextView) rootView.findViewById(R.id.tv_grbh);
        tv_grxm = (TextView) rootView.findViewById(R.id.tv_grxm);
        tv_illnessName = (TextView) rootView.findViewById(R.id.tv_illnessName);
        tv_hospital = (TextView) rootView.findViewById(R.id.tv_hospital);
        tv_cost = (TextView) rootView.findViewById(R.id.tv_cost);
        tv_detail = (TextView) rootView.findViewById(R.id.tv_detail);
        tv_relationTypeName= (TextView) rootView.findViewById(R.id.tv_relationTypeName);
        tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        tv_desc.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_desc.getPaint().setAntiAlias(true);//抗锯齿

        tv_desc.setOnClickListener(this);

    }

public  void  setHtyPresentInfo(HtyPresentInfo mHtyPresentInfo)
{
    tv_userName.setText(mHtyPresentInfo.getHolderName());
    tv_number.setText(mHtyPresentInfo.getFid());
    tv_grbh.setText(mHtyPresentInfo.getPid());
    tv_grxm.setText(mHtyPresentInfo.getPname());
    tv_illnessName.setText(mHtyPresentInfo.getIllnessName());
    tv_hospital.setText(mHtyPresentInfo.getHospital());
    tv_cost.setText(mHtyPresentInfo.getCost());
    tv_detail.setText(mHtyPresentInfo.getDescb());
    tv_relationTypeName.setText(mHtyPresentInfo.getRelationTypeName());
}


    @Override
    public void onClick(View v)
    {
       if (v == tv_desc)
        {
            myItemClickListener1.onItemClick(v, getAdapterPosition());
        }
    }
}
