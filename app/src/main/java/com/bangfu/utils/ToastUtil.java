package com.bangfu.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangfu.R;


/**
 * @ClassName: ToasUtil
 * @Description: Toast相关的工具类
 * @date 2011-7-29 上午01:09:04
 */

public class ToastUtil
{
    public static void show(Context context, int resourceId)
    {

        if (null != context)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_toast, null);
            TextView textView = (TextView) view.findViewById(R.id.text);

            textView.setText(context.getString(resourceId));

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.BOTTOM, 0, APPUtils.dip2px(context, 65));
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
    }

    public static void show(Context context, String text)
    {
        if (null != context)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_toast, null);
            TextView textView = (TextView) view.findViewById(R.id.text);

            textView.setText(text);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.BOTTOM, 0, APPUtils.dip2px(context, 65));
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
    }

    /**
     * 功能:在屏幕的中间显示Toast
     *
     * @author LiuHan
     */
    // public static void centerShow(Context context, String info) {
    // Toast toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
    // toast.setGravity(Gravity.CENTER, 0, 0);
    // toast.show();
    // }

}
