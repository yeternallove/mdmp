package com.eternallove.mdmp.util.gson;

import com.eternallove.mdmp.model.task.TaskDefined;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * @description: 项目中使用到的Gson
 * @author: eternallove
 * @date: 2018/5/1 17:59
 */
public class GsonHalper {
    private static Gson GSON;

    public static Gson build() {
        if (GSON == null) {
            GSON = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateAdapter())
                    .create();
        }
        return GSON;
    }
}
