package com.eternallove.mdmp.api;

import com.eternallove.mdmp.model.task.MonitorTaskInfo;
import com.eternallove.mdmp.model.parameter.TaskAudit;
import com.eternallove.mdmp.model.task.TaskDefined;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.model.user.department.DepartmentView;
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

    /**
     * Login Logout
     */
    //    @FormUrlEncoded
    @POST("account/login")
    Call<ResponseBody> login(@Body UserLogin data);

    @POST("account/logout")
    Call<ResponseBody> logout();

    /**
     * User
     */
    @GET("user/{userId}")
    Call<UserView> getUser(@Path("userId") Integer userId);

    @GET("user")
    Call<List<UserView>> getUser();

    @POST("user/{userId}?changePwd")
    Call<ResponseBody> changePwd(@Path("userId") Integer userId, @Body UserView userView);

    @POST("user/{userId}?update")
    Call<ResponseBody> updateUser(@Path("userId") Integer userId, @Body UserView userView);

    /**
     * User Attribute
     */
    @GET("role")
    Call<List<RoleView>> getRole();

    @GET("department")
    Call<List<DepartmentView>> getDepartment();

    @GET("viewright")
    Call<List<ViewRightView>> getViewRight();

    /**
     * Task
     */
    @GET("task")
    Call<List<TaskDefined>> getTasks(@Query("type") String type,
                                     @Query("currentPage") int currentPage,
                                     @Query("pageSize") int pageSize
    );

    @GET("task?size")
    Call<ResponseBody> getTaskSize(@Query("type") String type,
                                @Query("currentPage") int currentPage,
                                @Query("pageSize") int pageSize
    );
    @GET("task/mySubmit")
    Call<ResponseBody> getMySubmit(@Query("currentPage") int currentPage,
                                   @Query("pageSize") int pageSize,
                                   @Query("processStatus") int processStatus);
    @GET("task/mySubmit?size")
    Call<ResponseBody> getMyTaskSize(@Query("currentPage") int currentPage,
                                   @Query("pageSize") int pageSize,
                                   @Query("processStatus") int processStatus);

    @GET("task/{id}")
    Call<MonitorTaskInfo> getTask(@Path("id") String userId);

    /**
     * @param taskId 任务节点id
     */
    @POST("task/{id}?extract")
    Call<ResponseBody> extractTask(@Path("id") String taskId);

    /**
     * @param taskId 任务节点id
     */
    @POST("task/{id}?audit")
    Call<ResponseBody> handleTask(@Path("id") String taskId, @Body TaskAudit taskAudit);


}
