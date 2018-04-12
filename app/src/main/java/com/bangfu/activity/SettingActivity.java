package com.bangfu.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.UserInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.UserInfoHandler;
import com.bangfu.utils.APPUtils;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.TypeUtils;
import com.bangfu.utils.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/29 09:13
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class SettingActivity extends BaseActivity implements IRequestListener
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_userName)
    TextView       tvUserName;
    @BindView(R.id.tv_post)
    TextView       tvPost;
    @BindView(R.id.ll_post)
    LinearLayout   llPost;
    @BindView(R.id.tv_dname)
    TextView       tvDname;
    @BindView(R.id.ll_dname)
    LinearLayout   llDname;
    @BindView(R.id.tv_xzbm)
    TextView       tvXzbm;
    @BindView(R.id.ll_xzbm)
    LinearLayout   llXzbm;
    @BindView(R.id.tv_company)
    TextView       tvCompany;
    @BindView(R.id.ll_company)
    LinearLayout   llCompany;
    @BindView(R.id.tv_phone)
    TextView       tvPhone;
    @BindView(R.id.id_version)
    TextView       idVersion;
    @BindView(R.id.line_dname)
    View           lineDname;


    private static final int    REQUEST_LOGIN_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL          = 0x02;
    private static final String GET_USERINFO          = "GET_USERINFO";
    @BindView(R.id.ll_mid)
    LinearLayout llMid;
    @BindView(R.id.btn_logout)
    Button       btnLogout;


    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_LOGIN_SUCCESS:

                    UserInfoHandler mUserInfoHandler = (UserInfoHandler) msg.obj;
                    UserInfo mUserInfo = mUserInfoHandler.getUserInfo();


                    if (null != mUserInfo)
                    {
                        tvUserName.setText(mUserInfo.getPname());
                        tvPost.setText(mUserInfo.getDuty());
                        tvCompany.setText(TypeUtils.getOrgName(mUserInfo.getOid()));
                        tvDname.setText(mUserInfo.getDname());
                        tvXzbm.setText(mUserInfo.getDname());
                        tvPhone.setText(mUserInfo.getPhone());
                        if ("0".equals(mUserInfo.getIsHolder()))
                        {
                            llDname.setVisibility(View.GONE);
                            lineDname.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            llMid.setVisibility(View.GONE);
                        }
                    }
                    break;

                case REQUEST_FAIL:
                    ToastUtil.show(SettingActivity.this, msg.obj.toString());
                    break;


            }
        }
    };


    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_setting);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("设置");
        idVersion.setText("当前版本:V" + APPUtils.getVersionName(this));

        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("pid", ConfigManager.instance().getUserID());
        DataRequest.instance().request(SettingActivity.this, Urls.getCustomerInfoByPidUrl(), this, HttpRequest.POST, GET_USERINFO, valuePairs,
                new UserInfoHandler());
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

            case R.id.btn_logout:
                ConfigManager.instance().setUserId("");
                setResult(-1);
                finish();
                break;
        }
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (GET_USERINFO.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_LOGIN_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
