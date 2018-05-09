package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.ResponseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eternallove.mdmp.util.Constant.RS_CANCEL;
import static com.eternallove.mdmp.util.Constant.RS_UPDATE;

public class MeSettingActivity extends BaseActivity implements View.OnClickListener, EditActivity.OnSaveInterface {
    private final static int TAG_ACCOUNT = 0x10;
    private final static int TAG_USER_NAME = 0x12;
    private final static int TAG_DEPARTMENT = 0x13;
    private final static int TAG_ROLE = 0x14;


    private String TITLE_ACCOUNT;
    private String TITLE_USER_NAME;
    private String TITLE_DEPARTMENT;
    private String TITLE_ROLE;

    private AppManager appManager;
    private UserView mUser;

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
        init();
        updateData();
        toolbar.setNavigationOnClickListener(v -> finish());
    }


    public void init() {
        TITLE_ACCOUNT = this.getResources().getString(R.string.item_account);
        TITLE_USER_NAME = this.getResources().getString(R.string.item_user_name);
        TITLE_DEPARTMENT = this.getResources().getString(R.string.item_department);
        TITLE_ROLE = this.getResources().getString(R.string.item_role);

        appManager = AppManager.getInstance(this);
        mUser = appManager.getUserInfo();

        umAccount.setOnClickListener(this);
        umUserName.setOnClickListener(this);
        umDepartment.setOnClickListener(this);
        umRole.setOnClickListener(this);
        umPassword.setOnClickListener(this);
    }

    public void updateData() {
        if (mUser != null) {
            tvAccount.setText(mUser.getAccount());
            tvUserName.setText(mUser.getUsername());
            tvDepartment.setText(mUser.getDepartment());
            tvRole.setText(mUser.getRole());
        }
    }

    @Override
    public void onClick(View view) {
        appManager.setListener(this);
        switch (view.getId()) {
            case R.id.um_account:
                break;
            case R.id.um_username:
                EditActivity.actionStart(this, TITLE_USER_NAME, mUser.getUsername(), TAG_USER_NAME);
                break;
            case R.id.um_department:
                break;
            case R.id.um_role:
                break;
            case R.id.um_password:
                ChangePwdActivity.actionStart(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RS_UPDATE) {
            updateData();
        }
    }

    @Override
    public void onSave(String content, EditActivity activity) {
        UserView userView = (UserView) mUser.clone();
        switch (activity.getTag()) {
            case TAG_ACCOUNT:
                userView.setAccount(content);
                save(userView, activity);
                break;
            case TAG_USER_NAME:
                userView.setUsername(content);
                save(userView, activity);
                break;
            case TAG_DEPARTMENT:
                userView.setDepartment(content);
                save(userView, activity);
                break;
            case TAG_ROLE:
                userView.setRole(content);
                save(userView, activity);
                break;
            default:
                break;
        }
    }

    private void save(UserView user, EditActivity activity) {
        MdmpClient.getInstance().updateUser(user.getId(), user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String conntent = ResponseUtil.getVoidContent(response.body(), response.errorBody());
                if ("".equals(conntent)) {
                    activity.saveSuccess();
                    mUser = user;
                } else {
                    activity.saveFailure(conntent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                activity.saveFailure("网络异常");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        appManager.updateUser(mUser);
    }

}
