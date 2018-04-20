package com.eternallove.mdmp.api;

import com.eternallove.mdmp.model.test.user.UserTest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/21 11:13
 */

public interface MdmpServer {
    @GET("department")
    Call<ResponseBody> getData();

    //    @FormUrlEncoded
    @POST("account/login")
    Call<ResponseBody> login(@Body UserTest data);

    @GET("user")
    Call<ResponseBody> getUser();

    @GET("task")
    Call<ResponseBody> get(@Query("type") String type,
                               @Query("currentPage") int currentPage,
                               @Query("pageSize") int pageSize
    );

}
