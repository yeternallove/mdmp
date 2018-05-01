package com.eternallove.mdmp.api;

import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.department.DepartmentView;
import com.eternallove.mdmp.model.user.role.Role;
import com.eternallove.mdmp.model.user.role.RoleView;
import com.eternallove.mdmp.model.user.UserLogin;
import com.eternallove.mdmp.model.user.viewRight.ViewRightView;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/21 11:13
 */

public interface MdmpServer {
    //User
    //    @FormUrlEncoded
    @POST("account/login")
    Call<ResponseBody> login(@Body UserLogin data);

    @POST("user/{userId}?changePwd")
    Call<ResponseBody> changePwd(@Path("userId") Integer userId, @Body UserView userView);

    @GET("user")
    Call<ResponseBody> getUser();

    //Task
    @GET("task")
    Call<ResponseBody> getTask(@Query("type") String type,
                               @Query("currentPage") int currentPage,
                               @Query("pageSize") int pageSize
    );

    @GET("task/mySubmit")
    Call<ResponseBody> getMySubmit( @Query("currentPage") int currentPage,
                                    @Query("pageSize") int pageSize,
                                    @Query("processStatus") int processStatus);

    //user
    @GET("role")
    Call<List<RoleView>> getRole();

    @GET("department")
    Call<List<DepartmentView>> getDepartment();

    @GET("viewright")
    Call<List<ViewRightView>> getViewRight();
}
