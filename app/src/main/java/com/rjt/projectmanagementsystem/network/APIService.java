package com.rjt.projectmanagementsystem.network;

import com.rjt.projectmanagementsystem.model.Projects;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Yifu on 12/3/2017.
 */

public interface APIService {
    @GET("pms_projects.php")
    Call<Projects> getProjectList();

    @GET("pms_create_project.php")
    Call<String> createNewProject(@QueryMap Map<String, String> info);
}
