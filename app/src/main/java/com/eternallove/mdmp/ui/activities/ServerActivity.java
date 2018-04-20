package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServerActivity extends BaseActivity implements View.OnClickListener {

    private AppManager appManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_btn_save)
    Button btnSave;
    @BindView(R.id.server_edt_server)
    EditText edtServer;
    @BindView(R.id.server_edt_port)
    EditText edtPort;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ServerActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appManager = AppManager.getInstance(this);
        initView();
        btnSave.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn_save:
                String server = edtServer.getText().toString().trim();
                String port = edtPort.getText().toString().trim();
                appManager.setBaseUrl(server, port);
                break;
            default:
                break;
        }
    }

}
