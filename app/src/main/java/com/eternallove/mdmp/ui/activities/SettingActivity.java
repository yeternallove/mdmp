package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.dialog.PendingDialog;
import com.eternallove.mdmp.util.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eternallove.mdmp.util.Constant.RS_CANCEL;
import static com.eternallove.mdmp.util.Constant.RS_EXIT;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private AppManager appManager;
    private PendingDialog dialog;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting_about)
    View settingAbout;
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
            setResult(RS_CANCEL);
            finish();
        });
        appManager = AppManager.getInstance(this);
        dialog = new PendingDialog(this,"退出登录...");
        initView();
    }

    private void initView() {
        settingAbout.setOnClickListener(this);
        umLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_about:
                AboutActivity.actionStart(this);
                break;
            case R.id.um_logout:
                dialog.show();
                logout();
                break;
            default:
                break;
        }
    }

    private void logout() {
        MdmpClient.getInstance().logout().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                appManager.logout();
                LoginActivity.actionStart(SettingActivity.this);
                setResult(RS_EXIT);
                finish();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
}
