package com.eternallove.mdmp.model.test.role;


/**
 * @author hzmc
 *
 */
public class RolePermissionRelation {
	// 自增Id
	private Integer id;
	// 角色Id
	private Integer roleId;
	// 权限Id
	private Integer permissionId;
	// 逻辑删除状态
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
