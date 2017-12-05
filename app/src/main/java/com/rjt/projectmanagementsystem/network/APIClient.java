package com.rjt.projectmanagementsystem.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Yifu on 12/3/2017.
 */

public class APIClient {
    private static final String BASE_URL = "http://rjtmobile.com/aamir/pms/";
    static private Retrofit retrofit;
    static public Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
