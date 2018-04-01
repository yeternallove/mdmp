/**
 * 
 */
package com.eternallove.mdmp.model.test.user;

import java.io.Serializable;

/**
 * 工作流部门
 * @author zoujk
 *
 */
public class BaseDepartment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889401374694467688L;
	private String id;
	private String name;
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
