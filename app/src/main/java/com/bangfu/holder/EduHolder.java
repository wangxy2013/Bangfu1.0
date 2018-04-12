package com.bangfu.holder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.EduAssistInfo;
import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：教育求助
 */
public class EduHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView            tv_userName;
    private TextView            tv_number;
    private TextView            tv_grbh;
    private TextView            tv_grxm;
    private TextView            tv_phaseName;
    private TextView            tv_school;
    private TextView            tv_cost;
    private TextView    tv_relationTypeName;
    private TextView            tv_detail;
    private TextView            tv_desc;
    private TextView            tv_className;
    private TextView   tv_paymentTime;
    private MyItemClickListener myItemClickListener1;
    public EduHolder(View rootView, MyItemClickListener myItemClickListener1)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        tv_userName = (TextView) rootView.findViewById(R.id.tv_userName);
        tv_number = (TextView) rootView.findViewById(R.id.tv_number);
        tv_grbh = (TextView) rootView.findViewById(R.id.tv_grbh);
        tv_grxm = (TextView) rootView.findViewById(R.id.tv_grxm);
        tv_phaseName = (TextView) rootView.findViewById(R.id.tv_phaseName);
        tv_school = (TextView) rootView.findViewById(R.id.tv_school);
        tv_cost = (TextView) rootView.findViewById(R.id.tv_cost);
        tv_detail = (TextView) rootView.findViewById(R.id.tv_detail);
        tv_relationTypeName= (TextView) rootView.findViewById(R.id.tv_relationTypeName);
        tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        tv_className = (TextView) rootView.findViewById(R.id.tv_className);
        tv_paymentTime= (TextView) rootView.findViewById(R.id.tv_paymentTime);
        tv_desc.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_desc.getPaint().setAntiAlias(true);//抗锯齿

        tv_desc.setOnClickListener(this);

    }

public  void  setEduAssistInfo(EduAssistInfo mEduAssistInfo)
{
    tv_userName.setText(mEduAssistInfo.getHolderName());
    tv_number.setText(mEduAssistInfo.getFid());
    tv_grbh.setText(mEduAssistInfo.getPid());
    tv_grxm.setText(mEduAssistInfo.getPname());
    tv_phaseName.setText(mEduAssistInfo.getPhaseName());
    tv_school.setText(mEduAssistInfo.getEschool());
    tv_cost.setText(mEduAssistInfo.getEcost());
    tv_className.setText(mEduAssistInfo.getEclassName());
    tv_detail.setText(mEduAssistInfo.getDescb());
    tv_paymentTime.setText(mEduAssistInfo.getPaymentTime());
    tv_relationTypeName.setText(mEduAssistInfo.getRelationTypeName());
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
