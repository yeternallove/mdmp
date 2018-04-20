package com.eternallove.mdmp.model.task;

import java.util.Date;

public class Taskdefined {


    //主键，流程id
    private String flowId;
    //流程名称
    private String flowName;
    //数据容器
    private String mdCluster;
    //数据模型
    private String mdModel;
    //实体名称
    private String mdConcepet;
    //操作类型
    private String operateType;
    //待处理环节
    private String pendingLink;
    //开始时间
    private Date beforeTime;
    //结束时间
    private Date afterTime;
    //任务状态
    private int taskStatus;
    //节点id
    private String taskId;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

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

    public String getPendingLink() {
        return pendingLink;
    }

    public void setPendingLink(String pendingLink) {
        this.pendingLink = pendingLink;
    }

    public Date getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(Date beginTime) {
        this.beforeTime = beginTime;
    }

    public Date getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Date endTime) {
        this.afterTime = endTime;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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


}
