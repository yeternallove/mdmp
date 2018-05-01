package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.department.DepartmentView;
import com.eternallove.mdmp.model.user.role.RoleView;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;
import com.eternallove.mdmp.ui.adapters.FragmentAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.fragments.UserFragments.UserAttributeFragment;
import com.eternallove.mdmp.ui.fragments.UserFragments.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, UserAttributeFragment.OnListFragmentInteractionListener {

    public static final int SCH_ADD_RESULT_CANCELED = 0;
    public static final int SCH_ADD_SAVE = 1;
    //子页（fragment）标记
    public static final int USER_ATTRIBUTE_1 = 1;
    public static final int USER_ATTRIBUTE_2 = 2;
    public static final int USER_ATTRIBUTE_3 = 3;

    private final String[] titles = {"用户列表", "角色列表", "视图权限", "部门列表"};
    private FragmentAdapter adapter;
    @BindView(R.id.sch_add_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //初始化TabLayout的title
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        //初始化ViewPager的数据集
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new UserFragment());
        fragments.add(UserAttributeFragment.newInstance(USER_ATTRIBUTE_1));
        fragments.add(UserAttributeFragment.newInstance(USER_ATTRIBUTE_2));
        fragments.add(UserAttributeFragment.newInstance(USER_ATTRIBUTE_3));

        //创建ViewPager的adapter
        adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onClickMore(UserAttribute userAttribute) {
        switch (getFragmentTag(userAttribute)) {
            case USER_ATTRIBUTE_1:

                break;
            case USER_ATTRIBUTE_2:
                break;
            case USER_ATTRIBUTE_3:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickDetails(UserAttribute userAttribute) {
        switch (getFragmentTag(userAttribute)) {
            case USER_ATTRIBUTE_1:

                break;
            case USER_ATTRIBUTE_2:
                break;
            case USER_ATTRIBUTE_3:
                break;
            default:
                break;
        }
    }

    private int getFragmentTag(UserAttribute userAttribute) {
        int tag;
        if (userAttribute instanceof RoleView) {
            tag = USER_ATTRIBUTE_1;
        } else if (userAttribute instanceof ViewRightView) {
            tag = USER_ATTRIBUTE_2;
        } else if (userAttribute instanceof DepartmentView) {
            tag = USER_ATTRIBUTE_3;
        } else {
            tag = 0;
        }
        return tag;
    }
}
