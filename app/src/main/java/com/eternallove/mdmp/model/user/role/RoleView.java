package com.eternallove.mdmp.model.user.role;

import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;

import java.util.List;

/**
 * 用户角色
 * @author hzmc
 *
 */
public class RoleView implements UserAttribute{
	
	//系统角色
  	public static final int DEFAULTS = 10;
  	//自定义角色
  	public static final int CUSTOM = 10;
	
	//角色id
	private Integer id;
	//角色名称
    private String name;
    //角色说明
    private String comment;
    //页面权限
    private List<Integer> permissionIds;
    
    private List<Permission> permissions;
    //视图权限
    private List<Integer> viewRightIds;
    
  	private List<ViewRightView>	viewRights;

	public RoleView() {
	}

	public RoleView(String name, String comment) {
		this.name = name;
		this.comment = comment;
	}

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
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
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
