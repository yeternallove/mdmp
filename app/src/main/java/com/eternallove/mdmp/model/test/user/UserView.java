///**
// * 版权所有：美创科技
// * 项目名称:mdmp
// * 创建者: hzmc
// * 创建日期: 2017年2月15日
// * 文件说明:
// * 最近修改者：hzmc
// * 最近修改日期：2017年2月15日
// */
//package com.eternallove.mdmp.model.user;
//
//import java.util.Date;
//import java.util.List;
//import com.eternallove.mdmp.model.role.Permission;
//import com.eternallove.mdmp.model.viewright.ViewRightView;
//
///**
// * @author hzmc
// *
// */
//public class UserView {
//
//	// 用户id
//	private Integer					id;
//	// 账户（唯一）
//	private String				account;
//	// 名称(可重复)
//	private String				username;
//	// 部门id
//	private Integer				departmentId;
//	// 部门名称
//	private String				department;
//	// 角色id
//	private Integer				roleId;
//	// 角色
//	private String				role;
//	// 电话
//	private String				phone;
//	// 密码
//	private String				password;
//	// 密码
//	private String				oldpassword;
//	// 是否启用状态，启用为true
//	private Integer				enable;
//	//创建时间
//    private Date createTime;
//
//    // 当前用户所拥有的页面权限列表   登录使用
// 	List<Permission> pageRightList;
// 	// 最后更新时间
// 	private long lastUpdateTime;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getAccount() {
//		return account;
//	}
//
//	public void setAccount(String account) {
//		this.account = account;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public Integer getDepartmentId() {
//		return departmentId;
//	}
//
//	public void setDepartmentId(Integer departmentId) {
//		this.departmentId = departmentId;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getOldpassword() {
//		return oldpassword;
//	}
//
//	public void setOldpassword(String oldpassword) {
//		this.oldpassword = oldpassword;
//	}
//
//	public Integer getEnable() {
//		return enable;
//	}
//
//	public void setEnable(Integer enable) {
//		this.enable = enable;
//	}
//
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	public List<Permission> getPageRightList() {
//		return pageRightList;
//	}
//
//	public void setPageRightList(List<Permission> pageRightList) {
//		this.pageRightList = pageRightList;
//	}
//
//	public long getLastUpdateTime() {
//		return lastUpdateTime;
//	}
//
//	public void setLastUpdateTime(long lastUpdateTime) {
//		this.lastUpdateTime = lastUpdateTime;
//	}
//
//}
