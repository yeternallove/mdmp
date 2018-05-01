package com.eternallove.mdmp.ui.fragments.MainFragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.activities.ChangePwdActivity;
import com.eternallove.mdmp.ui.activities.MeSettingActivity;
import com.eternallove.mdmp.ui.activities.SettingActivity;
import com.eternallove.mdmp.ui.activities.UserActivity;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.customview.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: eternallove
 * @date: 2017/6/12 22:08
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {
    private final static int REQ_SETTING = 1;

    private Context mContext;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.me_fragment_user)
    View viewUser;
    @BindView(R.id.me_fragment_setting)
    View viewSetting;
    @BindView(R.id.item_user_headview)
    CircleImageView circleImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, rootView);
        InitView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void InitView() {
        fab.setOnClickListener(this);
        viewUser.setOnClickListener(this);
        viewSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_fragment_user:
                UserActivity.actionStart(mContext);
                break;
            case R.id.me_fragment_setting:
//                SettingsActivity.actionStart(getActivity());
//                String[] items = {"1","2","3"};
//                //dialog参数设置
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  //先得到构造器
//                builder.setTitle("请选择"); //设置标题
//                //builder.setMessage("是否确认退出?"); //设置内容
//                //builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
//                //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        RunOnUiThreadUtil.showToast(getActivity(),items[which]);
//                    }
//                });
//                builder.create().show();
//                ChangePwdActivity.actionStart(getActivity());
                SettingActivity.actionStart((Activity) mContext, REQ_SETTING);
                break;
            case R.id.fab:
                MeSettingActivity.actionStart(mContext);
            default:
                break;
        }
    }
}
