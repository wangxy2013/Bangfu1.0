package com.bangfu.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import static android.view.View.OnClickListener;

public class ViewUtil
{

    private View mView;

    public ViewUtil(View view) {
        this.mView = view;
    }

    public <T extends View> T findView(int id) {
        return (T) mView.findViewById(id);
    }

    public <T extends View> T setOnClick(int id, OnClickListener l) {
        T view = (T) mView.findViewById(id);
        view.setOnClickListener(l);
        return view;
    }

    public static <T> T findView(View v, int id) {
        return (T) v.findViewById(id);
    }

    @SuppressWarnings("deprecation")
    public static void setBackground(View view, Drawable d){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN){
            view.setBackground(d);
        }else{
            view.setBackgroundDrawable(d);

        }
    }
}
