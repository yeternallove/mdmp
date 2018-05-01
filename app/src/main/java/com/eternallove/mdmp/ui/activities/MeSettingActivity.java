package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.interfaces.OnSaveInterface;
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeSettingActivity extends BaseActivity implements View.OnClickListener ,OnSaveInterface{

    @BindView(R.id.sch_add_toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.um_password)
    View umPassword;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MeSettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_setting);
        ButterKnife.bind(this);
        initView();

        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void initView() {
        UserView user = AppManager.getInstance(this).getUser();
        tvAccount.setText(user.getAccount());
        tvUserName.setText(user.getUsername());
        tvDepartment.setText(user.getDepartment());
        tvRole.setText(user.getRole());

        umAccount.setOnClickListener(this);
        umUserName.setOnClickListener(this);
        umDepartment.setOnClickListener(this);
        umRole.setOnClickListener(this);
        umPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.um_account:
                break;
            case R.id.um_password:
                ChangePwdActivity.actionStart(this);
                break;
            default:
                EditActivity.actionStart(this, "233");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                RunOnUiThreadUtil.showToast(this, resultCode + "");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onSave(String content) {
        new Handler().postDelayed(() -> {
            ViewRightView viewRightView = new ViewRightView();
            viewRightView.setName("视图");
            viewRightView.setView("视图描述");
        }, 2000);
        return false;
    }
}
