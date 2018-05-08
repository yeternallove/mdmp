package com.eternallove.mdmp.model.parameter;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/5/7 16:36
 */
public class TaskAudit {

    private String advice;
    private String afterValue;
    private String comment;

    public TaskAudit() {
    }

    public TaskAudit(boolean advice, String afterValue, String comment) {
        setAdvice(advice);
        this.afterValue = afterValue;
        this.comment = comment;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(boolean advice) {
        if (advice) {
            this.advice = "Y";
        } else {
            this.advice = "N";
        }
    }

    public String getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(String afterValue) {
        this.afterValue = afterValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
