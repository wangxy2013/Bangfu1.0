package com.bangfu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.bangfu.fragment.BaseFragment;

import java.util.List;

/**
 * 作者：王先云 on 2016/9/22 11:44
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class ViewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter
{
    private List<BaseFragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragments.get(position).getTitle();
    }
}

