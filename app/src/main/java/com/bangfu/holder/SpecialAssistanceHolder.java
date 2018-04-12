package com.bangfu.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.TypeUtils;

/**
 * 作者：王先云 on 2017/11/20 10:05
 * 邮箱：wangxianyun1@163.com
 * 描述：特别援助
 */
public class SpecialAssistanceHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    private TextView tv_question_type;
    private TextView tv_time;
    private TextView tv_company;
    private TextView tv_content;
    private TextView tv_status;
    private Button   btn_answer;

    private MyItemClickListener mListener;

    public SpecialAssistanceHolder(View rootView, MyItemClickListener mListener)
    {
        super(rootView);
        this.mListener = mListener;
        tv_question_type = (TextView) rootView.findViewById(R.id.tv_question_type);
        tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        tv_company = (TextView) rootView.findViewById(R.id.tv_company);
        tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        tv_status = (TextView) rootView.findViewById(R.id.tv_status);
        btn_answer = (Button) rootView.findViewById(R.id.btn_answer);
        btn_answer.setOnClickListener(this);
    }


    public void setQuestionInfo(Context mContext, QuestionInfo mQuestionInfo)
    {
        if (null != mQuestionInfo)
        {
            tv_question_type.setText(TypeUtils.getConsultTypeName(mQuestionInfo.getQuestionTypeId()));
            tv_time.setText(mQuestionInfo.getSubDate());
            tv_company.setText(TypeUtils.getOrgName(mQuestionInfo.getOid()));
            tv_content.setText(mQuestionInfo.getContent());
            tv_status.setText(mQuestionInfo.getType());
            if ("2".equals(mQuestionInfo.getType()))
            {
                tv_status.setTextColor(ContextCompat.getColor(mContext, R.color.blueB));
                tv_status.setText("已解决");
                btn_answer.setText("回访评价");
            }
            else
            {
                tv_status.setTextColor(ContextCompat.getColor(mContext, R.color.redA));
                tv_status.setText("未解决");
                btn_answer.setText("立即解答");
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
