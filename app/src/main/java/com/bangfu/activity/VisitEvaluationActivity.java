package com.bangfu.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/22 14:51
 * 邮箱：wangxianyun1@163.com
 * 描述：回访评价
 */
public class VisitEvaluationActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_question_type)
    TextView       tvQuestionType;
    @BindView(R.id.tv_time)
    TextView       tvTime;
    @BindView(R.id.tv_company)
    TextView       tvCompany;
    @BindView(R.id.tv_content)
    TextView       tvContent;
    @BindView(R.id.tv_answer_content)
    TextView       tvAnswerContent;
    @BindView(R.id.tv_evaluation_content)
    TextView       tvEvaluationContent;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_visit_evaluation);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {

    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("回访评价");
    }


}
