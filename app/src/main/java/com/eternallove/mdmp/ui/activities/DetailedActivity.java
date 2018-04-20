package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedActivity extends BaseActivity implements View.OnClickListener {

    public final static String[] TAG_FRAGMENT = new String[]{"CHAT", "ACC", "SCH", "ME"};//标签

    private List<BaseFragment> fragments = new ArrayList<>();
    private BaseFragment fragmentNow;
    private FragmentManager fm;
    @BindView(R.id.sch_add_toolbar)
    Toolbar toolbar;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DetailedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);
//        toolbar.setTitle("详情");
//        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

}
