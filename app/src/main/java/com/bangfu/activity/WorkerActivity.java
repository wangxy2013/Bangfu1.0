package com.bangfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.fragment.SjsbFragment;
import com.bangfu.utils.APPUtils;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.widget.RoundAngleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/16 09:36
 * 邮箱：wangxianyun1@163.com
 * 描述：
 */
public class WorkerActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout      rlBack;
    @BindView(R.id.tv_title)
    TextView            tvTitle;
    @BindView(R.id.tv_submit)
    TextView            tvSubmit;
    @BindView(R.id.rl_head)
    RelativeLayout      rlHead;
    @BindView(R.id.iv_userPhoto)
    RoundAngleImageView ivUserPhoto;
    @BindView(R.id.tv_userName)
    TextView            tvUserName;
    @BindView(R.id.rl_user)
    RelativeLayout      rlUser;
    @BindView(R.id.iv_bfgl)
    ImageView           ivBfgl;
    @BindView(R.id.rl_bfgl)
    RelativeLayout      rlBfgl;
    @BindView(R.id.iv_sjsb)
    ImageView           ivSjsb;
    @BindView(R.id.rl_sjsb)
    RelativeLayout      rlSjsb;
    @BindView(R.id.ll_layout1)
    LinearLayout        llLayout1;
    @BindView(R.id.iv_ygfp)
    ImageView           ivYgfp;
    @BindView(R.id.rl_ygfp)
    RelativeLayout      rlYgfp;
    @BindView(R.id.iv_zcgl)
    ImageView           ivZcgl;
    @BindView(R.id.rl_zcgl)
    RelativeLayout      rlZcgl;
    @BindView(R.id.iv_downLoad)
    ImageView           ivDownLoad;
    @BindView(R.id.iv_menu)
    ImageView           ivMenu;
    @BindView(R.id.iv_setting)
    ImageView           ivSetting;

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_worker);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
        rlBfgl.setOnClickListener(this);
        rlSjsb.setOnClickListener(this);
        rlYgfp.setOnClickListener(this);
        rlZcgl.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        ivDownLoad.setOnClickListener(this);
        ivSetting.setOnClickListener(this);

    }

    @Override
    protected void initViewData()
    {
        tvTitle.setText("扶贫管理");
        //tvSubmit.setText("●●●");
        //        tvSubmit.setTextSize(8);
        //        tvSubmit.setVisibility(View.VISIBLE);
        tvUserName.setText(ConfigManager.instance().getUserName());
        ivUserPhoto.setImageResource(R.drawable.icon_head);

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

            case R.id.rl_bfgl:
                startActivity(new Intent(WorkerActivity.this, WorkerManageActivity.class));
                break;
            case R.id.rl_sjsb:
                startActivity(new Intent(WorkerActivity.this, SjsbActivity.class));
                break;
            case R.id.rl_ygfp:
                APPUtils.startYgfp(WorkerActivity.this);
                break;
            case R.id.rl_zcgl:

                startActivity(new Intent(WorkerActivity.this, AssetManageActivity.class));

                break;

            case R.id.iv_downLoad:
                break;
            case R.id.iv_menu:
                break;

            case R.id.iv_setting:
                startActivityForResult(new Intent(WorkerActivity.this, SettingActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1)
        {
            finish();
        }
    }
}
