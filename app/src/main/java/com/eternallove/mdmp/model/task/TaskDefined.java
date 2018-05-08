package com.eternallove.mdmp.model.task;

import com.eternallove.mdmp.model.interfaces.TaskInterface;
import com.eternallove.mdmp.util.DateUtil;

import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 待处理任务
 */
public class TaskDefined implements TaskInterface {
    /**
     * complete-已完成任务，
     * pend-待处理任务
     */
    public final static String COMPLETE = "complete";
    public final static String PEND = "pend";
    public final static String DEFAULT = "default";
    public final static int TO_EXTRACT = 1;
    /*主键，流程id*/
    private String flowId;
    /*流程名称*/
    private String flowName;
    /*数据容器*/
    private String mdCluster;
    /*数据模型*/
    private String mdModel;
    /*实体名称*/
    private String mdConcepet;
    /*操作类型*/
    private String operateType;
    /*待处理环节*/
    private String pendingLink;
    /*开始时间*/
    private Date beforeTime;
    /*结束时间*/
    private Date afterTime;
    /*任务状态*/
    private int taskStatus;
    /*节点id*/
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
        if (operateType == null) {
            return "";
        }
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


    @Override
    public String getId() {
        return getFlowId();
    }

    @Override
    public String getFlow() {
        return getFlowName();
    }

    @Override
    public String getTask1(String type) {
        String content = "";
        switch (type) {
            case COMPLETE:
                content = getPendingLink();
                break;
            case PEND:
                content = DateUtil.format(getBeforeTime()) + " 起始时间";
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getTask2(String type) {
        String content = "";
        switch (type) {
            case COMPLETE:
                content = DateUtil.format(getAfterTime()) + " 起始时间";
                break;
            case PEND:
                content = getPendingLink();
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getTask3(String type) {
        String content = "";
        switch (type) {
            case COMPLETE:
                content = DateUtil.format(getBeforeTime()) + " 结束时间";
                break;
            case PEND:
                content = DateUtil.format(getAfterTime()) + " 上个环节结束时间";
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getMDMName() {
        return getMdConcepet();
    }

    @Override
    public int getType() {
        switch (getOperateType()) {
            case "U":
                return TYPE_U;
            case "I":
                return TYPE_I;
            case "D":
                return TYPE_D;
            default:
                return TYPE_NULL;
        }
    }

    @Override
    public boolean isShowMore() {
        return TO_EXTRACT == getTaskStatus();

    }
}
