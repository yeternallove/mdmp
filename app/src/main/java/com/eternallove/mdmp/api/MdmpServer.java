package com.eternallove.mdmp.api;

import com.eternallove.mdmp.model.user.UserTest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/21 11:13
 */

public interface Server {
    @GET("mdmp/department")
    Call<ResponseBody> getData();

//    @FormUrlEncoded
    @POST("mdmp/account/login")
    Call<ResponseBody> edit(@Body UserTest data);

}