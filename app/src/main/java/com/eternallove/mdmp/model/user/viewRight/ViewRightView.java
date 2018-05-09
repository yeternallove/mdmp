package com.eternallove.mdmp.model.user.viewRight;

import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.role.Role;

import java.util.List;

public class ViewRightView implements UserAttribute {

    // 容器
    private String cluster;
    // 模型
    private String model;
    // 视图名称（key）
    private String view;
    //视图描述
    private String name;

    private List<Role> role;

    private List<Integer> roleIds;

    public String getCluster() {
        return cluster;
    }


    public void setCluster(String cluster) {
        this.cluster = cluster;
    }


    public String getModel() {
        return model;
    }


    public void setModel(String model) {
        this.model = model;
    }


    public String getView() {
        return view;
    }


    public void setView(String view) {
        this.view = view;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Role> getRole() {
        return role;
    }


    public void setRole(List<Role> role) {
        this.role = role;
    }


    public List<Integer> getRoleIds() {
        return roleIds;
    }


    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cluster == null) ? 0 : cluster.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((view == null) ? 0 : view.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ViewRightView other = (ViewRightView) obj;
        if (cluster == null) {
            if (other.cluster != null)
                return false;
        } else if (!cluster.equals(other.cluster))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (view == null) {
            if (other.view != null)
                return false;
        } else if (!view.equals(other.view))
            return false;
        return true;
    }


    @Override
    public String getHeadings() {
        return getName();
    }

    @Override
    public String getSecondary() {
        StringBuilder roles = new StringBuilder("");
        if (role != null && role.size() > 0) {
            roles.append(role.get(0).getName());
            for (int i = 1; i < role.size(); i++) {
                roles.append(",").append(role.get(i).getName());
            }
        }
        return roles.toString();
    }

    @Override
    public String getOther() {
        return null;
    }

    @Override
    public boolean isShowMore() {
        return false;
    }
}
