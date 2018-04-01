package com.eternallove.mdmp.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.activities.LoginActivity;
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

public class MeFragment extends BaseFragment implements View.OnClickListener{
    private View rootView;
    private Context mContext;
    private CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.btn)
    FloatingActionButton fab;

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
//                LoginActivity.actionStart(getActivity());
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserActivity.actionStart(getActivity());
            }
        });
        CircleImageView view = (CircleImageView) rootView.findViewById(R.id.item_user_headview);
        Glide.with(mContext).load(R.drawable.ic_home_profile).into(view);

    }

    @Override
    public void onClick(View view) {

    }
}
