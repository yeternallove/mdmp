package com.eternallove.mdmp.model.user.role;

import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;

import java.util.List;

public class RoleView extends Role implements UserAttribute {

    //页面权限
    private List<Integer> permissionIds;

    private List<Permission> permissions;
    //视图权限
    private List<Integer> viewRightIds;

    private List<ViewRightView> viewRights;

    public RoleView() {
    }

    public RoleView(String name, String comment) {
        super(name, comment);
    }


    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


    public List<Integer> getViewRightIds() {
        return viewRightIds;
    }


    public void setViewRightIds(List<Integer> viewRightIds) {
        this.viewRightIds = viewRightIds;
    }


    public List<ViewRightView> getViewRights() {
        return viewRights;
    }


    public void setViewRights(List<ViewRightView> viewRights) {
        this.viewRights = viewRights;
    }

    @Override
    public String getHeadings() {
        return getName();
    }

    @Override
    public String getSecondary() {
        return getComment();
    }

    @Override
    public String getOther() {
        return null;
    }
}
