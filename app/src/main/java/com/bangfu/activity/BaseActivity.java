package com.bangfu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.bangfu.R;
import com.bangfu.utils.LogUtil;
import com.bangfu.utils.StatusBarUtil;
import com.bangfu.widget.CustomProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：王先云 on 2016/7/1 10:23
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public abstract class BaseActivity extends AppCompatActivity  implements View.OnClickListener
{

    Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initData();
        initViews(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);;//注册黄油刀
        initEvent();
        initViewData();
    }

    /**
     * 设置状态栏文字高亮显示
     *
     * @param isDark
     */
    public void setStausBarTextDeep(boolean isDark)
    {
        if (isDark == true)
        {
            StatusBarUtil.setStatusBarTextColor(this, true);
        }
        else
        {
            StatusBarUtil.setStatusBarTextColor(this, false);
        }

    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */

    public void setStatusColor(int color)
    {

        //getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.windowTitleBackground));
        StatusBarUtil.setColor(this, color);

    }

    /**
     * 设置全屏模式透明
     */
    public void setTranslucentStatus()
    {
        StatusBarUtil.setTranslucentStatus(this, true);
    }

    /**
     * 设置全屏模式透明内容padding
     */
    public void setTranslucentStatusPadding()
    {
        StatusBarUtil.setTransparent(this);
    }

    /**
     * 从intent中获取数据
     */
    protected abstract void initData();

    /**
     * 存放从xml中获取ui,例如 findViewById
     */
    protected abstract void initViews(Bundle savedInstanceState);


    /**
     * 初始化页面UI事件,例如 setOnClickListener
     */
    protected abstract void initEvent();

    /**
     * 存放刷新页面的代码和初始化数据
     */
    protected abstract void initViewData();

    private String mPageName = getClass().getSimpleName();

    @Override
    protected void onPause()
    {
        super.onPause();
        //SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
        //        MobclickAgent.onPageEnd(mPageName);
        //        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
        //        MobclickAgent.onPageStart(mPageName);
        //        MobclickAgent.onResume(this);
    }
    @Override
    public void onClick(View v)
    {
    }

    public static final int  MIN_CLICK_DELAY_TIME = 1000;
    private             long lastClickTime        = 0;


    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION  = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    /**
     * 请求权限
     * <p/>
     * 如果权限被拒绝过，则提示用户需要权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected void requestPermission(final String permission, String rationale, final int requestCode)
    {
        if (shouldShowRequestPermissionRationale(permission))
        {
            showAlertDialog(getString(R.string.permission_title_rationale), rationale,
                    new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    }, getString(R.string.label_ok), null, getString(R.string.label_cancel));
        }
        else
        {
            requestPermissions(new String[]{permission}, requestCode);
        }
    }


    /**
     * 显示指定标题和信息的对话框
     *
     * @param title                         - 标题
     * @param message                       - 信息
     * @param onPositiveButtonClickListener - 肯定按钮监听
     * @param positiveText                  - 肯定按钮信息
     * @param onNegativeButtonClickListener - 否定按钮监听
     * @param negativeText                  - 否定按钮信息
     */
    protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                   @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                   @NonNull String positiveText,
                                   @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                   @NonNull String negativeText)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
        builder.show();
    }


    private CustomProgressDialog mProgressDialog = null;

    /**
     * @return void
     * @throws [异常类型] [异常说明]
     * @see
     */
    protected void showProgressDialog()
    {

        if (isFinishing())
        {
            return;
        }
        LogUtil.e("TAG", "showProgressDialog");
        if (mProgressDialog == null)
        {
            mProgressDialog = CustomProgressDialog.createDialog(this);
        }
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage("");
        mProgressDialog.show();
    }

    protected void hideProgressDialog()
    {
        if (isFinishing())
        {
            return;
        }
        LogUtil.e("TAG", "hideProgressDialog");
        if (mProgressDialog != null)
        {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
