package com.eternallove.mdmp.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.task.MonitorTaskInfo;
import com.eternallove.mdmp.model.parameter.TaskAudit;
import com.eternallove.mdmp.ui.base.BaseActivity;
import com.eternallove.mdmp.ui.dialog.MessageDialog;
import com.eternallove.mdmp.util.DateUtil;
import com.eternallove.mdmp.util.RunOnUiThreadUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eternallove.mdmp.util.Constant.RS_CANCEL;
import static com.eternallove.mdmp.util.Constant.RS_UPDATE;

public class DetailedActivity extends BaseActivity implements View.OnClickListener {
    private final static String NAME_FLOW_ID = "name_flow_id";
    private final static String NAME_TASK_ID = "name_task_id";
    private final static String[][] HEAD = new String[][]{{"操作条目", "旧值", "新值"}, {"操作条目", "值"}};

    private MonitorTaskInfo monitor;
    private String flowId, taskId;

    private MdmpClient mdmpClient;
    private MessageDialog yesDialog, noDialog;
    private JSONObject beforeJson, afterJson;

    @BindView(R.id.sch_add_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detailed_1)
    TextView tvDetailed1;
    @BindView(R.id.detailed_2)
    TextView tvDetailed2;
    @BindView(R.id.detailed_3)
    TextView tvDetailed3;
    @BindView(R.id.detailed_4)
    TextView tvDetailed4;
    @BindView(R.id.gridLayout)
    GridLayout gridLayout;
    @BindView(R.id.task_tv_1)
    TextView tvHead1;
    @BindView(R.id.task_tv_2)
    TextView tvHead2;
    @BindView(R.id.task_tv_3)
    TextView tvHead3;
    @BindView(R.id.btn_pass)
    Button btnPass;
    @BindView(R.id.btn_no_pass)
    Button btnNoPass;
    @BindView(R.id.handle_btn)
    View handleBtn;

    /**
     * @param flowId 流程实例id
     * @param taskID 任务节点id
     * @param tag    标签
     */
    public static void actionStart(Activity context, String flowId, String taskID, int tag) {
        Intent intent = new Intent();
        intent.setClass(context, DetailedActivity.class);
        intent.putExtra(NAME_FLOW_ID, flowId);
        if (taskID != null) {
            intent.putExtra(NAME_TASK_ID, taskID);
        }
        context.startActivityForResult(intent, tag);
    }

    public static void actionStart(Activity context, String flowId, int tag) {
        actionStart(context, flowId, null, tag);
    }

    public static void actionStart(Context context, String flowId) {
        Intent intent = new Intent();
        intent.setClass(context, DetailedActivity.class);
        intent.putExtra(NAME_FLOW_ID, flowId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);
//        toolbar.setTitle("详情");
//        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        Intent intent = getIntent();
        flowId = intent.getStringExtra(NAME_FLOW_ID);
        taskId = intent.getStringExtra(NAME_TASK_ID);
        if (taskId != null) {
            handleBtn.setVisibility(View.VISIBLE);
        }
        mdmpClient = MdmpClient.getInstance();
        initView();
        monitor = new MonitorTaskInfo();
//        test();
        initData();
        updateData();
        initEvent();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pass:
                yesDialog.show();
                break;
            case R.id.btn_no_pass:
                noDialog.show();
                break;
            default:
                break;
        }
    }

    private void initView() {
        yesDialog = new MessageDialog(this, "通过", "通过审核");
        yesDialog.setYesOnclickListener("是", note -> {
            yesDialog.dismiss();
            handleTask(true, note);
        });
        noDialog = new MessageDialog(this, "不通过", "备注");
        noDialog.setYesOnclickListener("是", note -> {
            if (note != null && !"".equals(note)) {
                noDialog.dismiss();
                handleTask(false, note);
            } else {
                RunOnUiThreadUtil.showToast(this, "请填写不通过理由~");
            }

        });
    }

    private void initEvent() {
        setResult(RS_CANCEL);
        btnPass.setOnClickListener(this);
        btnNoPass.setOnClickListener(this);
    }

    private void initData() {
        if (monitor == null) {
            return;
        }
        tvDetailed1.setText(monitor.getMdConcepet());
        tvDetailed2.setText(monitor.getProcessor());
        tvDetailed3.setText(DateUtil.format(monitor.getOperateTime()));
        String operateType = monitor.getOperateType();
        if (operateType != null) {
            switch (operateType) {
                case "U":
                    operateType = "修改";
                    break;
                case "I":
                    operateType = "插入";
                    break;
                case "D":
                    operateType = "删除";
                    break;
                default:
                    break;
            }
        }
        tvDetailed4.setText(operateType);
        List<String> values = null;
        try {
            values = conversionValues();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (values == null) {
            values = new ArrayList<>();
        }
        initHead();
        //内容
        GridLayout.LayoutParams layoutParams;
        TextView textView;
        for (int i = 0; i < values.size(); i++) {
            layoutParams = new GridLayout.LayoutParams();
            layoutParams.setGravity(Gravity.CENTER);
//            layoutParams.rowSpec = GridLayout.spec(i/3+1);
//            layoutParams.columnSpec = GridLayout.spec(i%3);
            textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            int padding = (int) this.getResources().getDimension(R.dimen.icon_padding);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText(values.get(i));
            gridLayout.addView(textView);
        }
    }


    private List<String> conversionValues() throws JSONException {
        List<String> jsons = new ArrayList<>();
        String key, beforeValue, afterValue;
        String[] contents;
        beforeJson = null;
        afterJson = null;
        JSONArray jsonArray;
        if (monitor.getBeforeValue() != null) {
            jsonArray = new JSONArray(monitor.getBeforeValue());
            if (jsonArray.length() == 1) {
                beforeJson = jsonArray.getJSONObject(0);
            }
        }
        if (monitor.getAfterValue() != null) {
            jsonArray = new JSONArray(monitor.getAfterValue());
            if (jsonArray.length() == 1) {
                afterJson = jsonArray.getJSONObject(0);
            } else {
                RunOnUiThreadUtil.showToast(this, "暂不支持显示");
                return jsons;
            }
        }
        if (beforeJson != null && afterJson != null) {
            Iterator iterator = beforeJson.keys();
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                beforeValue = beforeJson.getString(key);
                afterValue = afterJson.getString(key);
                contents = beforeValue.split("#");
                jsons.add(contents[1]);
                jsons.add(contents[0]);
                contents = afterValue.split("#");
                jsons.add(contents[0]);
            }
        } else if (afterJson != null) {
            Iterator iterator = afterJson.keys();
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                afterValue = afterJson.getString(key);
                contents = afterValue.split("#");
                jsons.add(contents[1]);
                jsons.add(contents[0]);
            }
        }
        return jsons;
    }

    /**
     * 修改表头信息
     */
    private void initHead() {
        if (monitor == null) {
            return;
        }
        if (beforeJson != null && afterJson != null) {
            gridLayout.setColumnCount(3);
            tvHead1.setText(HEAD[0][0]);
            tvHead2.setText(HEAD[0][1]);
            tvHead3.setText(HEAD[0][2]);

        } else if (afterJson != null) {
            gridLayout.setColumnCount(2);
            tvHead1.setText(HEAD[1][0]);
            tvHead2.setText(HEAD[1][1]);
            gridLayout.removeView(tvHead3);
        }
    }

    private void updateData() {
        if (flowId == null) {
            return;
        }
        mdmpClient.getTask(flowId).enqueue(new Callback<MonitorTaskInfo>() {
            @Override
            public void onResponse(Call<MonitorTaskInfo> call, Response<MonitorTaskInfo> response) {
                MonitorTaskInfo monitorTaskInfo = response.body();
                if (monitorTaskInfo != null) {
                    monitor = monitorTaskInfo;
                    initData();
                }
            }

            @Override
            public void onFailure(Call<MonitorTaskInfo> call, Throwable t) {

            }
        });
    }

    private void handleTask(boolean advice, String note) {
        TaskAudit taskAudit = new TaskAudit(advice, afterJson.toString(), note);
        if (taskId == null) {
            return;
        }
        mdmpClient.handleTask(taskId, taskAudit).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //TODO 随便一写 判断是否处理成功
                if (response.errorBody() == null) {
                    RunOnUiThreadUtil.showToast(DetailedActivity.this, "提交成功");
                    handleBtn.setVisibility(View.GONE);
                    setResult(RS_UPDATE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                RunOnUiThreadUtil.showNetworkToast(DetailedActivity.this);
            }
        });
    }

    private void test() {
        monitor.setMdConcepet("student");
        monitor.setOperateType("U");
        //language=JSON
        String av = "[{\n" +
                "  \"name\":\"不知道#昵称#\",\n" +
                "  \"haha\":\"男#性别#\",\n" +
                "  \"asd\":\"qweqwe#测试#\",\n" +
                "  \"ni\": \"aa#q#\",\n" +
                "  \"ni1\": \"aa#w#\",\n" +
                "  \"ni2\": \"aa#e#\",\n" +
                "  \"ni3\": \"aa#r#\",\n" +
                "  \"ni4\": \"aa#t#\",\n" +
                "  \"ni5\": \"aa#y#\",\n" +
                "  \"ni6\": \"aa#u#\"\n" +
                "}]";
        String bv = "[{\n" +
                "  \"name\":\"知道不#昵称#\",\n" +
                "  \"haha\":\"男#性别#\",\n" +
                "  \"asd\":\"qweqwe#测试#\",\n" +
                "  \"ni\": \"bb#q#\",\n" +
                "  \"ni1\": \"bb#w#\",\n" +
                "  \"ni2\": \"bb#e#\",\n" +
                "  \"ni3\": \"bb#r#\",\n" +
                "  \"ni4\": \"bb#t#\",\n" +
                "  \"ni5\": \"bb#y#\",\n" +
                "  \"ni6\": \"bb#u#\"\n" +
                "}]";
        monitor.setAfterValue(av);
        monitor.setBeforeValue(bv);
    }
}
