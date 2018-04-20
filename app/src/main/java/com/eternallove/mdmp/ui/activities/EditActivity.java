package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends BaseActivity implements View.OnClickListener {
    private final static String NAME_TITLE = "name_title";
    private final static String NAME_NOTE = "name_note";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_tv_note)
    TextView tvNote;
    @BindView(R.id.edit_btn_save)
    Button btnSave;
    @BindView(R.id.edit_et_content)
    EditText etContent;

    public static void actionStart(Context context, String title, String note) {
        Intent intent = new Intent();
        intent.setClass(context, EditActivity.class);
        if (title != null) {
            intent.putExtra(NAME_TITLE, title);
        }
        if (note != null) {
            intent.putExtra(NAME_NOTE, note);
        }
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String title) {
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvNote.setText(note);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn_save:

                break;
            default:
                break;
        }
    }

}
