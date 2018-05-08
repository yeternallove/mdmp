package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.os.Bundle;


import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.UserLogin;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @description:
 * @author: eternallove
 * @date: 2016/12/25
 */
public class SplashActivity extends BaseActivity {

    //TODO wait update
    private String mVersionName;//版本名
    private int mVersionCode;//版本号
    private String mDesc;//版本描述
    private String mdownloadUrl;//新版本下载网站

    private AppManager appManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        appManager = AppManager.getInstance(this);
        UserLogin login = appManager.getLoginInfo();
        if (login != null) {
//            //1.5S的延迟
//            Timer timer =new Timer();
//            TimerTask task =new TimerTask() {
//                @Override
//                public void run() {
//                    MainActivity.actionStart(SplashActivity.this);
//                    finish();
//                }
//            };
//            timer.schedule(task,1500);

            userLogin(this, login);
        } else {
            startLogin();
        }
    }

    private void userLogin(Context context, UserLogin userLogin) {
        MdmpClient.getInstance().login(userLogin).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if (body != null) {
                    try {
                        JSONObject json = new JSONObject(body.string());
                        if (json.has("userInfo")) {
                            UserView userView = UserView.build(json.getString("userInfo"));
                            appManager.updateUser(userView);
                            startMain();
                        }
                        if (json.has("error")) {
                            RunOnUiThreadUtil.showToast(context, "自动登录失败");
                            appManager.logout();
                            startLogin();
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                startLogin();
            }
        });
    }
    private void startLogin(){
        LoginActivity.actionStart(SplashActivity.this);
        finish();
    }

    private void startMain(){
        MainActivity.actionStart(SplashActivity.this);
        finish();
    }
}
