package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.MD5;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/29 15:55
 */
public class ChangePwdActivity extends BaseActivity implements View.OnClickListener {

    private UserView userView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.change_pwd_old)
    EditText edtPwdOld;
    @BindView(R.id.change_pwd_new)
    EditText edtPwdNew;
    @BindView(R.id.change_pwd_two)
    EditText edtPwdTwo;
    @BindView(R.id.change_btn_save)
    Button btnSave;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ChangePwdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(v -> finish());

        userView = AppManager.getInstance(this).getUser();
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_btn_save:
                save();
                break;
            default:
                break;
        }
    }

    private void save() {
        String oldPwd = edtPwdOld.getText().toString().trim();
        String newPwd = edtPwdNew.getText().toString().trim();
        String twoPwd = edtPwdTwo.getText().toString().trim();
        if (!newPwd.equals(twoPwd)) {
            RunOnUiThreadUtil.showToast(this, "两次密码输入不正确");
        }
        UserView user = new UserView();
        user.setOldpassword(MD5.getMD5Str(oldPwd));
        user.setPassword(MD5.getMD5Str(newPwd));
        MdmpClient.getInstance()
                .changePwd(userView.getId(), user)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        if (body == null) {
                            body = response.errorBody();
                        }
                        if (body != null) {
                            try {
                                JSONObject json = new JSONObject(body.string());
                                if (json.has("errMsg")) {
                                    RunOnUiThreadUtil.showToast(ChangePwdActivity.this, json.getString("errMsg"));
                                }else{
                                    RunOnUiThreadUtil.showToast(ChangePwdActivity.this, "修改密码成功！");
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        RunOnUiThreadUtil.showNetworkToast(ChangePwdActivity.this);
                    }
                });
    }

}
