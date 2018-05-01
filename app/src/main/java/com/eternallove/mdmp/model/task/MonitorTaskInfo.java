package com.eternallove.mdmp.model.task;

import java.util.Date;
import java.util.List;

public class MonitorTaskInfo {

	//实体名称
	private String mdConcepet;
	//操作类型
	private String operateType;
	//操作人
	private String processor;
	//操作时间
	private Date operateTime;
	//旧值
	private String beforeValue;
	//新值
	private String afterValue;
	//流程日志
	private List<AuditTask> logList;
	//流程模板
	private String templet;
	
	public String getMdConcepet() {
		return mdConcepet;
	}

	public void setMdConcepet(String mdView) {
		this.mdConcepet = mdView;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	public List<AuditTask> getLogList() {
		return logList;
	}

	public void setLogList(List<AuditTask> logList) {
		this.logList = logList;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

}
