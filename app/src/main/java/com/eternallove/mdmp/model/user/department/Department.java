package com.eternallove.mdmp.model.user.department;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/5/6 0:44
 */
public class Department {
    //部门Id
    private Integer id;
    //部门名称
    private String name;
    //逻辑删除状态
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
