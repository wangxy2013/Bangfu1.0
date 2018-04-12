package com.bangfu.holder;

import android.graphics.Paint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：家庭列表
 */
public class FamilyHolder extends FarmerInformationBaseHolder implements View.OnClickListener
{
    private TextView            tv_userName;
    private TextView            tv_number;
    private TextView            tv_population;
    private TextView            tv_address;
    private TextView            tv_phone;
    private TextView            tv_modify;
    private TextView            tv_desc;
    private MyItemClickListener myItemClickListener1;
    private MyItemClickListener myItemClickListener2;

    public FamilyHolder(View rootView, MyItemClickListener myItemClickListener1, MyItemClickListener myItemClickListener2)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        this.myItemClickListener2 = myItemClickListener2;
        tv_userName = (TextView) rootView.findViewById(R.id.tv_userName);
        tv_number = (TextView) rootView.findViewById(R.id.tv_number);
        tv_population = (TextView) rootView.findViewById(R.id.tv_population);
        tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
        tv_modify = (TextView) rootView.findViewById(R.id.tv_modify);
        tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        tv_modify.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_modify.getPaint().setAntiAlias(true);//抗锯齿
        tv_desc.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_desc.getPaint().setAntiAlias(true);//抗锯齿

        tv_modify.setOnClickListener(this);
        tv_desc.setOnClickListener(this);

    }

    @Override
    public void setFarmerDescInfo(UserInfo mUserInfo, int position)
    {
        if (null != mUserInfo)
        {

            tv_userName.setText(mUserInfo.getPname());
            tv_number.setText(mUserInfo.getFid());
            tv_population.setText(mUserInfo.getAmount());
            tv_address.setText(mUserInfo.getFaddr());
            tv_phone.setText(mUserInfo.getPhone());
        }


    }

    @Override
    public void onClick(View v)
    {
        if (v == tv_modify)
        {
            myItemClickListener2.onItemClick(v, getAdapterPosition());
        }
        else if (v == tv_desc)
        {
            myItemClickListener1.onItemClick(v, getAdapterPosition());
        }
    }
}
