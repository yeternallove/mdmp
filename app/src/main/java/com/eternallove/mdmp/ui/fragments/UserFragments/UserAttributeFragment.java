package com.eternallove.mdmp.ui.fragments.UserFragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.department.DepartmentView;
import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.model.user.role.RoleView;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;
import com.eternallove.mdmp.ui.activities.UserActivity;
import com.eternallove.mdmp.ui.adapters.UserAttributeAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/20 16:19
 */
public class UserAttributeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String FRAGMENT_TAG = "column-count";

    private int mTag;
    private Context mContext;
    private UserAttributeAdapter adapter;
    private List<UserAttribute> mData = new ArrayList<>();
    private OnListFragmentInteractionListener mListener;
    private MdmpClient mClient;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @SuppressWarnings("unused")
    public static UserAttributeFragment newInstance(int tag) {
        UserAttributeFragment fragment = new UserAttributeFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_TAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            mTag = getArguments().getInt(FRAGMENT_TAG);
        }
        //swipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        mClient = MdmpClient.getInstance();
        //mRecyclerView
        adapter = new UserAttributeAdapter(getActivity(), mData, mListener);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        updateData(mTag);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        updateData(mTag);
    }

    public interface OnListFragmentInteractionListener {
        void onClickMore(UserAttribute userAttribute);

        void onClickDetails(UserAttribute userAttribute);
    }

    private void updateData(int tag) {
        switch (tag) {
            case UserActivity.USER_ATTRIBUTE_1:
                getRole();
                break;
            case UserActivity.USER_ATTRIBUTE_2:
                getViewRight();
                break;
            case UserActivity.USER_ATTRIBUTE_3:
                getDepartment();
                break;
            default:
                break;

        }
    }

    private void getRole() {
        mClient.getRole().enqueue(new Callback<List<RoleView>>() {
            @Override
            public void onResponse(Call<List<RoleView>> call, Response<List<RoleView>> response) {
                List<RoleView> roleViews = response.body();
                if (roleViews != null) {
                    mData.clear();
                    mData.addAll(roleViews);
                }
                updateData();
            }

            @Override
            public void onFailure(Call<List<RoleView>> call, Throwable t) {

            }
        });
    }

    private void getViewRight() {
        mClient.getViewRight().enqueue(new Callback<List<ViewRightView>>() {
            @Override
            public void onResponse(Call<List<ViewRightView>> call, Response<List<ViewRightView>> response) {
                List<ViewRightView> views = response.body();
                if (views != null) {
                    mData.clear();
                    mData.addAll(views);
                }
                updateData();
            }

            @Override
            public void onFailure(Call<List<ViewRightView>> call, Throwable t) {

            }
        });
    }

    private void getDepartment() {
        mClient.getDepartment().enqueue(new Callback<List<DepartmentView>>() {
            @Override
            public void onResponse(Call<List<DepartmentView>> call, Response<List<DepartmentView>> response) {
                List<DepartmentView> departmentViews = response.body();
                if (departmentViews != null) {
                    mData.clear();
                    mData.addAll(departmentViews);
                }
                updateData();
            }

            @Override
            public void onFailure(Call<List<DepartmentView>> call, Throwable t) {

            }
        });
    }

    private void updateData() {
        adapter.updateData(mData);
        swipeRefreshLayout.setRefreshing(false);
    }
}