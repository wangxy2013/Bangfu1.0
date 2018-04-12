package com.bangfu.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.HolderListHandler;
import com.bangfu.json.QuestionListHandler;
import com.bangfu.json.ResultHandler;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.TypeUtils;
import com.bangfu.utils.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/22 10:39
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class AnswerActivity extends BaseActivity implements IRequestListener
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
    @BindView(R.id.btn_submit)
    Button         btnSubmit;
    @BindView(R.id.et_answer)
    EditText       etAnswer;

    private QuestionInfo mQuestionInfo;


    private static final int    REQUEST_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL    = 0x02;
    private static final String POST_ANSWER     = "POST_ANSWER";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_SUCCESS:
                    ToastUtil.show(AnswerActivity.this, "操作成功");
                    finish();

                    break;

                case REQUEST_FAIL:

                    ToastUtil.show(AnswerActivity.this, msg.obj.toString());
                    break;


            }
        }
    };


    @Override
    protected void initData()
    {
        mQuestionInfo = (QuestionInfo) getIntent().getSerializableExtra("QUESTION");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {

        setContentView(R.layout.activity_answer);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("问题解答");

        if (null != mQuestionInfo)
        {
            tvQuestionType.setText(TypeUtils.getConsultTypeName(mQuestionInfo.getQuestionTypeId()));
            tvTime.setText(mQuestionInfo.getSubDate());
            tvCompany.setText(TypeUtils.getOrgName(mQuestionInfo.getOid()));
            tvContent.setText(mQuestionInfo.getContent());
        }

    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.rl_back:
                finish();
                break;

            case R.id.btn_submit:
                String mAnswerContent = etAnswer.getText().toString();
                if (StringUtils.stringIsEmpty(mAnswerContent))
                {
                    ToastUtil.show(AnswerActivity.this, "请输入回答内容");
                    return;
                }

                Map<String, String> valuePairs = new HashMap<>();
                valuePairs.put("id", mQuestionInfo.getId());
                valuePairs.put("answer", mAnswerContent);
                valuePairs.put("pid", ConfigManager.instance().getUserID());
                DataRequest.instance().request(AnswerActivity.this, Urls.getSaveConsultInfoUrl(), this, HttpRequest.POST, POST_ANSWER, valuePairs,
                        new ResultHandler());


                break;
        }
    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (POST_ANSWER.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }
}
