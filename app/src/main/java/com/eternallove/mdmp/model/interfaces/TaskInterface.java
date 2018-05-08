package com.eternallove.mdmp.model.interfaces;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/22 10:24
 */
public interface TaskInterface {
    int TYPE_NULL = 0x10;
    int TYPE_I = 0x11;
    int TYPE_U = 0x12;
    int TYPE_D = 0x13;

    String getId();

    String getFlow();

    String getTask1(String type);

    String getTask2(String type);

    String getTask3(String type);

    String getMDMName();

    int getType();

    boolean isShowMore();
}
