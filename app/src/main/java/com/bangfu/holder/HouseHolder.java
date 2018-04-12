package com.bangfu.holder;

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
 * 描述：户主个人信息
 */
public class HouseHolder extends FarmerInformationBaseHolder
{
    private TextView tv_userName;
    private TextView tv_identityCard;
    private TextView tv_population;
    private TextView tv_address;
    private EditText et_phone;
    private MyItemClickListener myItemClickListener;

    public HouseHolder(View rootView,MyItemClickListener myItemClickListener)
    {
        super(rootView);
        this.myItemClickListener = myItemClickListener;
        tv_userName = (TextView) rootView.findViewById(R.id.tv_userName);
        tv_identityCard = (TextView) rootView.findViewById(R.id.tv_identityCard);
        tv_population = (TextView) rootView.findViewById(R.id.tv_population);
        tv_address = (TextView) rootView.findViewById(R.id.tv_address);
        et_phone = (EditText) rootView.findViewById(R.id.et_phone);
    }

    @Override
    public void setFarmerDescInfo(UserInfo mUserInfo, int position)
    {

        if (null != mUserInfo)
        {

            tv_userName.setText(mUserInfo.getPname());
            tv_identityCard.setText(mUserInfo.getCid());
           tv_population.setText(mUserInfo.getAmount());
            tv_address.setText(mUserInfo.getFaddr());
            et_phone.setText(mUserInfo.getPhone());


//            if (mFarmerDescInfo.getEditStatus() == 0)
//            {
//                et_phone.setEnabled(false);
//                et_phone.setBackgroundResource(R.color.transparent);
//            }
//            else
//            {
//                et_phone.setCursorVisible(true);
//                et_phone.setEnabled(true);
//                et_phone.setBackgroundResource(R.color.gray);
//            }
        }

    }
}
