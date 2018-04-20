package com.eternallove.mdmp.ui.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.task.Taskdefined;
import com.eternallove.mdmp.ui.activities.DetailedActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: @author: eternallove @date: 2017/7/6 14:39
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.UserViewHolder> {
    private Context mContext;
    private List<Taskdefined> mData = new ArrayList<>();

    public TaskAdapter(Context context, List<Taskdefined> data) {
        this.mContext = context;
        if (data != null) this.mData = data;
    }

    public void updateData(List<Taskdefined> data) {
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
        Taskdefined taskdefined = mData.get(position);
        holder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.getMenuInflater()
                        .inflate(R.menu.menu_comment,popupMenu.getMenu());
                popupMenu.setGravity(Gravity.START);
                popupMenu.show();
            }
        });
        holder.itemTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailedActivity.actionStart(mContext);
            }
        });
        if(position%2==0){
            holder.imgIC.setColorFilter(mContext.getColor(R.color.blue));
        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_task_more)
        ImageView imgMore;
        @BindView(R.id.item_task)
        View itemTask;
        @BindView(R.id.item_task_ic)
        ImageView imgIC;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}