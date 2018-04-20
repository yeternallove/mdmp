package com.eternallove.mdmp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.ui.activities.UserSettiongActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: @author: eternallove @date: 2017/7/6 14:39
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context mContext;
    private List<UserBean> mData = new ArrayList<>();

    public UserAdapter(Context context, List<UserBean> data) {
        this.mContext = context;
        if (data != null) this.mData = data;
    }

    public void updateData(List<UserBean> data) {
        if (data != null) mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserBean userBean = mData.get(position);
        holder.mTvNickName.setText(userBean.getUsername());
        holder.mTvAccount.setText(userBean.getAccount());
        holder.mTvOther.setText(userBean.getPhone());
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSettiongActivity.actionStart(mContext);
            }
        });
        if(userBean.getEnable() == 0 ){
            holder.imgNot.setVisibility(View.VISIBLE);
        }else{
            holder.imgNot.setVisibility(View.INVISIBLE);
        }

    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_tv_nickname)
        TextView mTvNickName;
        @BindView(R.id.item_user_tv_account)
        TextView mTvAccount;
        @BindView(R.id.item_user_tv_other)
        TextView mTvOther;
        @BindView(R.id.item_user_more)
        ImageView imgMore;
        @BindView(R.id.item_user_img_not)
        ImageView imgNot;
        @BindView(R.id.cardView)
        View cardView;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}