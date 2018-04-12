package com.bangfu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bangfu.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：王先云 on 2017/12/7 12:48
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class SjsbFragment extends BaseFragment
{

    private View rootView = null;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_sjsb, null);
            unbinder = ButterKnife.bind(this, rootView);
            initData();
            initViews();
            initViewData();
            initEvent();
        }
        // 缓存的rootView需要判断是否已经被加过parent
        // 如果有parent需要从parent删除，否则会发生这个rootView已经有parent的错误
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews()
    {

    }

    @Override
    protected void initEvent()
    {

    }

    @Override
    protected void initViewData()
    {

    }
}
