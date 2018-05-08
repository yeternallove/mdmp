package com.eternallove.mdmp.ui.fragments.MainFragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.role.Permission;
import com.eternallove.mdmp.ui.activities.MeSettingActivity;
import com.eternallove.mdmp.ui.activities.SettingActivity;
import com.eternallove.mdmp.ui.activities.UserActivity;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.customview.CircleImageView;
import com.eternallove.mdmp.util.AppManager;

import java.util.List;

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
    private UserView userView;
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
        initView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView() {
        userView = AppManager.getInstance(getActivity()).getUserInfo();
        if (userView != null) {
            List<Permission> permissions = userView.getPageRightList();
            if (permissions != null && permissions.size() > 0) {
                for (Permission permission :permissions) {
                    //5 用户管理
                    if(permission.getId()==5){
                        viewUser.setVisibility(View.VISIBLE);
                    }
                }
            }
        }


        circleImageView.setOnClickListener(this);
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
                SettingActivity.actionStart((Activity) mContext, REQ_SETTING);
                break;
            case R.id.fab:
                MeSettingActivity.actionStart(mContext);
                break;
            case R.id.item_user_headview:
                MeSettingActivity.actionStart(mContext);
                break;
            default:
                break;
        }
    }
}
