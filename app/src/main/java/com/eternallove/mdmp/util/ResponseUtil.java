package com.eternallove.mdmp.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.ResponseBody;

/**
 * @description: 用来处理相应消息
 * @author: eternallove
 * @date: 2018/5/9 21:50
 */
public class ResponseUtil {
    private static Logger logger = Logger.getLogger(ResponseUtil.class.getSimpleName());

    /**
     * 后台为void 获取成功项
     *
     * @param body      *
     * @param errorBody *
     * @return
     */
    public static String getVoidContent(ResponseBody body, ResponseBody errorBody) {
        String bodyStr = null;
        String errorBodyStr = null;
        String content = null;

        try {
            if (body != null) {
                bodyStr = body.string();
            }
            if (errorBody != null) {
                errorBodyStr = errorBody.string();
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        if (bodyStr != null) {
            if (bodyStr.contains("errMsg")) {
                try {
                    JSONObject json = new JSONObject(bodyStr);
                    content = json.getString("errMsg");
                } catch (JSONException e) {
                    logger.warning(e.getMessage());
                }
            }else{
                content = bodyStr;
            }
        }
        if (errorBodyStr != null) {
            if (errorBodyStr.contains("errMsg")) {
                try {
                    JSONObject json = new JSONObject(errorBodyStr);
                    content = json.getString("errMsg");
                } catch (JSONException e) {
                    logger.warning(e.getMessage());
                }
            }else{
                content = errorBodyStr;
            }
        }
        return content;
    }
}
