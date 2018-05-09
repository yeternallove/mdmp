package com.eternallove.mdmp.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.activities.UserSettingActivity;
import com.eternallove.mdmp.ui.dialog.MessageDialog;
import com.eternallove.mdmp.ui.fragments.UserFragments.UserFragment;
import com.eternallove.mdmp.util.MD5;
import com.eternallove.mdmp.util.ResponseUtil;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description: @author: eternallove @date: 2017/7/6 14:39
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context mContext;
    private List<UserView> mData = new ArrayList<>();

    public UserAdapter(Context context, List<UserView> data) {
        this.mContext = context;
        if (data != null) this.mData = data;
    }

    public void updateData(List<UserView> data) {
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
        UserView userBean = mData.get(position);
        holder.mTvNickName.setText(userBean.getUsername());
        holder.mTvAccount.setText(userBean.getAccount());
        holder.mTvOther.setText(userBean.getPhone());
        holder.imgMore.setOnClickListener(view -> RunOnUiThreadUtil.showPopupMenu(mContext, view, item -> showMessageDialog(item.getItemId(), userBean)));
        holder.cardView.setOnClickListener(view -> UserSettingActivity.actionStart((Activity) mContext, UserFragment.REQ_SETTING, userBean));
        if (userBean.getEnable() == 0) {
            holder.imgNot.setVisibility(View.VISIBLE);
        } else {
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

    private boolean showMessageDialog(int itemId, UserView user) {

        MessageDialog messageDialog = new MessageDialog(mContext);
        switch (itemId) {
            case R.id.action_comment_delete:
                messageDialog.setTitle("删除用户");
                messageDialog.setMessage("删除后不可恢复，确定删除这条用户信息吗？");
                messageDialog.setYesOnclickListener("确认", note -> {
                    messageDialog.dismiss();
                    deleteUser(user);
                });
                break;
            case R.id.action_comment_comment:
                messageDialog.setTitle("重置密码");
                messageDialog.setMessage("将密码重置为初始密码？");
                messageDialog.setYesOnclickListener("确认", note -> {
                    messageDialog.dismiss();
                    resetPwd(user);
                });
                break;
            default:
                return false;
        }
        messageDialog.setNoOnclickListener("取消", messageDialog::dismiss);
        messageDialog.show();
        return true;
    }

    private void deleteUser(UserView user) {
        MdmpClient.getInstance().deleteUser(user.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO 随便一写 判断是否删除成功
                String content = ResponseUtil.getVoidContent(response.body(), response.errorBody());
                if ("".equals(content)) {
                    RunOnUiThreadUtil.showToast(mContext, "成功删除用户");
                    mData.remove(user);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(mContext);
            }
        });
    }

    private void resetPwd(UserView user) {
        user.setPassword(MD5.getMD5Str(user.getAccount()));
        MdmpClient.getInstance().updateUser(user.getId(), user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO 随便一写 判断是否重置密码成功
                String content = ResponseUtil.getVoidContent(response.body(), response.errorBody());
                if ("".equals(content)) {
                    RunOnUiThreadUtil.showToast(mContext, "成功重置用户密码");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}