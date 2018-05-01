package com.eternallove.mdmp.model.user;

import com.eternallove.mdmp.util.MD5;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/21 11:50
 */
public class UserLogin {
    private String account;
    private String password;

    public UserLogin() {
        this.account = "test";
        this.password = "07116c88052ea8810cb298717a6f8f06";
    }

    public UserLogin(String account, String password) {
        this.account = account;
        this.password = MD5.getMD5Str(password);
    }
    public UserLogin(UserView userView) {
        this.account = userView.getAccount();
        this.password = userView.getPassword();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
