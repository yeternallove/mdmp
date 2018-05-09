package com.eternallove.mdmp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.eternallove.mdmp.R;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/29 15:28
 */
public class PendingDialog extends Dialog {

    private TextView messageTv;//消息提示文本
    private String messageStr;

    private PendingDialog(Context context) {
        super(context, R.style.PendingDialog);
    }

    public PendingDialog(Context context, String message ) {
        this(context);
        this.messageStr = message;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pending);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //按返回键不能取消动画
        setCancelable(false);

        initView();
        initData();
        initEvent();

    }

    private void initView() {
        messageTv = findViewById(R.id.message);
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
    }

    public void setMessage(String messageStr) {
        this.messageStr = messageStr;
    }
}
