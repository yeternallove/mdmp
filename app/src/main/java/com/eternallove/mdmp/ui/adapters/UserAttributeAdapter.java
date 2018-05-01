package com.eternallove.mdmp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.ui.fragments.UserFragments.UserAttributeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: 用户属性 角色 视图权限 部门
 * @author: eternallove
 * @date: 2017/7/6 14:39
 */
public class UserAttributeAdapter extends RecyclerView.Adapter<UserAttributeAdapter.UserViewHolder> {
    private Context mContext;
    private List<UserAttribute> mData = new ArrayList<>();
    private final UserAttributeFragment.OnListFragmentInteractionListener mListener;

    public UserAttributeAdapter(Context context, List<UserAttribute> data, UserAttributeFragment.OnListFragmentInteractionListener listener) {
        this.mContext = context;
        if (data != null) this.mData = data;
        this.mListener = listener;
    }

    public void updateData(List<UserAttribute> data) {
        if (data != null) mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user_attribute, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserAttribute userAttribute = mData.get(position);
        holder.tvHeadings.setText(userAttribute.getHeadings());
        holder.tvSecondary.setText(userAttribute.getSecondary());
        holder.tvOther.setText(userAttribute.getOther());
        holder.cardView.setOnClickListener(view -> mListener.onClickDetails(userAttribute));
        holder.imgMore.setOnClickListener(view -> mListener.onClickMore(userAttribute));
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_tv_headings)
        TextView tvHeadings;
        @BindView(R.id.item_user_tv_secondary)
        TextView tvSecondary;
        @BindView(R.id.item_user_tv_other)
        TextView tvOther;
        @BindView(R.id.item_user_more)
        ImageView imgMore;
        @BindView(R.id.cardView)
        CardView cardView;

        UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}