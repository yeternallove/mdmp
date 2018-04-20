package com.eternallove.mdmp.model.test.user;

import com.eternallove.mdmp.model.test.role.Permission;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserView {

    // 用户id
    private Integer id;
    // 账户（唯一）
    private String account;
    // 名称(可重复)
    private String username;
    // 部门id
    private Integer departmentId;
    // 部门名称
    private String department;
    // 角色id
    private Integer roleId;
    // 角色
    private String role;
    // 电话
    private String phone;
    // 密码
    private String password;
    // 密码
    private String oldpassword;
    // 是否启用状态，启用为true
    private Integer enable;
    //创建时间
    private Date createTime;

    // 当前用户所拥有的页面权限列表   登录使用
    private List<Permission> pageRightList;
    // 最后更新时间
    private long lastUpdateTime;

    public UserView() {
    }

    public UserView(String str) throws JSONException {
        JSONObject json = new JSONObject(str);
        if (json.has("account")) {
            this.setAccount(json.getString("account"));
        }
        if (json.has("username")) {
            this.setUsername(json.getString("username"));
        }
        if (json.has("department")) {
            this.setDepartment(json.getString("department"));
        }
        if (json.has("role")) {
            this.setRole(json.getString("role"));
        }
        if (json.has("enable")) {
            this.setEnable(json.getInt("enable"));
        }
        if (json.has("pageRightList")) {
            JSONArray jsonArray = json.getJSONArray("pageRightList");
            List<Permission> permissions = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                permissions.add(gson.fromJson(jsonArray.getString(i), Permission.class));
            }
            this.setPageRightList(permissions);
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
