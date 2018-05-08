package com.eternallove.mdmp.ui.fragments.TaskFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.eternallove.mdmp.ui.dialog.MessageDialog;
import com.eternallove.mdmp.ui.dialog.PendingDialog;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eternallove.mdmp.util.Constant.RS_UPDATE;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/2 19:42
 */
public class TaskChildren1Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, TaskAdapter.OnTaskAdapterInteractionListener {

    private final static int DETAILED = 0x11;
    private final String TYPE = TaskDefined.PEND;

    private Context mContext;
    private TaskAdapter adapter;
    private List<TaskInterface> mData;

    private MdmpClient mdmpClient;
    private MessageDialog messageDialog;
    private PendingDialog pendingDialog;
    private String taskId;

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

        //Dialog
        pendingDialog = new PendingDialog(mContext, "正在提取...");
        messageDialog = new MessageDialog(mContext);
        messageDialog.setTitle("提取");
        messageDialog.setMessage("是否提取任务？");
        messageDialog.setNoOnclickListener("否", () -> {
            messageDialog.dismiss();
        });
        messageDialog.setYesOnclickListener("是", note -> {
            messageDialog.dismiss();
            pendingDialog.show();
            extractTask();
        });

        mdmpClient = MdmpClient.getInstance();
        //mRecyclerView
        mData = new ArrayList<>();
        //TODO 测试数据
        TaskDefined taskDefined = new TaskDefined();
        taskDefined.setBeforeTime(new Date());
        mData.add(taskDefined);

        adapter = new TaskAdapter(mContext, mData, TYPE, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        updateData();
        return view;
    }

    @Override
    public void onRefresh() {
        updateData();
    }

    private void updateData() {
        mdmpClient.getTasks(TYPE).enqueue(new Callback<List<TaskDefined>>() {
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
                            RunOnUiThreadUtil.showToast(mContext, body.string());
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

    private void extractTask() {
        mdmpClient.extractTask(taskId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO 随便一写 判断是否提取成功
                if (response.errorBody() == null) {
                    RunOnUiThreadUtil.showToast(mContext, "提取成功");
                    updateData();
                }
                pendingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pendingDialog.dismiss();
                RunOnUiThreadUtil.showNetworkToast(mContext);
            }
        });
    }

    @Override
    public void onClickMore(TaskInterface task, View view) {
        if (task instanceof TaskDefined) {
            taskId = ((TaskDefined) task).getTaskId();
            messageDialog.show();
        }
    }

    @Override
    public void onClickDetails(TaskInterface task) {
        if (task instanceof TaskDefined && !task.isShowMore()) {
            DetailedActivity.actionStart((Activity) mContext, task.getId(), ((TaskDefined) task).getTaskId(), DETAILED);
        } else {
            DetailedActivity.actionStart(mContext, task.getId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAILED && resultCode == RS_UPDATE) {
            updateData();
        }
    }
}


