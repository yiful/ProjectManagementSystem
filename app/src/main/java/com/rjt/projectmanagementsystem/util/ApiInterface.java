package com.rjt.projectmanagementsystem.util;


import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.Events;
import com.rjt.projectmanagementsystem.model.Projects;
import com.rjt.projectmanagementsystem.model.TeamMembers;
import com.rjt.projectmanagementsystem.model.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Jinming on 12/1/17.
 */

public interface ApiInterface {
    /**
     *
     * @param first_name
     * @param last_name
     * @param email
     * @param mobile
     * @param password
     * @param company_size
     * @param your_role
     * @return successfully registered if succeed otherwise Email already exists
     */
    @GET("pms_reg.php")
    Call<String> register(@Query("first_name") String first_name,
                          @Query("last_name") String last_name,
                          @Query("email") String email,
                          @Query("mobile") String mobile,
                          @Query("password") String password,
                          @Query("company_size") String company_size,
                          @Query("your_role") String your_role);

    /**
     *
     * @param email
     * @param password
     * @return UserInfo object if succeed otherwise { "msg":[2]}
     */
    @GET("pms_login.php")
    Call<UserInfo> login(@Query("email") String email, @Query("password") String password);

    /**
     *
     * @param email
     * @return Account object otherwise {"msg":["Email is not register"]}

     */
    @GET("pms_forgot_pass.php")
    Call<Account> forgetPassword(@Query("email") String email);

    /**
     *
     * @param status
     * @return List<Events> if succeed otherwise {"msg":["No Data"]}</>
     */
    @GET("pms_events.php")
    Call<Events> getAllEvents(@Query("status") String status);

    /**
     *
     * @param status
     * @return List<Events> if succeed otherwise {"msg":["No Data"]}</>
     */
    @GET("pms_events_status.php")
    Call<Events> getCurrentEvents(@Query("status") String status);

    /**
     *
     * @param status
     * @return List<Events> if succeed otherwise {"msg":["No Data"]}</>
     */
    @GET("pms_events_status.php")
    Call<Events> getRecentEvents(@Query("status") String status);

    /**
     *
     * @return
     */
    @GET("pms_project_team_list.php")
    Call<TeamMembers> getTeamList();


    @GET("pms_create_project.php")
    Call<String> createProject(@QueryMap Map<String, String> map);
    /*(@Query("project_name") String project_name,
                               @Query("project_status") String project_status,
                               @Query("assigned_to") String assign_to,
                               @Query("project_desc") String project_desc,
                               @Query("start_date") String start_date,
                               @Query("end_date") String end_date);*/


    /**
     *
     * @return
     */
    @GET("pms_projects.php")
    Call<Projects> getProjects();

    /**
     *
     * @param project_id
     * @param project_status
     * @return status updated if success otherwise "Unsucessful! error"
     */
    @GET("pms_update_project_status.php")
    Call<String> updateProject(@Query("project_id") String project_id, @Query("project_status") String project_status);
}
