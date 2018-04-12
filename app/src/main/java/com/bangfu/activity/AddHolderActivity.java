package com.bangfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.BfApplication;
import com.bangfu.R;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.HealthyTypeInfo;
import com.bangfu.entity.OrgInfo;
import com.bangfu.entity.RelationTypeInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.entity.WorkTypeInfo;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/29 11:32
 * 邮箱：wangxianyun1@163.com
 * 描述：添加户主和农户信息
 */
public class AddHolderActivity extends BaseActivity implements IRequestListener
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.et_userName)
    EditText       etUserName;
    @BindView(R.id.et_address)
    EditText       etAddress;
    @BindView(R.id.et_phone)
    EditText       etPhone;
    @BindView(R.id.btn_submit)
    Button         btnSubmit;

    @BindView(R.id.tv_sex)
    TextView     tvSex;
    @BindView(R.id.et_userCid)
    EditText     etUserCid;
    @BindView(R.id.tv_marriedType)
    TextView     tvMarriedType;
    @BindView(R.id.tv_workType)
    TextView     tvWorkType;
    @BindView(R.id.tv_healthyType)
    TextView     tvHealthyType;
    @BindView(R.id.tv_did)
    TextView     tvDid;
    @BindView(R.id.tv_relationType)
    TextView     tvRelationType;
    @BindView(R.id.ll_relationType)
    LinearLayout llRelationType;
    @BindView(R.id.line_relationType)
    View         lineRelationType;



    private ArrayList<WorkTypeInfo>     mWorkTypeInfoList     = new ArrayList<>();
    private ArrayList<RelationTypeInfo> mRelationTypeInfoList = new ArrayList<>();
    private ArrayList<OrgInfo>          mOrgInfoList          = new ArrayList<>();
    private ArrayList<HealthyTypeInfo>  mHealthyTypeInfoList  = new ArrayList<>();
    private ArrayList<DeptInfo>         mDeptInfoList         = new ArrayList<>();
    private List<String> marriedTypeList;

    private String sexType;
    private String marriedType;//婚姻状况。0-未婚	1-已婚	2-未知',
    private String workType;
    private String healthyType;
    private String deptId;
    private String relationType;

    private String isHolder="0";
    ;//如relationType=1(户主)，则该字段无意义，可空；否则表示户主pid，不可空

    private String type;//1:新增戶主信息 2：修改戶主信息 3：新增家庭成員信息 4：修改家庭成員信息

    private UserInfo mUserInfo;

    private static final int    REQUEST_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL          = 0x02;
    private static final String ADD_HOLDER            = "add_holder";

    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case REQUEST_SUCCESS:
                    ToastUtil.show(AddHolderActivity.this, "操作成功");
                    finish();
                    break;
                case REQUEST_FAIL:
                    ToastUtil.show(AddHolderActivity.this, msg.obj.toString());
                    break;


            }
        }
    };


    @Override
    protected void initData()
    {

        relationType = getIntent().getStringExtra("relationType");

        type=getIntent().getStringExtra("TYPE");
        if("3".equals(type))
        {
            isHolder = getIntent().getStringExtra("isHolder");
        }
        mUserInfo = (UserInfo)getIntent().getSerializableExtra("UserInfo");
        mWorkTypeInfoList = BfApplication.getInstance().getWorkTypeInfoList();
        mRelationTypeInfoList = BfApplication.getInstance().getRelationTypeInfoList();
        mOrgInfoList = BfApplication.getInstance().getOrgInfoList();
        mHealthyTypeInfoList = BfApplication.getInstance().getHealthyTypeInfoList();
        mDeptInfoList = BfApplication.getInstance().getDeptInfoList();

        marriedTypeList = Arrays.asList(getResources().getStringArray(R.array.marriedType));
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_add_family);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        tvMarriedType.setOnClickListener(this);
        tvWorkType.setOnClickListener(this);
        tvHealthyType.setOnClickListener(this);
        tvDid.setOnClickListener(this);
        tvRelationType.setOnClickListener(this);


        if(!"0".equals(ConfigManager.instance().getHolder()))
        {
            etUserName.setEnabled(false);
            tvSex.setEnabled(false);
            etUserCid.setEnabled(false);
            tvHealthyType.setEnabled(false);
            tvDid.setEnabled(false);
            tvRelationType.setEnabled(false);
        }


    }

    @Override
    protected void initViewData()
    {


        if(null != mUserInfo)
        {
            isHolder = mUserInfo.getFid();
            etUserName.setText(mUserInfo.getPname());
            sexType = mUserInfo.getSex();
            tvSex.setText(mUserInfo.getSexName());
            etUserCid.setText(mUserInfo.getCid());
            marriedType = mUserInfo.getMarriedType();
            tvMarriedType.setText(mUserInfo.getMarriedTypeName());
            workType = mUserInfo.getWorkType();
            tvWorkType.setText(mUserInfo.getWorkTypeName());
            healthyType = mUserInfo.getHealthyType();
            tvHealthyType.setText(mUserInfo.getHealthyTypeName());
            relationType = mUserInfo.getRelationType();
            tvRelationType.setText(mUserInfo.getRelationTypeName());
            deptId = mUserInfo.getDid();
            tvDid.setText(mUserInfo.getDname());
            etAddress.setText(mUserInfo.getFaddr());
            etPhone.setText(mUserInfo.getPhone());


        }

        if ("1".equals(type))
        {
            tvTitle.setText("户主信息添加");
            llRelationType.setVisibility(View.GONE);
            lineRelationType.setVisibility(View.GONE);
        }
        else    if ("2".equals(type))
        {
            tvTitle.setText("户主信息修改");
            llRelationType.setVisibility(View.GONE);
            lineRelationType.setVisibility(View.GONE);
        }

        else if ("3".equals(type))
        {
            tvTitle.setText("家庭成员信息添加");
            llRelationType.setVisibility(View.VISIBLE);
            lineRelationType.setVisibility(View.VISIBLE);
        }
        else if ("4".equals(type))
        {
            tvTitle.setText("家庭成员信息修改");
            llRelationType.setVisibility(View.VISIBLE);
            lineRelationType.setVisibility(View.VISIBLE);
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

                String mName = etUserName.getText().toString();
                String mAddress = etAddress.getText().toString();
                String mPhone = etPhone.getText().toString();
                String cid = etUserCid.getText().toString();


                if (StringUtils.stringIsEmpty(mName))
                {
                    ToastUtil.show(this, "请输入户主姓名");
                    return;
                }
                if (StringUtils.stringIsEmpty(sexType))
                {
                    ToastUtil.show(this, "请选择性别");
                    return;
                }
                if (StringUtils.stringIsEmpty(cid))
                {
                    ToastUtil.show(this, "请输入身份证号");
                    return;
                }
                if (StringUtils.stringIsEmpty(marriedType))
                {
                    ToastUtil.show(this, "请选择婚姻状况");
                    return;
                }
                if (StringUtils.stringIsEmpty(workType))
                {
                    ToastUtil.show(this, "请选择工作状况");
                    return;
                }
                if (StringUtils.stringIsEmpty(healthyType))
                {
                    ToastUtil.show(this, "请选择健康状况");
                    return;
                }
                if (StringUtils.stringIsEmpty(deptId))
                {
                    ToastUtil.show(this, "请选择所属行政区");
                    return;
                }
                if (StringUtils.stringIsEmpty(relationType))
                {
                    ToastUtil.show(this, "请选择与户主关系");
                    return;
                }

//                if (StringUtils.stringIsEmpty(mAddress))
//                {
//                    ToastUtil.show(this, "请输入家庭地址");
//                    return;
//                }
                if (StringUtils.stringIsEmpty(mPhone))
                {
                    ToastUtil.show(this, "请输入联系方式");
                    return;
                }
                Map<String, String> valuePairs = new HashMap<>();

                if(null !=mUserInfo)
                {
                    valuePairs.put("flaged", "m");
                }
                else
                {
                    valuePairs.put("flaged", "a");
                }

                valuePairs.put("did", deptId);
                valuePairs.put("pname", mName);
                valuePairs.put("sex", sexType);
                valuePairs.put("relationType", relationType);
                valuePairs.put("cid", cid);
                valuePairs.put("workType", workType);
                valuePairs.put("marriedType", marriedType);
                valuePairs.put("phone", mPhone);
                valuePairs.put("healthyType", healthyType);
                valuePairs.put("isHolder", isHolder);
                valuePairs.put("faddr", mAddress);

                if("2".equals(type) || "4".equals(type))
                {
                    valuePairs.put("pid", mUserInfo.getPid());
                }
                DataRequest.instance().request(AddHolderActivity.this, Urls.getAddCustomerUrl(), this, HttpRequest.POST, ADD_HOLDER, valuePairs,
                        new ResultHandler());

                break;

            case R.id.tv_sex:
                //                DialogUtils.showSexDialog(AddHolderActivity.this, new OnClickListenerUtils.OnSubmitBtnClickListerner()
                //                {
                //                    @Override
                //                    public void onSubmit(String content)
                //                    {
                //
                //                    }
                //                });
                List<String> sexList = Arrays.asList(getResources().getStringArray(R.array.sexType));
                Intent mIntent1 = new Intent(AddHolderActivity.this, TypeActivity.class);
                mIntent1.putStringArrayListExtra("LIST", TypeUtils.getTypeNameList(sexList));
                mIntent1.putExtra("TITLE", "性别");
                startActivityForResult(mIntent1, 1);
                break;

            case R.id.tv_marriedType:

                List<String> marriedTypeList = Arrays.asList(getResources().getStringArray(R.array.marriedType));

                Intent mIntent2 = new Intent(AddHolderActivity.this, TypeActivity.class);
                mIntent2.putStringArrayListExtra("LIST", TypeUtils.getTypeNameList(marriedTypeList));
                mIntent2.putExtra("TITLE", "婚姻状况");
                startActivityForResult(mIntent2, 2);
                //                DialogUtils.showCategoryDialog(this, categoryList, new MyItemClickListener()
                //                {
                //                    @Override
                //                    public void onItemClick(View view, int postion)
                //                    {
                //                        marriedType = postion + "";
                //                        tvMarriedType.setText(categoryList.get(postion));
                //                    }
                //
                //                });

                break;

            case R.id.tv_workType:

                ArrayList<String> workTypeList = TypeUtils.getWorkTypeList(mWorkTypeInfoList);

                Intent mIntent3 = new Intent(AddHolderActivity.this, TypeActivity.class);
                mIntent3.putStringArrayListExtra("LIST", workTypeList);
                mIntent3.putExtra("TITLE", "工作状况");
                startActivityForResult(mIntent3, 3);
                //                DialogUtils.showCategoryDialog(this, workTypeList, new MyItemClickListener()
                //                {
                //                    @Override
                //                    public void onItemClick(View view, int postion)
                //                    {
                //                        workType = mWorkTypeInfoList.get(postion).getId();
                //                        tvWorkType.setText(workTypeList.get(postion));
                //                    }
                //
                //                });
                break;

            case R.id.tv_healthyType:

                ArrayList<String> healthyTypeList = TypeUtils.getHealthyTypeList(mHealthyTypeInfoList);

                Intent mIntent4 = new Intent(AddHolderActivity.this, TypeActivity.class);
                mIntent4.putStringArrayListExtra("LIST", healthyTypeList);
                mIntent4.putExtra("TITLE", "健康状况");
                startActivityForResult(mIntent4, 4);

                //                DialogUtils.showCategoryDialog(this, healthyTypeList, new MyItemClickListener()
                //                {
                //                    @Override
                //                    public void onItemClick(View view, int postion)
                //                    {
                //                        healthyType = mHealthyTypeInfoList.get(postion).getId();
                //                        tvHealthyType.setText(healthyTypeList.get(postion));
                //                    }
                //
                //                });
                break;

            case R.id.tv_did:

                ArrayList<String> deptNameList = TypeUtils.getDeptNameList(mDeptInfoList);

                Intent mIntent5 = new Intent(AddHolderActivity.this, TypeActivity.class);
                mIntent5.putStringArrayListExtra("LIST", deptNameList);
                mIntent5.putExtra("TITLE", "所属行政区");
                startActivityForResult(mIntent5, 5);
                //                DialogUtils.showCategoryDialog(this, deptNameList, new MyItemClickListener()
                //                {
                //                    @Override
                //                    public void onItemClick(View view, int postion)
                //                    {
                //                        deptId = mDeptInfoList.get(postion).getDid();
                //                        tvDid.setText(mDeptInfoList.get(postion).getDname());
                //                    }
                //
                //                });


                break;

            case R.id.tv_relationType:
                ArrayList<String> relationTypeList = TypeUtils.getRelationTypeList(mRelationTypeInfoList);
                Intent mIntent6 = new Intent(AddHolderActivity.this, TypeActivity.class);
                mIntent6.putStringArrayListExtra("LIST", relationTypeList);
                mIntent6.putExtra("TITLE", "与户主关系");
                startActivityForResult(mIntent6, 6);


                //                final List<String> relationTypeList = TypeUtils.getRelationTypeList(mRelationTypeInfoList);
                //                DialogUtils.showCategoryDialog(this, relationTypeList, new MyItemClickListener()
                //                {
                //                    @Override
                //                    public void onItemClick(View view, int postion)
                //                    {
                //                        relationType = mRelationTypeInfoList.get(postion).getId();
                //                        tvRelationType.setText(mRelationTypeInfoList.get(postion).getType_name());
                //                    }
                //                });

                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        int p=0;
        if(null !=data)
        {
             p = data.getIntExtra("position", 0);
        }
        if (resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case 1:
                    if (p == 0)
                    {
                        sexType = "M";
                        tvSex.setText("男");
                    }
                    else
                    {
                        sexType = "F";
                        tvSex.setText("女");
                    }

                    break;

                case 2:
                    marriedType = p + "";
                    tvMarriedType.setText(marriedTypeList.get(p));
                    break;

                case 3:
                    workType = mWorkTypeInfoList.get(p).getId();
                    tvWorkType.setText(mWorkTypeInfoList.get(p).getType_name());
                    break;

                case 4:
                    healthyType = mHealthyTypeInfoList.get(p).getId();
                    tvHealthyType.setText(mHealthyTypeInfoList.get(p).getType_name());
                    break;

                case 5:
                    deptId = mDeptInfoList.get(p).getDid();
                    tvDid.setText(mDeptInfoList.get(p).getDname());
                    break;

                case 6:
                    relationType = mRelationTypeInfoList.get(p).getId();
                    tvRelationType.setText(mRelationTypeInfoList.get(p).getType_name());
                    break;


            }
        }

    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (ADD_HOLDER.equals(action))
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
