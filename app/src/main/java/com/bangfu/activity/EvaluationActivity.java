package com.bangfu.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.ResultHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.DialogUtils;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.TypeUtils;
import com.bangfu.utils.Urls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/22 15:01
 * 邮箱：wangxianyun1@163.com
 * 描述：求助解答--->对咨询解答进行评价
 */
public class EvaluationActivity extends BaseActivity implements IRequestListener
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
    @BindView(R.id.tv_eval_submit)
    TextView       tvSubmit;
    @BindView(R.id.tv_evaluation_content)
    EditText       tvEvaluationContent;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_evaluation)
    TextView       tvEvaluation;

    private QuestionInfo mQuestionInfo;

    private int eval;
    private static final int    REQUEST_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL          = 0x02;
    private static final String UPDATA_QUESTION            = "UPDATA_QUESTION";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case REQUEST_SUCCESS:
                    ToastUtil.show(EvaluationActivity.this, "操作成功");
                    finish();
                    break;
                case REQUEST_FAIL:
                    ToastUtil.show(EvaluationActivity.this, msg.obj.toString());
                    break;


            }
        }
    };


    @Override
    protected void initData()
    {
        mQuestionInfo = (QuestionInfo) getIntent().getSerializableExtra("QuestionInfo");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_evaluation);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvEvaluation.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {

        if("0".equals(ConfigManager.instance().getHolder()))
        {
            tvSubmit.setVisibility(View.GONE);
            tvEvaluation.setEnabled(false);
        }
        tvTitle.setText("求助解答评价");
        if (null != mQuestionInfo)
        {
            tvQuestionType.setText(TypeUtils.getConsultTypeName(mQuestionInfo.getQuestionTypeId()));
            tvTime.setText(mQuestionInfo.getAnsDate());
            tvCompany.setText(TypeUtils.getOrgName(mQuestionInfo.getOid()));
            tvContent.setText(mQuestionInfo.getContent());
            tvAnswerContent.setText(mQuestionInfo.getAnswer());
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

            case R.id.tv_evaluation:
                final List<String> mList = Arrays.asList(getResources().getStringArray(R.array.evalType));
                DialogUtils.showCategoryDialog(EvaluationActivity.this, mList, new MyItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        eval = position + 1;
                        tvEvaluation.setText(mList.get(position));
                    }
                });

                break;

            case R.id.tv_eval_submit:
                Map<String, String> valuePairs = new HashMap<>();


                valuePairs.put("id", mQuestionInfo.getId());
                valuePairs.put("eval", eval+"");
                valuePairs.put("pid", ConfigManager.instance().getUserID());
                DataRequest.instance().request(EvaluationActivity.this, Urls.getEvaluationUrl(), this, HttpRequest.POST, UPDATA_QUESTION, valuePairs,
                        new ResultHandler());


                break;
        }
    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (UPDATA_QUESTION.equals(action))
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
