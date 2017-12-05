package com.rjt.projectmanagementsystem.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.network.ApiClient;
import com.rjt.projectmanagementsystem.network.ApiService;

import java.util.HashMap;
import java.util.Map;

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
public class NewProjectFragment extends Fragment {


    @BindView(R.id.dpStart)
    EditText dpStart;
    @BindView(R.id.dpEnd)
    EditText dpEnd;
    @BindView(R.id.etAssign)
    EditText etAssign;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;

    public NewProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_project, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        submitNetworkRequest();
    }

    private void submitNetworkRequest() {
        Map<String,String> map = new HashMap<>();
        final String[] result = new String[1];
        map.put("project_name", etName.getText().toString());
        map.put("project_status", "New");
        map.put("assigned_to", etAssign.getText().toString());
        map.put("project_desc", etDes.getText().toString());
        map.put("start_date", dpStart.getText().toString());
        map.put("end_date", dpEnd.getText().toString());
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<String> call = apiService.createNewProject(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                result[0] = response.body();
                Toast.makeText(getActivity(), result[0], Toast.LENGTH_LONG).show();
                Log.i("new", "success");
                getActivity().onBackPressed();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("new", t.toString());
            }
        });

    }
}
