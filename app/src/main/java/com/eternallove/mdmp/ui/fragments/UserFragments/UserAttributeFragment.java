package com.eternallove.mdmp.ui.fragments.UserFragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternallove.mdmp.R;
import com.eternallove.mdmp.api.MdmpClient;
import com.eternallove.mdmp.model.test.user.UserTest;
import com.eternallove.mdmp.model.user.UserAttribute;
import com.eternallove.mdmp.ui.adapters.UserAttributeAdapter;
import com.eternallove.mdmp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/20 16:19
 */
public class UserAttributeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private UserAttributeAdapter adapter;
    private List<UserAttribute> mData;

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
        mData.add(new UserAttribute("2233", "123", "hahaha"));
        mData.add(new UserAttribute("123123", "123", "hahaha"));
        mData.add(new UserAttribute("asdah", "123", "hahaha"));

        adapter = new UserAttributeAdapter(getActivity(), mData);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
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
        MdmpClient.getInstance().login(new UserTest()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.headers());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
//        Call<ResponseBody> call = MdmpClient.getInstance().getmUser();
//        //开始异步请求
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                ResponseBody body = response.body();
//                if (body != null) {
//                    mData.clear();
//                    try {
//                        JSONArray jsonArray = new JSONArray(body.string());
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject user = jsonArray.getJSONObject(i);
//                            UserBean userBean = new UserBean();
//                            userBean.setUsername(user.getString("username"));
//                            userBean.setAccount(user.getString("account"));
//                            userBean.setPhone(user.getString("createTime"));
//                            mData.add(userBean);
//                        }
//                        adapter.updateData(mData);
//                    } catch (JSONException | IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 2000);

    }


}