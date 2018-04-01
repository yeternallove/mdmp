/**
 * 版权所有：美创科技
 * 项目名称:mdmp
 * 创建者: hzmc
 * 创建日期: 2017年2月14日
 * 文件说明:
 * 最近修改者：hzmc
 * 最近修改日期：2017年2月14日
 */
package com.eternallove.mdmp.model.user;

/**
 * 查询用
 * @author hzmc
 */
public class UserQuery {

	// 查询内容：账号或名称
	private String	searchValue;
	// 是否启用状态，启用为1
	private Integer enable;
	// 部门id
	private Integer	departmentId;
	// 角色id
	private Integer	roleId;
	
	public String getSearchValue() {
		return searchValue;
	}
	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	public Integer getEnable() {
		return enable;
	}
	
	public void setEnable(Integer enable) {
		this.enable = enable;
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

}
