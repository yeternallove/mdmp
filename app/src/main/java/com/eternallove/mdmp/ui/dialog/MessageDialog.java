package com.eternallove.mdmp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eternallove.mdmp.R;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/29 15:28
 */
public class MessageDialog extends Dialog {

    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView tvTitle;//消息标题文本
    private TextView tvMessage;//消息提示文本
    private EditText edtNote;//备注填写
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    private String yesStr, noStr;//确定文本和取消文本的显示内容
    private String commentHint;//备注

    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public MessageDialog(Context context) {
        super(context, R.style.MessageDialog);
    }

    public MessageDialog(Context context, String title, String commentHint) {
        this(context);
        this.titleStr = title;
        this.commentHint = commentHint;
        setNoOnclickListener("否", this::dismiss);
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_message);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick(edtNote.getText().toString().trim());
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            tvTitle.setText(titleStr);
        }
        if (messageStr != null) {
            tvMessage.setText(messageStr);
        } else {
            tvMessage.setVisibility(View.GONE);
        }
        if (commentHint != null) {
            edtNote.setHint(commentHint);
        } else {
            edtNote.setVisibility(View.GONE);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        tvTitle = (TextView) findViewById(R.id.title);
        tvMessage = (TextView) findViewById(R.id.message);
        edtNote = (EditText) findViewById(R.id.note);
    }

    public void setTitle(String title) {
        titleStr = title;
    }


    public void setMessage(String message) {
        messageStr = message;
    }

    public void setCommentHint(String commentHint) {
        this.commentHint = commentHint;
    }

    public void cancel() {
        this.dismiss();
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick(String note);
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
