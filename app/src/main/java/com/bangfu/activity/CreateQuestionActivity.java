package com.bangfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.BfApplication;
import com.bangfu.R;
import com.bangfu.entity.ConsultTypeInfo;
import com.bangfu.entity.OrgInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.OrgInfoHandler;
import com.bangfu.json.OrgInfoListHandler;
import com.bangfu.json.ResultHandler;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.TypeUtils;
import com.bangfu.utils.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/21 14:26
 * 邮箱：wangxianyun1@163.com
 * 描述：新建咨询求助
 */
public class CreateQuestionActivity extends BaseActivity implements IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_submit)
    TextView       tvSubmit;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_company_title)
    TextView       tvCompanyTitle;
    @BindView(R.id.tv_choice_company)
    TextView       tvChoiceCompany;
    @BindView(R.id.tv_company)
    TextView       tvCompany;
    @BindView(R.id.tv_type_title)
    TextView       tvTypeTitle;
    @BindView(R.id.tv_choice_type)
    TextView       tvChoiceType;
    @BindView(R.id.tv_type)
    TextView       tvType;
    @BindView(R.id.tv_content_title)
    TextView       tvContentTitle;
    @BindView(R.id.et_content)
    EditText       etContent;
    @BindView(R.id.iv_choice)
    ImageView      ivChoice;
    @BindView(R.id.btn_submit)
    Button         btnSubmit;
    private ArrayList<OrgInfo>         mOrgInfoList         = new ArrayList<>();
    private ArrayList<ConsultTypeInfo> mConsultTypeInfoList = new ArrayList<>();


    private String questionTypeId;
    private String oid;
    private String isAnony = "1";//0-非匿名，1匿名

    private static final int REQUEST_SUCCESS = 0x01;
    public static final  int REQUEST_FAIL    = 0x02;
    private static final int GET_ORG_SUCCESS = 0x03;

    private static final String ADD_QUESTION = "add_question";
    private static final String GET_ORG      = "get_org";


    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case REQUEST_SUCCESS:
                    ToastUtil.show(CreateQuestionActivity.this, "操作成功");
                    finish();
                    break;
                case REQUEST_FAIL:
                    ToastUtil.show(CreateQuestionActivity.this, msg.obj.toString());
                    break;

                case GET_ORG_SUCCESS:
//                    OrgInfoListHandler mOrgInfoListHandler = (OrgInfoListHandler) msg.obj;
//                    mOrgInfoList.clear();
//                    mOrgInfoList.addAll(mOrgInfoListHandler.getOrgInfoList());
                    OrgInfoHandler mOrgInfoHandler = (OrgInfoHandler)msg.obj;

                    OrgInfo mOrgInfo=mOrgInfoHandler.getOrgInfo();

                    if(null != mOrgInfo)
                    {
                        oid = mOrgInfo.getOid();
                    }

                    break;
            }
        }
    };

    @Override
    protected void initData()
    {
        // mOrgInfoList = BfApplication.getInstance().getOrgInfoList();
        mConsultTypeInfoList = BfApplication.getInstance().getConsultTypeInfoList();
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_creat_question);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvChoiceCompany.setOnClickListener(this);
        tvChoiceType.setOnClickListener(this);
        ivChoice.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("新建咨询求助");
        //        tvSubmit.setText("●●●");
        //        tvSubmit.setTextSize(8);
        //        tvSubmit.setVisibility(View.VISIBLE);
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

            case R.id.tv_choice_company:


                if (StringUtils.stringIsEmpty(questionTypeId))
                {
                    ToastUtil.show(CreateQuestionActivity.this, "请先选择问题类型");
                    return;
                }

                if (!mOrgInfoList.isEmpty())
                {
                    ArrayList<String> orgList = TypeUtils.getOrgNameList(mOrgInfoList);
                    Intent mIntent3 = new Intent(CreateQuestionActivity.this, TypeActivity.class);
                    mIntent3.putStringArrayListExtra("LIST", orgList);
                    mIntent3.putExtra("TITLE", "咨询单位");
                    startActivityForResult(mIntent3, 11);
                }
                break;
            case R.id.tv_choice_type:

                ArrayList<String> mConsultTypeList = TypeUtils.getConsultTypeList(mConsultTypeInfoList);
                Intent mIntent4 = new Intent(CreateQuestionActivity.this, TypeActivity.class);
                mIntent4.putStringArrayListExtra("LIST", mConsultTypeList);
                mIntent4.putExtra("TITLE", "问题类型");
                startActivityForResult(mIntent4, 12);
                break;


            case R.id.btn_submit:
                String content = etContent.getText().toString();
                //                if (StringUtils.stringIsEmpty(oid))
                //                {
                //                    ToastUtil.show(this, "请选择咨询单位");
                //
                //                    return;
                //                }

                if (StringUtils.stringIsEmpty(questionTypeId))
                {
                    ToastUtil.show(this, "请选问题类型");

                    return;
                }

                if (StringUtils.stringIsEmpty(content))
                {
                    ToastUtil.show(this, "请输入咨询内容");
                    return;
                }
                Map<String, String> valuePairs = new HashMap<>();
                valuePairs.put("pid", ConfigManager.instance().getUserID());
                valuePairs.put("content", content);
                valuePairs.put("isAnony", isAnony);
                valuePairs.put("questionTypeId", questionTypeId);
                valuePairs.put("oid", oid);
                DataRequest.instance().request(CreateQuestionActivity.this, Urls.getAddConsultInfoUrl(), this, HttpRequest.POST, ADD_QUESTION, valuePairs,
                        new ResultHandler());


                break;
            case R.id.iv_choice:

                if ("1".equals(isAnony))
                {
                    isAnony = "0";
                    ivChoice.setImageResource(R.drawable.ic_choice_selected);
                }
                else
                {
                    isAnony = "1";
                    ivChoice.setImageResource(R.drawable.ic_choice_normal);
                }

                break;

        }
    }


    private void getOrg(String conTypeId)
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("conTypeId", conTypeId);
        DataRequest.instance().request(CreateQuestionActivity.this, Urls.getOrgForApp1Url(), this, HttpRequest.GET, GET_ORG, valuePairs,
                new OrgInfoHandler());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        int p = 0;
        if (null != data)
        {
            p = data.getIntExtra("position", 0);
        }
        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case 11:
                    oid = mOrgInfoList.get(p).getOid();
                    tvCompany.setText(mOrgInfoList.get(p).getOname());

                    break;

                case 12:
                    questionTypeId = mConsultTypeInfoList.get(p).getId();
                    tvType.setText(mConsultTypeInfoList.get(p).getTypeName());
                    oid = "";
                    tvCompany.setText("");
                    getOrg(questionTypeId);
                    break;

            }
        }

    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (ADD_QUESTION.equals(action))
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
        else if (GET_ORG.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(GET_ORG_SUCCESS, obj));
            }

            else
            {
                //mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }
}
