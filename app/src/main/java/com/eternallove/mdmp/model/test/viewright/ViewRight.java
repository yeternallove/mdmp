package com.eternallove.mdmp.model.test.viewright;


/**
 * 视图权限表
 * 表达用户和视图的关联关系
 * @author hzmc
 *
 */
public class ViewRight {
	//自增Id
	private Integer id;
	//用户Id
	private Integer roleId;
	//容器
	private String mdCluster;
	//模型
	private String mdModel;
	//视图
	private String mdView;
	//视图描述
	private String description;
	//逻辑删除状态
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

	public String getMdCluster() {
		return mdCluster;
	}
	
	public void setMdCluster(String mdCluster) {
		this.mdCluster = mdCluster;
	}
	
	public String getMdModel() {
		return mdModel;
	}
	
	public void setMdModel(String mdModel) {
		this.mdModel = mdModel;
	}
	
	public String getMdView() {
		return mdView;
	}
	
	public void setMdView(String mdView) {
		this.mdView = mdView;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
