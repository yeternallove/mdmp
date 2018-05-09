package com.eternallove.mdmp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.interfaces.UserAttribute;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.department.Department;
import com.eternallove.mdmp.model.user.department.DepartmentView;
import com.eternallove.mdmp.model.user.role.Role;
import com.eternallove.mdmp.model.user.role.RoleView;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;
import com.eternallove.mdmp.ui.adapters.FragmentAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.dialog.MessageDialog;
import com.eternallove.mdmp.ui.fragments.UserFragments.UserAttributeFragment;
import com.eternallove.mdmp.ui.fragments.UserFragments.UserFragment;
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

public class UserActivity extends AppCompatActivity implements View.OnClickListener, UserAttributeFragment.OnListFragmentInteractionListener {

    //子页（fragment）标记
    public static final int USER_ATTRIBUTE_1 = 1;
    public static final int USER_ATTRIBUTE_2 = 2;
    public static final int USER_ATTRIBUTE_3 = 3;

    private final String[] titles = {"用户列表", "角色列表", "视图权限", "部门列表"};
    private FragmentAdapter adapter;
    private List<BaseFragment> fragments;
    private MdmpClient mdmpClient;
    @BindView(R.id.sch_add_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        //初始化TabLayout的title
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        //初始化ViewPager的数据集
        fragments = new ArrayList<>();
        fragments.add(new UserFragment());
        fragments.add(UserAttributeFragment.newInstance(USER_ATTRIBUTE_1));
        fragments.add(UserAttributeFragment.newInstance(USER_ATTRIBUTE_2));
        fragments.add(UserAttributeFragment.newInstance(USER_ATTRIBUTE_3));

        //创建ViewPager的adapter
        adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

        mdmpClient = MdmpClient.getInstance();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onClickMore(UserAttribute userAttribute) {
        MessageDialog messageDialog = new MessageDialog(this);
        messageDialog.setNoOnclickListener("取消", messageDialog::dismiss);
        messageDialog.setMessage("删除后不可恢复，确定删除这条信息吗？");
        switch (getFragmentTag(userAttribute)) {
            case USER_ATTRIBUTE_1:
                messageDialog.setTitle("删除角色");
                messageDialog.setYesOnclickListener("确认", note -> {
                    messageDialog.dismiss();
                    deleteRole(((Role) userAttribute).getId(), (UserAttributeFragment) fragments.get(USER_ATTRIBUTE_1));
                });
                break;
            case USER_ATTRIBUTE_2:
                break;
            case USER_ATTRIBUTE_3:
                messageDialog.setTitle("删除部门");
                messageDialog.setYesOnclickListener("确认", note -> {
                    messageDialog.dismiss();
                    deleteDepartment(((Department) userAttribute).getId(), (UserAttributeFragment) fragments.get(USER_ATTRIBUTE_3));
                });
                break;
            default:
                break;
        }
        messageDialog.show();
    }

    @Override
    public void onClickDetails(UserAttribute userAttribute) {
        switch (getFragmentTag(userAttribute)) {
            case USER_ATTRIBUTE_1:

                break;
            case USER_ATTRIBUTE_2:
                break;
            case USER_ATTRIBUTE_3:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.getItem(0).onActivityResult(requestCode, resultCode, data);
    }

    private int getFragmentTag(UserAttribute userAttribute) {
        int tag;
        if (userAttribute instanceof RoleView) {
            tag = USER_ATTRIBUTE_1;
        } else if (userAttribute instanceof ViewRightView) {
            tag = USER_ATTRIBUTE_2;
        } else if (userAttribute instanceof DepartmentView) {
            tag = USER_ATTRIBUTE_3;
        } else {
            tag = 0;
        }
        return tag;
    }

    private void deleteRole(Integer roleId, UserAttributeFragment fragment) {
        mdmpClient.deleteRole(roleId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO 随便一写 判断是否删除角色成功
                String content = ResponseUtil.getVoidContent(response.body(), response.errorBody());
                if ("".equals(content)) {
                    RunOnUiThreadUtil.showToast(UserActivity.this, "成功删除角色");
                    fragment.onRefresh();
                } else {
                    RunOnUiThreadUtil.showToast(UserActivity.this, content);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(UserActivity.this);
            }
        });
    }

    private void deleteDepartment(Integer departmentId, UserAttributeFragment fragment) {
        mdmpClient.deleteDepartment(departmentId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO 随便一写 判断是否删除部门成功
                String content = ResponseUtil.getVoidContent(response.body(), response.errorBody());
                if ("".equals(content)) {
                    RunOnUiThreadUtil.showToast(UserActivity.this, "成功删除部门");
                    fragment.onRefresh();
                } else {
                    RunOnUiThreadUtil.showToast(UserActivity.this, content);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(UserActivity.this);
            }
        });
    }
}
