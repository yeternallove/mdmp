package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.dialog.PendingDialog;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.eternallove.mdmp.util.Constant.RS_CANCEL;
import static com.eternallove.mdmp.util.Constant.RS_UPDATE;

public class EditActivity extends BaseActivity implements View.OnClickListener {
    private final static String NAME_TITLE = "name_title";
    private final static String NAME_CONTENT = "name_content";
    private final static String NAME_NOTE = "name_note";
    private final static String NAME_TAG = "name_tag";

    private int tag;
    private OnSaveInterface listener;
    private PendingDialog dialog;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_tv_note)
    TextView tvNote;
    @BindView(R.id.edit_btn_save)
    Button btnSave;
    @BindView(R.id.edit_et_content)
    EditText etContent;

    public interface OnSaveInterface {
        void onSave(String content, EditActivity activity);
    }

    /**
     * @ context *
     * @ title   标题
     * @ note    内容
     * @ tag     标签 >=0 默认-1
     */
    public static void actionStart(Activity context, String title, String content, String note, int tag) {
        if (context instanceof OnSaveInterface) {
            AppManager.getInstance(context).setListener((OnSaveInterface) context);
        } else {
            AppManager.getInstance(context).setListener(null);
        }
        Intent intent = new Intent();
        intent.setClass(context, EditActivity.class);
        if (title != null) {
            intent.putExtra(NAME_TITLE, title);
        }
        if (content != null) {
            intent.putExtra(NAME_CONTENT, content);
        }
        if (note != null) {
            intent.putExtra(NAME_NOTE, note);
        }
        intent.putExtra(NAME_TAG, tag);
        context.startActivityForResult(intent, tag);
    }

    public static void actionStart(Activity context, String title, String content, int tag) {
        actionStart(context, title, content, null, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra(NAME_TITLE);
        String content = intent.getStringExtra(NAME_CONTENT);
        String note = intent.getStringExtra(NAME_NOTE);
        tag = intent.getIntExtra(NAME_TAG, -1);
        if (title != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
        dialog = new PendingDialog(this,"正在保存...");
        listener = AppManager.getInstance(this).getListener();
        etContent.setText(content);
        tvNote.setText(note);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn_save:
                dialog.show();
                String content = etContent.getText().toString().trim();
                save();
                if (listener != null) {
                    listener.onSave(content, this);
                } else {
                    saveFailure("未指定操作");
                }
                break;
            default:
                break;
        }
    }


    private void save() {
        btnSave.setEnabled(false);
    }

    public int getTag() {
        return tag;
    }

    public void saveSuccess() {
        onSave(RS_UPDATE, "保存成功");
    }

    public void saveFailure(String content) {
        onSave(RS_CANCEL, content);
    }

    private void onSave(int resultCode, String content) {
        setResult(resultCode);
        btnSave.setEnabled(true);
        dialog.cancel();
        RunOnUiThreadUtil.showToast(this, content);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
}
