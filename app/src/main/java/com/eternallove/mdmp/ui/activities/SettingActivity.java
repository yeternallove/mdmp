package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.fragments.MainFragments.MeFragment;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RegexUtil;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private AppManager appManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.um_logout)
    View umLogout;

    public static void actionStart(Activity context, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        context.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(view -> {
            setResult(MainActivity.RS_CANCEL);
            finish();
        });
        appManager = AppManager.getInstance(this);
        initView();
    }

    private void initView() {
        umLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.um_logout:
                appManager.logout();
                LoginActivity.actionStart(this);
                setResult(MainActivity.RS_EXIT);
                finish();
                break;
            default:
                break;
        }
    }

}
