package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSettiongActivity extends BaseActivity implements View.OnClickListener {

    public final static String[] TAG_FRAGMENT = new String[]{"CHAT", "ACC", "SCH", "ME"};//标签

    private List<BaseFragment> fragments = new ArrayList<>();
    private BaseFragment fragmentNow;
    private FragmentManager fm;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_username)
    TextView tvUserName;


    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UserSettiongActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanage);
        ButterKnife.bind(this);
//        toolbar.setTitle("详情");
//        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditActivity.actionStart(UserSettiongActivity.this,"用户姓名");
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

}
