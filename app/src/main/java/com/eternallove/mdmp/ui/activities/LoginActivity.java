package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.test.user.UserTest;
import com.eternallove.mdmp.model.test.user.UserView;
import com.eternallove.mdmp.ui.base.BaseActivity;
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

public class LoginActivity extends BaseActivity implements OnClickListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
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
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
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

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edtPassword.setError(getString(R.string.error_invalid_password));
            focusView = edtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(account)) {
            edtAccount.setError(getString(R.string.error_field_required));
            focusView = edtAccount;
            cancel = true;
        } else if (!isAccountValid(account)) {
            edtAccount.setError(getString(R.string.error_invalid_email));
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
            UserLogin();
        }
    }

    private boolean isAccountValid(String account) {
        //TODO: Replace this with your own logic
//        return email.contains("@");
        return account.length() > 0;

    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
    }

    private void UserLogin() {
        MdmpClient.getInstance().login(new UserTest()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if (body != null) {
                    try {
                        Gson gson = new Gson();
                        JSONObject json = new JSONObject(body.string());
                        if (json.has("userInfo")) {
                            UserView userView = new UserView(json.getString("userInfo"));
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

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RunOnUiThreadUtil.showToast(LoginActivity.this, "请检查网络");
            }
        });
    }
}
