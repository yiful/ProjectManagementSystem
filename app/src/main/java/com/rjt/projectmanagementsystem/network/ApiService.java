package com.rjt.projectmanagementsystem.network;

import com.rjt.projectmanagementsystem.model.ForgotPwdResponse;
import com.rjt.projectmanagementsystem.model.Projects;
import com.rjt.projectmanagementsystem.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by rashmi on 12/2/2017.
 */

public interface ApiService {

    //register call
    @GET("pms_reg.php")
    Call<String> getRegisterResponse(@Query("first_name") String firt_name,
                                     @Query("last_name") String last_name,
                                     @Query("email") String email,
                                     @Query("mobile") String mobile,
                                     @Query("password") String password,
                                     @Query("company_size") String company_size,
                                     @Query("your_role") String your_role);


    //login call
    @GET("pms_login.php")
    Call<User> getLoginResponse(@Query("email") String email,
                                @Query("password") String password);

    @GET("pms_forgot_pass.php")
    Call<ForgotPwdResponse> getForgotPwdResponse(@Query("email") String email);

    @GET("pms_projects.php")
    Call<Projects> getProjectList();

    @GET("pms_create_project.php")
    Call<String> createNewProject(@QueryMap Map<String, String> info);
}
