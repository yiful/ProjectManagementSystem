package com.rjt.projectmanagementsystem.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Jinming on 12/1/17.
 */

public class ApiClient {
    public static String BASE_URL = "http://rjtmobile.com/aamir/pms/";

    private static Retrofit myInstance = null;

    public static Retrofit getRetrofit() {
        if (myInstance == null) {
            myInstance = new Retrofit
                    .Builder().baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return myInstance;
    }
}
