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

import com.bangfu.R;
import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.HolderListHandler;
import com.bangfu.json.ResultHandler;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/21 15:04
 * 邮箱：wangxianyun1@163.com
 * 描述：大病救助信息录入
 */
public class InputDiseaseActivity extends BaseActivity implements IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_submit)
    TextView       tvSubmit;

    @BindView(R.id.et_disease)
    EditText       etDisease;
    @BindView(R.id.et_hospital)
    EditText       etHospital;
    @BindView(R.id.et_total)
    EditText       etTotal;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_userName)
    TextView       tvUserName;
    @BindView(R.id.et_desc)
    EditText       etDesc;
    @BindView(R.id.btn_submit)
    Button         btnSubmit;

    private String pid;
    private String pname;

    private String         type;//1:新增 2：修改
private     HtyPresentInfo mHtyPresentInfo;
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
                    ToastUtil.show(InputDiseaseActivity.this,"操作成功");
                    finish();

                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(InputDiseaseActivity.this, msg.obj.toString());
                    break;


            }
        }
    };
    @Override
    protected void initData()
    {
        mHtyPresentInfo = (HtyPresentInfo)getIntent().getSerializableExtra("HtyPresentInfo");
        type = getIntent().getStringExtra("TYPE");
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_input_disease);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        if("1".equals(type))
        {
            tvTitle.setText("大病救助信息录入");
        }

        else
        {
            tvTitle.setText("大病救助信息修改");
        }

        if(null != mHtyPresentInfo)
        {
            pid = mHtyPresentInfo.getPid();
            tvUserName.setText(mHtyPresentInfo.getPname());
            etDisease.setText(mHtyPresentInfo.getIllnessName());
            etHospital.setText(mHtyPresentInfo.getHospital());
            etTotal.setText(mHtyPresentInfo.getCost());
            etDesc.setText(mHtyPresentInfo.getDescb());
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
                startActivityForResult(new Intent(InputDiseaseActivity.this,HolderListActivity.class),1);
                break;
            case R.id.btn_submit:

                String illnessName = etDisease.getText().toString();
                String hospital =etHospital.getText().toString();
                String cost = etTotal.getText().toString();
                String desc = etDesc.getText().toString();

                if(StringUtils.stringIsEmpty(pid))
                {
                    ToastUtil.show(InputDiseaseActivity.this,"请选择患者姓名");
                    return;
                }

                if(StringUtils.stringIsEmpty(illnessName))
                {
                    ToastUtil.show(InputDiseaseActivity.this,"请输入大病名称");
                    return;
                }
                if(StringUtils.stringIsEmpty(hospital))
                {
                    ToastUtil.show(InputDiseaseActivity.this,"请输入就诊医院");
                    return;
                }
                if(!StringUtils.isNumber(cost))
                {
                    ToastUtil.show(InputDiseaseActivity.this,"请输入正确救助金额");
                    return;
                }
                Map<String, String> valuePairs = new HashMap<>();
                valuePairs.put("pid",pid);
                valuePairs.put("illnessName",illnessName);
                valuePairs.put("hospital",hospital);
                valuePairs.put("cost",cost);
                valuePairs.put("descb",desc);

                if("1".equals(type))
                {
                    valuePairs.put("flaged","a");
                }
                else
                {
                    valuePairs.put("flaged","m");
                    valuePairs.put("id",mHtyPresentInfo.getId());
                }

                DataRequest.instance().request(InputDiseaseActivity.this, Urls.getAddHtyAssistInfoUrl(), this, HttpRequest.POST, ADD_DATA, valuePairs,
                        new ResultHandler());
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK&& requestCode==1)
        {
            if(null !=data)
            {
                pid = data.getStringExtra("PID");
                pname=data.getStringExtra("PNAME");
                tvUserName.setText(pname);
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
