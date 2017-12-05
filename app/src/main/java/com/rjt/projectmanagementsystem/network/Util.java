package com.rjt.projectmanagementsystem.network;

import android.util.Log;

import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.Events;
import com.rjt.projectmanagementsystem.model.Projects;
import com.rjt.projectmanagementsystem.model.TeamMembers;
import com.rjt.projectmanagementsystem.model.TeamMembersItem;
import com.rjt.projectmanagementsystem.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jinming on 12/1/17.
 */



public class Util {
    private UserInfo userInfo;
    private Account account;
    private Events events;
    private static ApiInterface apiInterface;

    public interface UserInfoCallback {
        void onResponse(UserInfo info);
    }

    public interface  RegisterCallback {
        void onResponse(String s);
    }

    public interface ForgetPasswordCallback {
        void onResponse(Account account);
    }

    public interface EventsCallback {
        void onResponse(Events events);
    }

    public interface TeamListCallback {
        void onResponse(List<TeamMembersItem> list);
    }

    public interface CreateProjectCallback {
        void onResponse(String response);
    }

    public interface GetProjectsCallback {
        void onResponse(Projects response);
    }

    public interface UpdateProjectCallback {
        void onResponse(String response);
    }

    static {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    /**
     *
     * @return UserInfo Object;
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     *
     * @return Account Object;
     */
    public Account getAccount() {
        return account;
    }

    /**
     *
     * @return Events Object;
     */
    public Events getEvents() {
        return events;
    }

    /**
     *
     * @param first_name
     * @param last_name
     * @param email
     * @param mobile
     * @param password
     * @param company_size
     * @param your_role
     */
    public void register(final String first_name, final String last_name, final String email,
                         final String mobile, final String password, final String company_size,
                         final String your_role, final RegisterCallback registerCallback) {
        Call<String> registerCall = apiInterface.register(first_name, last_name, email, mobile, password, company_size, your_role);

        registerCall.enqueue(new Callback<String> () {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Register", "Success");
                registerCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Register", "Fail");
            }
        });
    }

    /**
     *
     * @param email
     * @param password
     */
    public void login(final String email, final String password,
                      final UserInfoCallback userInfoCallback) {
        Call<UserInfo> loginCall = apiInterface.login(email, password);
        loginCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                Log.i("Login", "Success");
                userInfo = response.body();
                userInfoCallback.onResponse(userInfo);
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.d("Login", "Fail");
            }
        });
    }


    /**
     *
     * @param email
     */
    public void forgetPassword(final String email, final ForgetPasswordCallback forgetPasswordCallback) {
        Call<Account> forgetPasswordCall = apiInterface.forgetPassword(email);
        forgetPasswordCall.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.i("ForgetPassWord", "Success");
                account = response.body();
                forgetPasswordCallback.onResponse(account);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d("ForgetPassword", "Fail");
            }
        });
    }

    /**
     *
     * @param status
     */
    public void getAllEvents(final String status, final EventsCallback eventsCallback) {
        Call<Events> getAllEventsCall = apiInterface.getAllEvents(status);
        getAllEventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Log.i("GetAllEvents", "Success");
                events = response.body();
                eventsCallback.onResponse(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.d("GetAllEvents", "Fail");
            }
        });
    }

    /**
     *
     * @param status
     */
    public void getCurrentEvents(final String status, final EventsCallback eventsCallback) {
        Call<Events> getCurrentEventsCall = apiInterface.getCurrentEvents(status);
        getCurrentEventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Log.i("GetCurrentEvents", "Success");
                events = response.body();
                eventsCallback.onResponse(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.d("GetCurrentEvents", "Fail");
            }
        });
    }

    /**
     *
     * @param status
     */
    public void getRecentEvents(final String status, final EventsCallback eventsCallback) {
        Call<Events> getRecentEventsCall = apiInterface.getRecentEvents(status);
        getRecentEventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Log.i("GetRecentEvents", "Success");
                events = response.body();
                eventsCallback.onResponse(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.d("GetRecentEvents", "Success");
            }
        });
    }

    /**
     *
     * @param teamListCallback
     */
    public void getTeamList(final TeamListCallback teamListCallback) {
        Call<TeamMembers> getTeamListCall = apiInterface.getTeamList();
        getTeamListCall.enqueue(new Callback<TeamMembers>() {
            @Override
            public void onResponse(Call<TeamMembers> call, Response<TeamMembers> response) {
                Log.i("GetTeamList", "Success");
                TeamMembers teamMembers = response.body();
                teamListCallback.onResponse(teamMembers.getTeamMembers());
            }

            @Override
            public void onFailure(Call<TeamMembers> call, Throwable t) {
                Log.d("GetTeamList", "Fail");
            }
        });
    }

    /**
     *
     * @param project_name
     * @param project_status
     * @param assign_to
     * @param project_desc
     * @param start_date
     * @param end_data
     * @param createProjectCallback
     */
    public void createProject(String project_name,
                              String project_status,
                              String assign_to,
                              String project_desc,
                              String start_date,
                              String end_data, final CreateProjectCallback createProjectCallback) {
        Call<String> createProjectsCall = apiInterface.createProject(project_name,
                project_status, assign_to, project_desc, start_date, end_data);
        createProjectsCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("CreateProject", "success");
                createProjectCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("CreateProject", "Fail");
            }
        });
    }

    /**
     *
     * @param getProjectsCallback
     */
    public void getProjects(final GetProjectsCallback getProjectsCallback) {
        final Call<Projects> getProjectsCall = apiInterface.getProjects();
        getProjectsCall.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                Log.i("getProjects", "Success");
                getProjectsCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Log.d("getProjects", "Fail");
            }
        });
    }

    /**
     *
     * @param project_id
     * @param project_status
     * @param updateProjectCallback
     */
    public void updateProject(String project_id, String project_status,
                              final UpdateProjectCallback updateProjectCallback) {
        Call<String> updateProjectCall = apiInterface.updateProject(project_id, project_status);
        updateProjectCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("UpdateProjects", "Success");
                updateProjectCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("UpdateProjects", "Fail");
            }
        });
    }
}

