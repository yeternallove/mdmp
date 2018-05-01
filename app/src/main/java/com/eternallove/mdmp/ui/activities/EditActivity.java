package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.interfaces.OnSaveInterface;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends BaseActivity implements View.OnClickListener {
    private final static String NAME_TITLE = "name_title";
    private final static String NAME_NOTE = "name_note";
    private OnSaveInterface listener;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_tv_note)
    TextView tvNote;
    @BindView(R.id.edit_btn_save)
    Button btnSave;
    @BindView(R.id.edit_et_content)
    EditText etContent;

    public static void actionStart(Activity context, String title, String note) {
        Intent intent = new Intent();
        intent.setClass(context, EditActivity.class);
        if (title != null) {
            intent.putExtra(NAME_TITLE, title);
        }
        if (note != null) {
            intent.putExtra(NAME_NOTE, note);
        }
        context.startActivityForResult(intent, 1);
    }

    public static void actionStart(Activity context, String title) {
        actionStart(context, title, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra(NAME_TITLE);
        String note = intent.getStringExtra(NAME_NOTE);
        if (title != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
        setResult(0);
        listener = AppManager.getInstance(this).getListener();
        tvNote.setText(note);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn_save:
                String content = tvNote.getText().toString().trim();
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        return false;
                    }
                };
                if (listener != null && listener.onSave(content)) {
                    RunOnUiThreadUtil.showToast(this, "保存成功！~");
                    setResult(1);
                }
                break;
            default:
                break;
        }
    }

}
