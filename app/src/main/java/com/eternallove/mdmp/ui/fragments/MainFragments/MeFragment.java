package com.eternallove.mdmp.ui.fragments.MainFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.activities.MeSettingActivity;
import com.eternallove.mdmp.ui.activities.SettingsActivity;
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
    private View rootView;
    private Context mContext;
    private CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.btn)
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
        rootView = inflater.inflate(R.layout.fragment_me, container, false);
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

        collapsing_toolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
//        collapsing_toolbar.setTitle("个人中心");
//        swt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(),"还没设置呢", Toast.LENGTH_SHORT).show();
//                swt.setChecked(false);
//                Login2Activity.actionStart(getActivity());
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeSettingActivity.actionStart(getActivity());
            }
        });
//        Glide.with(mContext).load(R.drawable.ic_home_profile).into(circleImageView);
        viewUser.setOnClickListener(this);
        viewSetting.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_fragment_user:
                UserActivity.actionStart(getActivity());
                break;
            case R.id.me_fragment_setting:
                SettingsActivity.actionStart(getActivity());
                break;
            default:
                break;
        }
    }
}
