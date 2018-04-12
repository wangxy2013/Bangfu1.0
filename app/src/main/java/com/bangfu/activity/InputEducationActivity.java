package com.bangfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.BfApplication;
import com.bangfu.R;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.EduAssistInfo;
import com.bangfu.entity.EduPhaseTypeInfo;
import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
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
 * 作者：王先云 on 2017/11/21 15:04
 * 邮箱：wangxianyun1@163.com
 * 描述：教育救助信息录入
 */
public class InputEducationActivity extends BaseActivity implements IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_submit)
    TextView       tvSubmit;

    @BindView(R.id.et_school)
    EditText etSchool;
    @BindView(R.id.et_className)
    EditText etClassName;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_phaseName)
    TextView tvPhaseName;
    @BindView(R.id.et_total)
    EditText etTotal;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.btn_submit)
    Button   btnSubmit;
    private ArrayList<EduPhaseTypeInfo> mEduPhaseTypeInfoList = new ArrayList<>();
    private String pid;
    private String pname;
    private String eid;
    private String        type;//1:新增 2：修改
    private EduAssistInfo mEduAssistInfo;
    private static final int    REQUEST_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL    = 0x02;
    private static final String ADD_DATA        = "ADD_DATA";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_SUCCESS:
                    ToastUtil.show(InputEducationActivity.this,"操作成功");
                    finish();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(InputEducationActivity.this, msg.obj.toString());
                    break;


            }
        }
    };
    @Override
    protected void initData()
    {
        mEduAssistInfo = (EduAssistInfo)getIntent().getSerializableExtra("EduAssistInfo");
        type = getIntent().getStringExtra("TYPE");
        mEduPhaseTypeInfoList= BfApplication.getInstance().getEduPhaseTypeInfoList();


    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_input_education);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvPhaseName.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {

        if("1".equals(type))
        {
            tvTitle.setText("教育救助信息录入");
        }
        else
        {
            tvTitle.setText("教育救助信息修改");
        }
        if(null !=mEduAssistInfo)
        {
            pid = mEduAssistInfo.getPid();
            tvUserName.setText(mEduAssistInfo.getPname());
            eid = mEduAssistInfo.getEid();
            tvPhaseName.setText(mEduAssistInfo.getPhaseName());
            etClassName.setText(mEduAssistInfo.getEclassName());
            etSchool.setText(mEduAssistInfo.getEschool());
            etTotal.setText(mEduAssistInfo.getEcost());
            etDesc.setText(mEduAssistInfo.getDescb());
        }

        if(!"0".equals(ConfigManager.instance().getHolder()))
        {
            tvUserName.setEnabled(false);

        }

//        tvSubmit.setText("保存");
//        tvSubmit.setTextSize(14);
//        tvSubmit.setVisibility(View.VISIBLE);
//        tvSubmit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        tvSubmit.getPaint().setAntiAlias(true);//抗锯齿
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
            case R.id.tv_userName:
                startActivityForResult(new Intent(InputEducationActivity.this,HolderListActivity.class),10);
                break;

            case R.id.tv_phaseName:
                ArrayList<String> eduPhaseType = TypeUtils.getEduPhaseTypeList(mEduPhaseTypeInfoList);
                Intent mIntent = new Intent(InputEducationActivity.this, TypeActivity.class);
                mIntent.putStringArrayListExtra("LIST", eduPhaseType);
                mIntent.putExtra("TITLE", "学段");
                startActivityForResult(mIntent, 11);

                break;
            case R.id.btn_submit:
                String  school = etSchool.getText().toString();
                String className = etClassName.getText().toString();
                String total =etTotal.getText().toString();
                String desc = etDesc.getText().toString();

                if(StringUtils.stringIsEmpty(pid))
                {
                    ToastUtil.show(InputEducationActivity.this,"请选择上学者");
                    return;
                }

                if(StringUtils.stringIsEmpty(eid))
                {
                    ToastUtil.show(InputEducationActivity.this,"请选择学段");
                    return;
                }

                if(StringUtils.stringIsEmpty(school))
                {
                    ToastUtil.show(InputEducationActivity.this,"请输入学校名称");
                    return;
                }
                if(StringUtils.stringIsEmpty(className))
                {
                    ToastUtil.show(InputEducationActivity.this,"请输入所在班级");
                    return;
                }
if(!StringUtils.isNumber(total))
{
    ToastUtil.show(InputEducationActivity.this,"请输入正确救助金额");
    return;
}

                Map<String, String> valuePairs = new HashMap<>();
                valuePairs.put("pid",pid);
                valuePairs.put("eid",eid);
                valuePairs.put("eschool",school);
                valuePairs.put("eclassName",className);
                valuePairs.put("ecost",total);
                valuePairs.put("descb",desc);

                if("1".equals(type))
                {
                    valuePairs.put("flaged","a");
                }
                else
                {
                    valuePairs.put("flaged","m");
                    valuePairs.put("id",mEduAssistInfo.getId());
                }

                DataRequest.instance().request(InputEducationActivity.this, Urls.getAddEduAssistInfoUrl(), this, HttpRequest.POST, ADD_DATA, valuePairs,
                        new ResultHandler());

                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            if(null !=data&& requestCode==10)
            {
                pid = data.getStringExtra("PID");
                pname=data.getStringExtra("PNAME");
                tvUserName.setText(pname);
            }
            else if(null !=data&& requestCode==11)
            {
                int p = data.getIntExtra("position", 0);
                eid = mEduPhaseTypeInfoList.get(p).getEid();
                tvPhaseName.setText(mEduPhaseTypeInfoList.get(p).getPhase());


            }
        }
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (ADD_DATA.equals(action))
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
