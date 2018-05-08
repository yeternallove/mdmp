package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.department.Department;
import com.eternallove.mdmp.model.user.department.DepartmentView;
import com.eternallove.mdmp.model.user.role.Role;
import com.eternallove.mdmp.model.user.role.RoleView;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.util.AppManager;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eternallove.mdmp.util.Constant.RS_CANCEL;
import static com.eternallove.mdmp.util.Constant.RS_UPDATE;

public class UserSettingActivity extends BaseActivity implements View.OnClickListener, EditActivity.OnSaveInterface {
    private final static String BUNDLE = "bundle" + UserSettingActivity.class.getSimpleName();

    private final static int TAG_ACCOUNT = 0x10;
    private final static int TAG_USER_NAME = 0x12;
    private final static int TAG_DEPARTMENT = 0x13;
    private final static int TAG_ROLE = 0x14;

    private UserView mUser;
    private AppManager appManager;
    private MdmpClient mClient;

    private String TITLE_ACCOUNT;
    private String TITLE_USER_NAME;
    private String TITLE_DEPARTMENT;
    private String TITLE_ROLE;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_btn_save)
    Button btnSave;
    @BindView(R.id.um_icon)
    View umIcon;
    @BindView(R.id.um_account)
    View umAccount;
    @BindView(R.id.um_account_tv)
    TextView tvAccount;
    @BindView(R.id.um_username)
    View umUserName;
    @BindView(R.id.um_username_tv)
    TextView tvUserName;
    @BindView(R.id.um_department)
    View umDepartment;
    @BindView(R.id.um_department_tv)
    TextView tvDepartment;
    @BindView(R.id.um_role)
    View umRole;
    @BindView(R.id.um_role_tv)
    TextView tvRole;
    @BindView(R.id.um_enable)
    View umEnable;
    @BindView(R.id.um_enable_sw)
    Switch swEnable;


    public static void actionStart(Activity activity, int requestCode, UserView user) {
        if (user != null) {
            Intent intent = new Intent();
            intent.setClass(activity, UserSettingActivity.class);
            intent.putExtra(BUNDLE, user.toString());
            activity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanage);
        ButterKnife.bind(this);
//        toolbar.setTitle("详情");
//        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        initData();
        //获取数据后进行更新  不更新
//        initView();
        initEvent();
    }

    private void initData() {
        TITLE_ACCOUNT = this.getResources().getString(R.string.item_account);
        TITLE_USER_NAME = this.getResources().getString(R.string.item_user_name);
        TITLE_DEPARTMENT = this.getResources().getString(R.string.item_department);
        TITLE_ROLE = this.getResources().getString(R.string.item_role);

        appManager = AppManager.getInstance(this);
        mClient = MdmpClient.getInstance();
        String userStr = getIntent().getStringExtra(BUNDLE);
        if (userStr != null) {
            getUser(userStr);
        }
    }

    private void initView() {
        tvAccount.setText(mUser.getAccount());
        tvUserName.setText(mUser.getUsername());
        tvDepartment.setText(mUser.getDepartment());
        tvRole.setText(mUser.getRole());
        swEnable.setChecked(mUser.getEnable().equals(UserBean.ENABLE));
    }

    private void initEvent() {
        btnSave.setOnClickListener(this);
        umIcon.setOnClickListener(this);
        umAccount.setOnClickListener(this);
        umUserName.setOnClickListener(this);
        umDepartment.setOnClickListener(this);
        umRole.setOnClickListener(this);
        swEnable.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mUser.setEnable(UserBean.ENABLE);
            } else {
                mUser.setEnable(UserBean.NOTENABLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        appManager.setListener(this);
        switch (view.getId()) {
//            case R.id.um_account:
//                break;
            case R.id.um_username:
                EditActivity.actionStart(this, TITLE_USER_NAME, mUser.getUsername(), TAG_USER_NAME);
                break;
            case R.id.um_department:
                onDepartment();
                break;
            case R.id.um_role:
                onRole();
                break;
            case R.id.edit_btn_save:
                save(mUser);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSave(String content, EditActivity activity) {
        switch (activity.getTag()) {
            case TAG_USER_NAME:
                mUser.setUsername(content);
                activity.saveSuccess();
                break;
            default:
                break;
        }
    }

    private void getUser(String user) {
        mUser = UserView.build(user);
        initView();
    }

    private void getUser(int userID) {
        mClient.getUser(userID).enqueue(new Callback<UserView>() {
            @Override
            public void onResponse(Call<UserView> call, Response<UserView> response) {
                UserView userView = response.body();
                if (userView != null) {
                    mUser = userView;
                    initView();
                }
            }

            @Override
            public void onFailure(Call<UserView> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(UserSettingActivity.this);
            }
        });
    }

    private void getRole() {
        mClient.getRole().enqueue(new Callback<List<RoleView>>() {
            @Override
            public void onResponse(Call<List<RoleView>> call, Response<List<RoleView>> response) {
                List<RoleView> roleViews = response.body();
                if (roleViews != null && roleViews.size() != 0) {
                    appManager.setRoles(roleViews);
                    UserSettingActivity.this.onDepartment();
                }else {
                    RunOnUiThreadUtil.showToast(UserSettingActivity.this,"角色加载失败");
                }
            }

            @Override
            public void onFailure(Call<List<RoleView>> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(UserSettingActivity.this);
            }
        });
    }


    private void getDepartment() {
        mClient.getDepartment().enqueue(new Callback<List<DepartmentView>>() {
            @Override
            public void onResponse(Call<List<DepartmentView>> call, Response<List<DepartmentView>> response) {
                List<DepartmentView> departmentViews = response.body();
                if (departmentViews != null && departmentViews.size() != 0) {
                    appManager.setDepartments(departmentViews);
                    UserSettingActivity.this.onDepartment();
                }else {
                    RunOnUiThreadUtil.showToast(UserSettingActivity.this,"部门加载失败");
                }
            }

            @Override
            public void onFailure(Call<List<DepartmentView>> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(UserSettingActivity.this);
            }
        });
    }

    private void onDepartment() {
        List<Department> list = appManager.getDepartments();
        if (list == null || list.size() == 0) {
            getDepartment();
        } else {
            String[] items = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                items[i] = list.get(i).getName();
            }
            RunOnUiThreadUtil.showAlertDialog(this, items, (dialogInterface, i) -> {
                mUser.setDepartment(list.get(i).getName());
                mUser.setDepartmentId(list.get(i).getId());
                tvDepartment.setText(mUser.getDepartment());
            });
        }
    }

    private void onRole() {
        List<Role> list = appManager.getRoles();
        if (list == null || list.size() == 0) {
            getRole();
        }else{
            String[] items = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                items[i] = list.get(i).getName();
            }
            RunOnUiThreadUtil.showAlertDialog(this, items, (dialogInterface, i) -> {
                mUser.setRole(list.get(i).getName());
                mUser.setRoleId(list.get(i).getId());
                tvRole.setText(mUser.getRole());
            });
        }
    }

    private void save(UserView user) {
        MdmpClient.getInstance().updateUser(user.getId(), user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String content, errorContent;
                try {
                    ResponseBody body = response.errorBody();
                    ResponseBody errorBody = response.errorBody();
                    if (body != null) {
                        content = body.string();
                        JSONObject json = new JSONObject(content);
                        if (json.has("errMsg")) {
                            RunOnUiThreadUtil.showToast(UserSettingActivity.this, json.getString("errMsg"));
                        }
                    } else if (errorBody != null) {
                        errorContent = errorBody.string();
                        if (errorContent != null && "".equals(errorContent)) {
                            RunOnUiThreadUtil.showToast(UserSettingActivity.this, errorContent);
                        }
                    } else {
                        setResult(RS_UPDATE);
                        RunOnUiThreadUtil.showToast(UserSettingActivity.this, "修改成功");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(UserSettingActivity.this);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RS_UPDATE) {
            initView();
        }
    }
}
