package com.rjt.projectmanagementsystem.network;

/**
 * Created by Jinming on 12/2/17.
 */


import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.Events;
import com.rjt.projectmanagementsystem.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
}

