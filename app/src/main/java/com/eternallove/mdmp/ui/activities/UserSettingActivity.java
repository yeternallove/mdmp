package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSettingActivity extends BaseActivity implements View.OnClickListener {
    private final static String BUNDLE = "bundle" + UserSettingActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.um_icon)
    View umIcon;
    @BindView(R.id.um_account)
    View umAccount;
    @BindView(R.id.um_account_tv)
    TextView tvAccount;
    @BindView(R.id.um_username)
    View umUserName;
    @BindView(R.id.um_username_tv)
    TextView tvUserName;
    @BindView(R.id.um_department)
    View umDepartment;
    @BindView(R.id.um_department_tv)
    TextView tvDepartment;
    @BindView(R.id.um_role)
    View umRole;
    @BindView(R.id.um_role_tv)
    TextView tvRole;
    @BindView(R.id.um_enable)
    View umEnable;
    @BindView(R.id.um_enable_sw)
    Switch swEnable;


    public static void actionStart(Activity activity, int requestCode, UserView user) {
        Intent intent = new Intent();
        intent.setClass(activity, UserSettingActivity.class);
        if (user != null) {
            ArrayList<String> list = new ArrayList<>();
            list.add(user.getAccount());
            list.add(user.getUsername());
            list.add(user.getDepartment());
            list.add(user.getRole());
            list.add(user.getEnable().toString());
            intent.putStringArrayListExtra(BUNDLE, list);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void actionStart(Context context, UserView user) {
        Intent intent = new Intent();
        intent.setClass(context, UserSettingActivity.class);
        if (user != null) {
            ArrayList<String> list = new ArrayList<>();
            list.add(user.getAccount());
            list.add(user.getUsername());
            list.add(user.getDepartment());
            list.add(user.getRole());
            list.add(user.getEnable().toString());
            intent.putStringArrayListExtra(BUNDLE, list);
        }
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

        initView();

    }

    public void initView() {
        List<String> list = getIntent().getStringArrayListExtra(BUNDLE);
        tvAccount.setText(list.get(0));
        tvUserName.setText(list.get(1));
        tvDepartment.setText(list.get(2));
        tvRole.setText(list.get(3));

        swEnable.setChecked((UserBean.ENABLE+"").equals(list.get(4)));

        umIcon.setOnClickListener(this);
        umAccount.setOnClickListener(this);
        umUserName.setOnClickListener(this);
        umDepartment.setOnClickListener(this);
        umRole.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

}
