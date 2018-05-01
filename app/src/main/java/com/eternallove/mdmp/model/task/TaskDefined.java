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
     * complete-已完成任务，pend-待处理任务
     */
    public final static String COMPLETE = "complete";
    public final static String PEND = "pend";
    private final static int COMPLETE_VALUE = 0xF1;
    private final static int PEND_VALUE = 0xF2;
    private final static int DEFINED = 0x0;
    private int taskType = PEND_VALUE;

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

    private void setTaskType(int taskType) {
        this.taskType = taskType;
    }

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


    @Override
    public void setType(String type) {
        switch (type) {
            case COMPLETE:
                setTaskType(COMPLETE_VALUE);
                break;
            case PEND:
                setTaskType(PEND_VALUE);
                break;
            default:
                setTaskType(DEFINED);
                break;
        }
    }

    @Override
    public String getFlow() {
        String content = "";
        switch (taskType) {
            case COMPLETE_VALUE:
            case PEND_VALUE:
                content = getFlowName();
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getTask1() {
        String content = "";
        switch (taskType) {
            case COMPLETE_VALUE:
                content = getPendingLink();
                break;
            case PEND_VALUE:
                content = DateUtil.format(getBeforeTime()) + " 起始时间";
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getTask2() {
        String content = "";
        switch (taskType) {
            case COMPLETE_VALUE:
                content = DateUtil.format(getAfterTime()) + " 起始时间";
                break;
            case PEND_VALUE:
                content = getPendingLink();
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getTask3() {
        String content = "";
        switch (taskType) {
            case COMPLETE_VALUE:
                content = DateUtil.format(getBeforeTime()) + " 结束时间";
                break;
            case PEND_VALUE:
                content = DateUtil.format(getAfterTime()) + " 上个环节结束时间";
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public String getMDMName() {
        String content = "";
        switch (taskType) {
            case COMPLETE_VALUE:
            case PEND_VALUE:
                content = getMdConcepet();
                break;
            default:
                break;
        }
        return content;
    }
}
