package com.eternallove.mdmp.model.user.department;

import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.UserBean;

import java.util.List;

public class DepartmentView implements UserAttribute {
    //部门Id
    private Integer id;
    //部门名称
    private String name;
    //逻辑删除状态
    private Integer status;
    private List<UserBean> user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    @Override
    public String getHeadings() {
        return getName();
    }

    @Override
    public String getSecondary() {
        StringBuilder sb = new StringBuilder();
        for (UserBean user :getUser()){
            sb.append(user.getUsername()).append("(").append(user.getAccount()).append(") ");
        }
        return sb.toString();
    }

    @Override
    public String getOther() {
        return null;
    }
}
