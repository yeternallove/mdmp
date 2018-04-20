package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeSettingActivity extends BaseActivity {

    @BindView(R.id.sch_add_toolbar)
    Toolbar toolbar;
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MeSettingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_setting);
        ButterKnife.bind(this);
//        toolbar.setTitle("22333");
//        setSupportActionBar(toolbar);

//        tvTitle.setText("2233");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
