package com.eternallove.mdmp.model.user;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/21 11:50
 */
public class UserTest {
    private String account;
    private String password;

    public UserTest() {
        this.account = "admin";
        this.password = "07116c88052ea8810cb298717a6f8f06";
    }

    public UserTest(String account, String password) {
        this.account = account;
        this.password = password;
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
