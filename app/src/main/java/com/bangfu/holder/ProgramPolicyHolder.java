package com.bangfu.holder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.NewsInfo;
import com.bangfu.entity.PolicyInfo;
import com.bangfu.listener.MyItemClickListener;

/**
 * 作者：王先云 on 2017/11/21 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：扶贫政策
 */
public class ProgramPolicyHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private RelativeLayout itemLayout;
    private TextView       tv_name;

    private MyItemClickListener mListener;

    public ProgramPolicyHolder(View rootView, MyItemClickListener myItemClickListener)
    {
        super(rootView);

        this.mListener = myItemClickListener;
        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        itemLayout = (RelativeLayout) rootView.findViewById(R.id.rl_item);
        itemLayout.setOnClickListener(this);
    }

    public void setPolicyInfo(NewsInfo mNewsInfo)
    {

        if (null != mNewsInfo)
        {
            tv_name.setText(mNewsInfo.getTitle());

        }

    }

    @Override
    public void onClick(View v)
    {
        if (mListener != null)
        {
            mListener.onItemClick(v, getAdapterPosition());
        }
    }
}
