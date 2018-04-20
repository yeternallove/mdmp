package com.eternallove.mdmp.model.user;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/3 21:52
 */
public class UserAttribute {
    private String headings;
    private String secondary;
    private String other;

    public UserAttribute(String headings, String secondary, String other) {
        this.headings = headings;
        this.secondary = secondary;
        this.other = other;
    }

    public UserAttribute() {

    }

    public String getHeadings() {
        return headings;
    }

    public void setHeadings(String headings) {
        this.headings = headings;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
