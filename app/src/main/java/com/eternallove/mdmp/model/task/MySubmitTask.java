package com.eternallove.mdmp.model.task;

import com.eternallove.mdmp.model.interfaces.TaskInterface;
import com.eternallove.mdmp.util.DateUtil;

import java.util.Date;

public class MySubmitTask implements TaskInterface {
    public final static int STATUS_EXTRACT = 0;
    public final static int STATUS_PENDING = 1;
    public final static int STATUS_END = 2;
    public final static int STATUS_DEFAULT = -1;


    // 主键，流程id
    private String flowId;
    // 流程名称
    private String flowName;
    // 数据容器
    private String mdCluster;
    // 数据模型
    private String mdModel;
    // 实体名称
    private String mdConcepet;
    // 操作类型
    private String operateType;
    // 待处理环节
    private String pendingLink;
    // 环节状态 :0.待提取；1.待处理；2.结束
    private int processStatus;
    // 提交时间
    private Date submitTime;
    // 用户ID
    private int userId;

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

    public String getMdConcepet() {
        return mdConcepet;
    }

    public void setMdConcepet(String mdConcepet) {
        this.mdConcepet = mdConcepet;
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

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public void setType(String type) {

    }

    @Override
    public String getFlow() {
        return getFlowName();
    }

    @Override
    public String getTask1() {
        return getPendingLink();
    }

    @Override
    public String getTask2() {
        String content = "";
        switch (getProcessStatus()){
            case STATUS_DEFAULT:
                content = "";
                break;
            case STATUS_EXTRACT:
                content = "待提取";
                break;
            case STATUS_PENDING:
                content = "待处理";
                break;
            case STATUS_END:
                content = "已结束";
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getTask3() {
        return DateUtil.format(getSubmitTime())+" 提交时间";
    }

    @Override
    public String getMDMName() {
        return getMdConcepet();
    }
}
