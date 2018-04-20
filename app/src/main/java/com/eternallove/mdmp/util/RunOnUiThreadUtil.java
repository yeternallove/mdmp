package com.eternallove.mdmp.util;

import android.content.Context;
import android.widget.Toast;

import com.eternallove.mdmp.R;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/20 19:43
 */
public class RunOnUiThreadUtil {
    private static String APP_NAME;

    public static void showToast(Context context, String content) {
        if (APP_NAME == null) {
            APP_NAME = context.getString(R.string.app_name);
        }
        Toast.makeText(context, APP_NAME + ":" + content, Toast.LENGTH_SHORT).show();
    }

}
