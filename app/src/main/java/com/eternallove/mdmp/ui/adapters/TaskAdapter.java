package com.eternallove.mdmp.ui.adapters;

import android.content.Context;
import android.graphics.ColorFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.interfaces.TaskInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: eternallove
 * @date: 2017/7/6 14:39
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.UserViewHolder> {
    private Context mContext;
    private List<TaskInterface> mData = new ArrayList<>();

    private final String mType;
    private final OnTaskAdapterInteractionListener mListener;

    /**
     * @param context  *
     * @param data     *
     * @param type     类型，无类型则可填null
     * @param listener *
     */
    public TaskAdapter(Context context, List<TaskInterface> data, String type, OnTaskAdapterInteractionListener listener) {
        this.mContext = context;
        if (data != null) this.mData = data;
        mListener = listener;
        mType = type;
    }

    public void updateData(List<TaskInterface> data) {
        if (data != null) mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_task, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        TaskInterface task = mData.get(position);
        holder.tvMDMName.setText(task.getMDMName());
        holder.tvTask1.setText(task.getTask1(mType));
        holder.tvTask2.setText(task.getTask2(mType));
        holder.tvTask3.setText(task.getTask3(mType));
        holder.tvFlow.setText(task.getFlow());
        switch (task.getType()) {
            case TaskInterface.TYPE_I:
                holder.imgIC.setColorFilter(mContext.getResources().getColor(R.color.blue));
                break;
            case TaskInterface.TYPE_U:
                holder.imgIC.setColorFilter(mContext.getResources().getColor(R.color.green));
                break;
            case TaskInterface.TYPE_D:
                holder.imgIC.setColorFilter(mContext.getResources().getColor(R.color.red));
                break;
            default:
                break;
        }
        if (task.isShowMore()) {
            holder.imgMore.setVisibility(View.VISIBLE);
            holder.imgMore.setOnClickListener(view -> mListener.onClickMore(task, view));
        } else {
            holder.imgMore.setVisibility(View.INVISIBLE);
        }
        holder.itemTask.setOnClickListener(view -> mListener.onClickDetails(task));

    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_task_more)
        ImageView imgMore;
        @BindView(R.id.item_task)
        View itemTask;
        @BindView(R.id.item_task_ic)
        ImageView imgIC;
        @BindView(R.id.item_task_tv_1)
        TextView tvTask1;
        @BindView(R.id.item_task_tv_2)
        TextView tvTask2;
        @BindView(R.id.item_task_tv_3)
        TextView tvTask3;
        @BindView(R.id.item_task_tv_mdmname)
        TextView tvMDMName;
        @BindView(R.id.item_task_tv_flow)
        TextView tvFlow;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnTaskAdapterInteractionListener {
        void onClickMore(TaskInterface task, View view);

        void onClickDetails(TaskInterface task);
    }
}