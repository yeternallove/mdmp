package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.introduce)
    TextView tvIntroduce;
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(v -> finish());
        //language=TEXT
        String str = "2018毕业设计\n" +
                "主数据是指系统间的共享数据，也称标准数据。\n" +
                "本课题基于开源的Talend MDM解决方案。\n" +
                "以此平台为基础，给用户提供一个可视化的、一体化的、流程化的主数据管理方式。\n" +
                "联系方式：yeternallove@163.com";
        tvIntroduce.setText(str);
    }
}
