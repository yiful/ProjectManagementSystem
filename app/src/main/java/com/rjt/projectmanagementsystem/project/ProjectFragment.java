package com.rjt.projectmanagementsystem.project;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.model.Projects;
import com.rjt.projectmanagementsystem.util.ApiClient;
import com.rjt.projectmanagementsystem.util.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    @BindView(R.id.btnCreate)
    Button btnCreate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private static final String TAG = "project fragment";
    private List<Projects.ProjectsBean> list;
    private ProjectAdapter adapter;

    public ProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataFromApi();

    }

    private void loadDataFromApi() {
        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Projects> call = apiService.getProjects();
        call.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                list = response.body().getProjects();
                adapter = new ProjectAdapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(requestCode==1){
                Log.i("fragment","success");
                //loadDataFromApi();
                int position = data.getIntExtra("position",0);
                String status = data.getStringExtra("status");
                Log.i("fragment", "position is "+position);
                Log.i("fragment", "status is "+status);
                list.get(position).setProjectstatus(status);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnCreate)
    public void onViewClicked() {
        Toast.makeText(getActivity(), "button clicked", Toast.LENGTH_SHORT).show();
        NewProjectFragment fragment = new NewProjectFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, fragment)
                .addToBackStack("addNewProjectFrag")
                .commit();
    }
}
