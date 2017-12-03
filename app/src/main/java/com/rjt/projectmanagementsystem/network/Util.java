package com.rjt.projectmanagementsystem.network;

import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.Events;
import com.rjt.projectmanagementsystem.model.UserInfo;

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

    static {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    /**
     * Login callback interface
     */
    public interface LoginCallback {
        void onResponse(UserInfo info);
    }

    /**
     * Register callback interface
     */
    public interface  RegisterCallback {
        void onResponse(String s);
    }

    /**
     * Forget password callback interface
     */
    public interface ForgetPasswordCallback {
        void onResponse(Account account);
    }

    /**
     * Events callback interface
     */
    public interface EventsCallback {
        void onResponse(Events events);
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
     * @param registerCallback  interface, need to be override when register is called
     */
    public void register(final String first_name, final String last_name, final String email,
                         final String mobile, final String password, final String company_size,
                         final String your_role, final RegisterCallback registerCallback) {
        Call<String> registerCall = apiInterface.register(first_name, last_name, email, mobile, password, company_size, your_role);

        registerCall.enqueue(new Callback<String> () {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                registerCallback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }


    /**
     *
     * @param email
     * @param password
     * @param loginCallback this interface needs to be override, when login is called
     */
    public void login(final String email, final String password, final LoginCallback loginCallback) {
        Call<UserInfo> loginCall = apiInterface.login(email, password);
        loginCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                userInfo = response.body();
                loginCallback.onResponse(userInfo);
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
            }
        });
    }

    /**
     *
     * @param email
     * @param forgetPasswordCallback this interface needs to be override, when forgetPassword is called
     */
    public void forgetPassword(final String email, final ForgetPasswordCallback forgetPasswordCallback) {
        Call<Account> forgetPasswordCall = apiInterface.forgetPassword(email);
        forgetPasswordCall.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                account = response.body();
                forgetPasswordCallback.onResponse(account);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    /**
     *
     * @param status
     * @param eventsCallback this interface needs to be override, when getAllEvents is called
     */
    public void getAllEvents(final String status, final EventsCallback eventsCallback) {
        Call<Events> getAllEventsCall = apiInterface.getAllEvents(status);
        getAllEventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                events = response.body();
                eventsCallback.onResponse(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });
    }

    /**
     *
     * @param status
     * @param eventsCallback this interface needs to be override, when getCurrentEvents is called
     */
    public void getCurrentEvents(final String status, final EventsCallback eventsCallback) {
        Call<Events> getCurrentEventsCall = apiInterface.getCurrentEvents(status);
        getCurrentEventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                events = response.body();
                eventsCallback.onResponse(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });
    }

    /**
     *
     * @param status
     * @param eventsCallback this interface needs to be override, when getRecentEvents is called
     */
    public void getRecentEvents(final String status, final EventsCallback eventsCallback) {
        Call<Events> getRecentEventsCall = apiInterface.getRecentEvents(status);
        getRecentEventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                events = response.body();
                eventsCallback.onResponse(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });
    }
}

