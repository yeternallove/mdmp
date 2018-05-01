package com.eternallove.mdmp.model.task;

import java.io.Serializable;
import java.util.Date;

public class AuditTask implements Serializable {

	private static final long serialVersionUID = 5282747335717126889L;
	//开始时间
	private Date startTime;
	//环节名称
	private String linkName;
	//操作人
	private String operator;
	//审核状态
	private int auditStatus;
	//处理
	private String manage;
	//备注
	private String remark;
	//结束时间
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}

}
