package com.bangfu.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;

import butterknife.BindView;

/**
 * 作者：王先云 on 2017/11/22 15:01
 * 邮箱：wangxianyun1@163.com
 * 描述：求助解答--->对咨询解答进行评价
 */
public class QuestionDetailActivity extends BaseActivity
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
        setContentView(R.layout.activity_question_detail);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("回访评价");
    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case  R.id.rl_back:
                finish();
                break;

            case  R.id.tv_submit:
                break;
        }
    }
}
