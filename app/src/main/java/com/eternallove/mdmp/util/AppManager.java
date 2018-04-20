package com.eternallove.mdmp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eternallove.mdmp.model.test.user.UserView;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.logging.Logger;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/20 13:26
 */
public class AppManager {
    private Logger logger = Logger.getLogger(AppManager.class.getSimpleName());
    private static final String BASE_URL = "http://{localhost}:{port}/mdmp/";
    private static AppManager appManager;
    private UserView mUser;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String BaseUrl;

    private AppManager(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static AppManager getInstance(Context context) {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null)
                    appManager = new AppManager(context);
            }
        }
        return appManager;
    }

    public static AppManager getInstance() {
        return appManager;
    }

    public UserView getUser() {
        if (mUser == null) {
            String user = pref.getString("myUser", new UserView().toString());
            try {
                mUser = new UserView(user);
            } catch (JSONException e) {
                logger.warning(e.getMessage());
            }
        }
        return mUser;
    }

    public void setUser(UserView mUser) {
        this.mUser = mUser;
        editor = pref.edit();
        editor.putString("account", mUser.getAccount());
        editor.putString("password", mUser.getPassword());
        editor.putString("myUser", mUser.toString());
        editor.apply();
    }

    public String getBaseUrl() {
        if (BaseUrl == null) {
            BaseUrl = pref.getString("baseUrl", BASE_URL);
        }
        return BaseUrl;
    }

    public void setBaseUrl(String localhost, String port) {
        BaseUrl = BASE_URL
                .replace("{localhost}", localhost)
                .replace("{port}", port);
        editor = pref.edit();
        editor.putString("baseUrl", BaseUrl);
        editor.apply();
    }
}
