package com.eternallove.mdmp.model.user;


import java.util.Date;

/**
 * 用户
 * @author hzmc
 *
 */
public class User {

	//角色启用
  	public static final int ENABLE = 1;
  	//角色禁用
  	public static final int NOTENABLE = 0;
  	
	// 用户id
	private Integer					id;
	// 账户（唯一）
	private String				account;
	// 电话
	private String				phone;
	// 是否启用状态，启用为true
	private Integer				enable;
	// 创建时间
	private Date				createTime;
	// 名称(可重复)
	private String				username;
	// 密码
	private String				password;
	// 部门id
	private Integer				departmentId;
	// 角色id
	private Integer				roleId;
	// 逻辑删除状态
	private Integer				status;
	
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
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getEnable() {
		return enable;
	}
	
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	public Date getCreatetime() {
		return createTime;
	}
	
	public void setCreatetime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

}
