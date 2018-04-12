package com.bangfu.holder;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.FarmerDescInfo;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.TypeUtils;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：我的咨询求组
 */
public class MyQuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    private TextView            tv_question_type;
    private TextView            tv_time;
    private TextView            tv_company;
    private TextView            tv_content;
    private TextView            tv_status;
    private TextView            tv_desc;
    private MyItemClickListener mListener;
    private Context             mContext;

    public MyQuestionHolder(View rootView, Context mContext, MyItemClickListener mListener)
    {
        super(rootView);
        this.mContext = mContext;
        this.mListener = mListener;
        tv_question_type = (TextView) rootView.findViewById(R.id.tv_question_type);
        tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        tv_company = (TextView) rootView.findViewById(R.id.tv_company);
        tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        tv_status = (TextView) rootView.findViewById(R.id.tv_status);
        tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        tv_desc.setOnClickListener(this);

    }


    public void setQuestionInfo(QuestionInfo mQuestionInfo)
    {
        if (null != mQuestionInfo)
        {
            tv_question_type.setText(TypeUtils.getConsultTypeName(mQuestionInfo.getQuestionTypeId()));
            tv_time.setText(mQuestionInfo.getSubDate());
            tv_company.setText(TypeUtils.getOrgName(mQuestionInfo.getOid()));
            tv_content.setText(mQuestionInfo.getContent());
            //
            //            tv_status.setText(mQuestionInfo.getStatus());

            if (mQuestionInfo.getAid() == 0)
            {
                tv_status.setText("未解决");
                tv_status.setTextColor(ContextCompat.getColor(mContext, R.color.redA));
                tv_desc.setVisibility(View.GONE);
            }
            else
            {
                tv_status.setText("已解决");
                tv_status.setTextColor(ContextCompat.getColor(mContext, R.color.blackA));
            }

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
