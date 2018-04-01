package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.test.DummyContent;
import com.eternallove.mdmp.ui.adapters.FragmentAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.fragments.ItemFragment;
import com.eternallove.mdmp.ui.fragments.NotifyChildFragment;
import com.eternallove.mdmp.ui.fragments.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements View.OnClickListener ,ItemFragment.OnListFragmentInteractionListener {

    public static final int SCH_ADD_RESULT_CANCELED = 0;
    public static final int SCH_ADD_SAVE = 1;

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
        String[] titles = {"用户列表", "角色列表", "视图权限", "部门列表"};
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        //初始化ViewPager的数据集
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new UserFragment());
        fragments.add(new NotifyChildFragment());
        fragments.add(new NotifyChildFragment());
        fragments.add(new ItemFragment());

        //创建ViewPager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
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
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this,item.toString(), Toast.LENGTH_SHORT).show();
    }
}
