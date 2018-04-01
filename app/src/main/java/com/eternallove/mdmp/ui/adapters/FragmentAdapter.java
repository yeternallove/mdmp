package com.eternallove.mdmp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eternallove.mdmp.ui.base.BaseFragment;

import java.util.List;

/**
 * @description:
 * @author: eternallove
 * @date: 2017/6/12 22:08
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;
    private String[] mTitles;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
