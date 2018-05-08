package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RegexUtil;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServerActivity extends BaseActivity implements View.OnClickListener {

    private AppManager appManager;
    private String mServer;
    private String mPort;
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
        toolbar.setNavigationOnClickListener(v -> finish());
        appManager = AppManager.getInstance(this);
        initView();
    }

    private void initView() {
        mServer = appManager.getServer();
        mPort = appManager.getPort();
        edtServer.setHint(mServer);
        edtServer.setText(mServer);
        edtPort.setHint(mPort);
        edtPort.setText(mPort);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_btn_save:
                boolean save = true;
                String server = edtServer.getText().toString().trim();
                String port = edtPort.getText().toString().trim();
                if (mServer.equals(server) && mPort.equals(port)) {
                    break;
                }
                if (!mServer.equals(server) && !RegexUtil.isIP(server)) {
                    save = false;
                    RunOnUiThreadUtil.showToast(this, "服务器地址错误");
                }
                if (!mPort.equals(port) && !RegexUtil.isPort(port)) {
                    save = false;
                    RunOnUiThreadUtil.showToast(this, "端口不存在");
                }
                if (save) {
                    appManager.setBaseUrl(server, port);
                    MdmpClient.getInstance().clear();
                    RunOnUiThreadUtil.showToast(this, "保存成功");
                }
                break;
            default:
                break;
        }
    }

}
