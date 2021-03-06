package com.eternallove.mdmp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.user.UserLogin;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.department.Department;
import com.eternallove.mdmp.model.user.department.DepartmentView;
import com.eternallove.mdmp.model.user.role.Role;
import com.eternallove.mdmp.model.user.role.RoleView;
import com.eternallove.mdmp.ui.activities.EditActivity.OnSaveInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/20 13:26
 */
public class AppManager {
    private Logger logger = Logger.getLogger(AppManager.class.getSimpleName());
    private static AppManager appManager;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private UserView mUser;

    private String BaseUrl;
    private String mServer;
    private String mPort;

    private OnSaveInterface listener;

    private List<Department> departments = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();


    private final static String BASE_URL = "http://{localhost}:{port}/mdmp/";
    private final static String SP_PASSWORD = "APP_ETERNALLOVE";
    private final static String SP_USER = "myUser";
    private final static String SP_PORT = "port";
    private final static String SP_SERVER = "server";


    private AppManager(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        mServer = pref.getString(SP_SERVER, context.getString(R.string.server_server_value));
        mPort = pref.getString(SP_PORT, context.getString(R.string.server_port_value));
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

    public UserView getUserInfo() {
        mUser = getUser();
        if (mUser == null) {
            return null;
        }
        UserView user = (UserView) mUser.clone();
        user.setPassword(null);
        return user;
    }

    private UserView getUser() {
        if (mUser == null) {
            String user = pref.getString(SP_USER, "");
            if ("".equals(user)) {
                return null;
            } else {
                mUser = UserView.build(user);
            }
        }
        return mUser;
    }

    public UserLogin getLoginInfo() {
        mUser = getUser();
        if (mUser == null) {
            return null;
        }
        return new UserLogin(mUser);
    }

    public void logout() {
        if (mUser != null) {
            mUser = null;
            editor = pref.edit();
            editor.remove(SP_PASSWORD);
            editor.remove(SP_USER);
            editor.apply();
        }
    }

    /**
     * 登录
     *
     * @param mUser 登录信息
     * @param pwd   初始密码 保存加密内容
     */
    public void login(UserView mUser, String pwd) {
        this.mUser = mUser;
        editor = pref.edit();
        editor.putString(SP_PASSWORD, pwd);
        mUser.setPassword(MD5.getMD5Str(pwd));
        editor.putString(SP_USER, mUser.toString());
        editor.apply();
    }

    /**
     * 自动登录 更新用户信息
     *
     * @param user
     */
    public void updateUser(UserView user) {
        this.mUser = (UserView) user.clone();
        String pwd = pref.getString(SP_PASSWORD, "");
        this.mUser.setPassword(MD5.getMD5Str(pwd));
        editor = pref.edit();
        editor.putString(SP_USER, this.mUser.toString());
        editor.apply();
    }

    public String getBaseUrl() {
        if (BaseUrl == null) {
            BaseUrl = BASE_URL
                    .replace("{localhost}", mServer)
                    .replace("{port}", mPort);
        }
        return BaseUrl;
    }

    public void setBaseUrl(String localhost, String port) {
        if (RegexUtil.isIP(localhost)) {
            mServer = localhost;
        }
        mPort = port;
        BaseUrl = BASE_URL
                .replace("{localhost}", mServer)
                .replace("{port}", mPort);

        editor = pref.edit();
        editor.putString(SP_SERVER, mServer);
        editor.putString(SP_PORT, mPort);
        editor.apply();
    }

    public String getServer() {
        return mServer;
    }

    public String getPort() {
        return mPort;
    }

    public OnSaveInterface getListener() {
        return listener;
    }

    public void setListener(OnSaveInterface listener) {
        this.listener = listener;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentView> departments) {
        this.departments.clear();
        this.departments.addAll(departments);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleView> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
    }
}
