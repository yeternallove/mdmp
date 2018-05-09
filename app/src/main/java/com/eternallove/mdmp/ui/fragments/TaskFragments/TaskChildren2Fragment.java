package com.eternallove.mdmp.ui.fragments.TaskFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.interfaces.TaskInterface;
import com.eternallove.mdmp.model.task.TaskDefined;
import com.eternallove.mdmp.ui.activities.DetailedActivity;
import com.eternallove.mdmp.ui.adapters.TaskAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;
import com.eternallove.mdmp.util.gson.GsonHalper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/2 19:42
 */
public class TaskChildren2Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, TaskAdapter.OnTaskAdapterInteractionListener {

    private final String TYPE = TaskDefined.COMPLETE;

    private Context mContext;
    private TaskAdapter adapter;
    private List<TaskInterface> mData;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        ButterKnife.bind(this, view);
        //swipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) (mContext.getResources().getDisplayMetrics().density * 64));
        swipeRefreshLayout.setOnRefreshListener(this);
        //mRecyclerView
        mData = new ArrayList<>();
        // 测试数据
        mData.add(new TaskDefined());

        adapter = new TaskAdapter(getActivity(), mData, TYPE, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        updateData();
        return view;
    }

    @Override
    public void onRefresh() {
        updateData();
    }

    private void updateData() {
        MdmpClient.getInstance().getTasks(TYPE).enqueue(new Callback<List<TaskDefined>>() {
            @Override
            public void onResponse(Call<List<TaskDefined>> call, Response<List<TaskDefined>> response) {
                List<TaskDefined> taskDefineds = response.body();
                if (taskDefineds != null) {
                    mData.clear();
                    mData.addAll(taskDefineds);
                    adapter.updateData(mData);
                } else {
                    ResponseBody body = response.errorBody();
                    try {
                        if (body != null) {
                            RunOnUiThreadUtil.showToast(getActivity(), body.string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<TaskDefined>> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(mContext);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClickMore(TaskInterface task, View view) {
    }

    @Override
    public void onClickDetails(TaskInterface task) {
        DetailedActivity.actionStart(mContext, task.getId());
    }
}


