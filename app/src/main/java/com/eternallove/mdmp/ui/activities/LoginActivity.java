package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.UserLogin;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.dialog.PendingDialog;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements OnClickListener {

    private AppManager appManager;
    private PendingDialog dialog;
    @BindView(R.id.edt_acc)
    EditText edtAccount;
    @BindView(R.id.edt_pwd)
    EditText edtPassword;
    @BindView(R.id.main_btn_login)
    TextView btnLogin;
    @BindView(R.id.main_btn_server)
    TextView btnServer;


    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
        //hide the status bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        appManager = AppManager.getInstance(this);

        dialog = new PendingDialog(this,"正在登录...");
        btnLogin.setOnClickListener(this);
        btnServer.setOnClickListener(this);
//        lyLogin.setPadding(0,0,0,0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_login:
                attemptLogin();
                break;
            case R.id.main_btn_server:
                ServerActivity.actionStart(this);
            default:
                break;
        }
    }

    /**
     * 尝试登录
     */
    private void attemptLogin() {

        // Reset errors.
        edtAccount.setError(null);
        edtPassword.setError(null);

        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        //TODO 测试使用
        if (account.equals("1")) {
            MainActivity.actionStart(this);
            finish();
            return;
        }
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            edtPassword.setError(getString(R.string.error_invalid_password));
            focusView = edtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (!isAccountValid(account)) {
            edtAccount.setError(getString(R.string.error_field_required));
            focusView = edtAccount;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            btnLogin.setEnabled(false);
            UserLogin(account, password);
        }
    }

    private boolean isAccountValid(String account) {
        //TODO: Replace this with your own logic
//        return email.contains("@");
        return account.length() > 0;
//        return true;

    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
//        return true;
    }

    private void UserLogin(String account, String password) {
        dialog.show();
        MdmpClient.getInstance().login(new UserLogin(account, password)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if (body != null) {
                    try {
                        JSONObject json = new JSONObject(body.string());
                        if (json.has("userInfo")) {
                            UserView userView = UserView.build(json.getString("userInfo"));
                            appManager.login(userView, password);
                            MainActivity.actionStart(LoginActivity.this);
                            finish();
                        }
                        if (json.has("error")) {
                            RunOnUiThreadUtil.showToast(LoginActivity.this, json.getString("error"));
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                saveFailure();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                saveFailure();
                RunOnUiThreadUtil.showNetworkToast(LoginActivity.this);
            }
        });
    }

    private void saveFailure() {
        btnLogin.setEnabled(true);
        new Handler().postDelayed(() -> dialog.cancel(), 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
}
