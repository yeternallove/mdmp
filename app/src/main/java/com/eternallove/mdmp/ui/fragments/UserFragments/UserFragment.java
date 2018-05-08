package com.eternallove.mdmp.ui.fragments.UserFragments;

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
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.ui.activities.UserSettingActivity;
import com.eternallove.mdmp.ui.adapters.UserAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.util.gson.GsonHalper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
 * @date: 2018/3/20 16:19
 */
public class UserFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public final static int REQ_SETTING = 0x1;

    private Context mContext;
    private UserAdapter adapter;
    private List<UserView> mData;

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
        //TODO 测试数据

        adapter = new UserAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        getUser();
        //滑动监听
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (Math.abs(dy) > 5) {
//                    if (dy > 0) {
//                        fab.hide(true);
//                    } else {
//                        fab.show(true);
//                    }
//                }
//            }
//        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_SETTING && resultCode == RS_UPDATE) {
            getUser();
        }
    }

    @Override
    public void onRefresh() {
        getUser();
    }

    private void getUser() {
        MdmpClient.getInstance().getUser().enqueue(new Callback<List<UserView>>() {
            @Override
            public void onResponse(Call<List<UserView>> call, Response<List<UserView>> response) {
                List<UserView> users = response.body();
                if (users != null) {
                    mData.clear();
                    mData.addAll(users);
                    adapter.updateData(mData);
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<UserView>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}