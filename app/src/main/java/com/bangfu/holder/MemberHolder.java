package com.bangfu.holder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:12
 * 邮箱：wangxianyun1@163.com
 * 描述：家庭成员基本信息
 */
public class MemberHolder extends FarmerInformationBaseHolder implements View.OnClickListener
{
    private TextView tv_title;
    private TextView tv_userName;
    private TextView tv_identityCard;
    private TextView tv_address;
    private TextView tv_sex;
    private TextView tv_relationship;
    private TextView tv_maritalStatus;
    private TextView tv_employmentStatus;
    private TextView tv_health;
    private TextView tv_number;
    private TextView tv_edit;
    private EditText et_phone;

    private MyItemClickListener myItemClickListener;

    public MemberHolder(View rootView, MyItemClickListener myItemClickListener)
    {
        super(rootView);
        this.myItemClickListener = myItemClickListener;
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_userName = (TextView) rootView.findViewById(R.id.tv_userName);
        tv_identityCard = (TextView) rootView.findViewById(R.id.tv_identityCard);
        tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        et_phone = (EditText) rootView.findViewById(R.id.et_phone);
        tv_sex = (TextView) rootView.findViewById(R.id.tv_sex);
        tv_relationship = (TextView) rootView.findViewById(R.id.tv_relationship);
        tv_maritalStatus = (TextView) rootView.findViewById(R.id.tv_maritalStatus);
        tv_employmentStatus = (TextView) rootView.findViewById(R.id.tv_employmentStatus);
        tv_health = (TextView) rootView.findViewById(R.id.tv_health);
        tv_number = (TextView) rootView.findViewById(R.id.tv_number);
        tv_edit = (TextView) rootView.findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(this);
    }

    @Override
    public void setFarmerDescInfo(UserInfo mUserInfo, int position)
    {
        if (position == 1)
        {
            tv_title.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_title.setVisibility(View.GONE);
        }
        tv_number.setText("家庭成员" + position);
        tv_userName.setText(mUserInfo.getPname());
        tv_identityCard.setText(mUserInfo.getCid());
        et_phone.setText(mUserInfo.getPhone());
        tv_sex.setText(mUserInfo.getSexName());
        tv_relationship.setText(mUserInfo.getRelationTypeName());
        tv_maritalStatus.setText(mUserInfo.getMarriedTypeName());
        tv_employmentStatus.setText(mUserInfo.getWorkTypeName());
        tv_health.setText(mUserInfo.getHealthyTypeName());


    }

    @Override
    public void onClick(View v)
    {
        if (v == tv_edit)
        {
            myItemClickListener.onItemClick(v, getAdapterPosition());
        }

    }
}
