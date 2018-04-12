package com.bangfu.activity;

import android.content.Context;
import android.os.Handler;

/**
 * 作者：王先云 on 2016/9/5 16:31
 * 邮箱：wangxianyun1@163.com
 * 描述：为了防止内存泄漏，定义外部类，防止内部类对外部类的引用
 */
public class BaseHandler extends Handler
{
    Context context;

    public BaseHandler(Context context) {
        this.context = context;
    }
};