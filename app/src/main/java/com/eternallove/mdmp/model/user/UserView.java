package com.eternallove.mdmp.model.user;

import com.eternallove.mdmp.model.user.role.Permission;
import com.eternallove.mdmp.util.gson.DateAdapter;
import com.eternallove.mdmp.util.gson.GsonHalper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserView extends UserBean {

    // 部门名称
    private String department;
    // 角色
    private String role;
    // 密码
    private String oldpassword;
    // 是否启用状态，启用为true

    // 当前用户所拥有的页面权限列表   登录使用
    private List<Permission> pageRightList;
    // 最后更新时间
    private long lastUpdateTime;

    public UserView() {
    }

    public static UserView build(String user) {
        Gson gson = GsonHalper.build();
        return gson.fromJson(user, UserView.class);
    }

    @Override
    public String toString() {
        Gson gson = GsonHalper.build();
        return gson.toJson(this);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public List<Permission> getPageRightList() {
        return pageRightList;
    }

    public void setPageRightList(List<Permission> pageRightList) {
        this.pageRightList = pageRightList;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
