/**
 * 
 */
package com.eternallove.mdmp.model.user;

import java.io.Serializable;

/**
 * 工作流用户
 * @author zoujk
 *
 */
public class BaseUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5682201128889324081L;
	private String id;
	private String state;
	private String firstName;
	private Integer userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}



}
