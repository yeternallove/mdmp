package com.eternallove.mdmp.model.user.role;

public class Role {
	
	private static final long serialVersionUID = 1L;
	 
	//系统角色
  	public static final int DEFAULTS = 10;
  	//自定义角色
  	public static final int CUSTOM = 11;
	
	//角色id
	private Integer id;
	//角色名称
    private String name;
    
    //是否为系统角色
    private Integer type;
    //角色说明
    private String comment;
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
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
    
}
