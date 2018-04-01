package com.eternallove.mdmp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: eternallove
 * @date: 2017/6/12 22:08
 */

public class NotifyFragment extends BaseFragment {
    private View rootView;
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public void onAttach(Context context) {// API>=23才会调用
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notify, container, false);
        InitView();
        return rootView;
    }


    private void InitView() {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        //初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText("通知"));
        mTabLayout.addTab(mTabLayout.newTab().setText("赞与感谢"));
        mTabLayout.addTab(mTabLayout.newTab().setText("关注"));

        List<String> titles = new ArrayList<>();
        titles.add("通知");
        titles.add("赞与感谢");
        titles.add("关注");

        //初始化ViewPager的数据集
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new NotifyChildFragment());
//        fragments.add(new NotifyChildFragment());
//        fragments.add(new NotifyChildFragment());

        //创建ViewPager的adapter
//        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
//        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setTabsFromPagerAdapter(adapter);

    }


}
