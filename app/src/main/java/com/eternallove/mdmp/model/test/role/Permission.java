package com.eternallove.mdmp.model.role;


/**
 * @author hzmc
 *
 */
public class Permission {
	private static final long serialVersionUID = 1L;

	//所有角色，默认包含“我的任务”权限
	public static final int MYTASK = 7;
	public static final Integer MYTASKObj = 7;
	
	//权限id
	private Integer id;
	//权限名称
    private String name;
    //逻辑删除状态
    private Integer status;
	
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
    
}
