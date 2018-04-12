package com.bangfu.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.adapter.CategoryAdapter;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.listener.MyOnClickListener;
import com.bangfu.listener.OnClickListenerUtils;
import com.bangfu.widget.NoScrollListView;

import java.util.List;

/**
 * 作者：王先云 on 2017/12/1 10:07
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class DialogUtils
{
    //性别选择
    public static Dialog showSexDialog(final Context mContext, final OnClickListenerUtils.OnSubmitBtnClickListerner listerner)
    {
        final Dialog dialog = new Dialog(mContext, R.style.DialogStyle);
        dialog.setCancelable(false);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sex, null);
        dialog.setContentView(view);

        LinearLayout mManLayout = (LinearLayout) view.findViewById(R.id.ll_man);
        LinearLayout mWomenLayout = (LinearLayout) view.findViewById(R.id.ll_women);

        mManLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listerner.onSubmit("0");
                dialog.dismiss();
            }
        });

        mWomenLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listerner.onSubmit("1");
                dialog.dismiss();
            }
        });
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = dm.widthPixels * 3 / 4;
        mWindow.setGravity(Gravity.CENTER);
        //        mWindow.setWindowAnimations(R.style.dialogAnim);
        mWindow.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }


    public static Dialog showCategoryDialog(Context mContext, List<String> list, final MyItemClickListener mMyItemClickListener)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialogNoAnimation);
        dialog.setCancelable(false);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_type, null);
        dialog.setContentView(view);

        NoScrollListView mListView = (NoScrollListView) view.findViewById(R.id.lv_type);
        CategoryAdapter mCategoryAdapter = new CategoryAdapter(mContext, list);
        mListView.setAdapter(mCategoryAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                mMyItemClickListener.onItemClick(view, position);
                dialog.dismiss();
            }
        });

        //Dialog部分
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.75);
        mWindow.setGravity(Gravity.CENTER);
        mWindow.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }

    public static void showVersionUpdateDialog(Context mContext, String content, final MyOnClickListener.OnSubmitListener listener)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialogNoAnimation);
        dialog.setCancelable(false);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_version, null);
        dialog.setContentView(view);
        TextView mContent = (TextView) view.findViewById(R.id.tv_content);
        Button mSubmitBtn = (Button) view.findViewById(R.id.btn_submit);
        Button mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);

        mContent.setText(content);
        mSubmitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                dialog.dismiss();
                listener.onSubmit("1");
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                listener.onSubmit("2");
            }
        });
        //Dialog部分
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.75);
        mWindow.setGravity(Gravity.CENTER);
        mWindow.setAttributes(lp);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showLunarDialog(Context mContext, String suit, String taboo)
    {
        final Dialog dialog = new Dialog(mContext, R.style.dialogNoAnimation);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_lunar, null);
        dialog.setContentView(view);
        TextView mSuitTv = (TextView) view.findViewById(R.id.tv_suit);
        TextView mTabooTv = (TextView) view.findViewById(R.id.tv_taboo);

        mSuitTv.setText(Html.fromHtml(suit));
        mTabooTv.setText(Html.fromHtml(taboo));
        //Dialog部分
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.75);
        mWindow.setGravity(Gravity.CENTER);
        mWindow.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.show();
    }

}
