package com.eternallove.mdmp.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.eternallove.mdmp.R;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/20 19:43
 */
public class RunOnUiThreadUtil {
    private static String APP_NAME;
    private static String NETWORK;

    public static void showToast(Context context, String content) {
        if (APP_NAME == null) {
            APP_NAME = context.getString(R.string.app_name);
        }
        if (content != null) {
            Toast.makeText(context, APP_NAME + ":" + content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showNetworkToast(Context context) {
        if (NETWORK == null) {
            NETWORK = context.getString(R.string.network);
        }
        showToast(context, NETWORK);
    }

    public static void showAlertDialog(Context context, String[] items, DialogInterface.OnClickListener listener) {
        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //先得到构造器
        builder.setTitle("请选择"); //设置标题
        //builder.setMessage("是否确认退出?"); //设置内容
        //builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
        builder.setItems(items, listener);
        builder.create().show();
    }

    public static void showPopupMenu(@NonNull Context context, @NonNull View anchor, PopupMenu.OnMenuItemClickListener listener) {
        PopupMenu popupMenu = new PopupMenu(context, anchor);
        popupMenu.getMenuInflater()
                .inflate(R.menu.menu_comment, popupMenu.getMenu());
        popupMenu.setGravity(Gravity.START);
        popupMenu.setOnMenuItemClickListener(listener);
        popupMenu.show();
    }


}
