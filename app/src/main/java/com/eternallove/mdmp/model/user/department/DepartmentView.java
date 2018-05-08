package com.eternallove.mdmp.model.user.department;

import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.UserBean;

import java.util.List;

public class DepartmentView extends Department implements UserAttribute {
    private List<UserBean> user;

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
        for (UserBean user : getUser()) {
            sb.append(user.getUsername()).append("(").append(user.getAccount()).append(") ");
        }
        return sb.toString();
    }

    @Override
    public String getOther() {
        return null;
    }
}
