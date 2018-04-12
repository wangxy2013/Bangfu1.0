package com.bangfu.holder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.UserInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：家庭列表
 */
public class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView tv_typeName;

    private MyItemClickListener myItemClickListener1;


    public TypeHolder(View rootView, MyItemClickListener myItemClickListener1)
    {
        super(rootView);
        this.myItemClickListener1 = myItemClickListener1;
        tv_typeName = (TextView) rootView.findViewById(R.id.tv_typeName);


        tv_typeName.setOnClickListener(this);

    }

    public void setTypeName(String name)
    {
        tv_typeName.setText(name);
    }

    @Override
    public void onClick(View v)
    {
        if (v == tv_typeName)
        {
            myItemClickListener1.onItemClick(v, getAdapterPosition());
        }

    }
}
