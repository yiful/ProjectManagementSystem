package com.rjt.projectmanagementsystem.project;


import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.util.ApiClient;
import com.rjt.projectmanagementsystem.util.ApiInterface;

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
public class UpdateStatusFragment extends DialogFragment {


    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.radio1)
            RadioButton radioButton1;
    @BindView(R.id.radio2)
            RadioButton radioButton2;
    @BindView(R.id.radio3)
            RadioButton radioButton3;
    Unbinder unbinder;
    int position;

    public UpdateStatusFragment() {
        // Required empty public constructor
    }

    public static UpdateStatusFragment newInstance(int position){
        UpdateStatusFragment fragment = new UpdateStatusFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt("position",0);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_update_status, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                final String status = radioButton.getText().toString();
                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                Call<String> call = apiInterface.updateProject(String.valueOf(position+1), status);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getActivity(), response.body(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("status", status);
                        intent.putExtra("position", position);
                        getTargetFragment().onActivityResult(1,1,intent);
                        dismiss();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

/*    @OnClick(R.id.btnConfirm)
    public void onViewClicked() {
        String status = (view radioGroup.getCheckedRadioButtonId()).getText()
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<String> call = apiInterface.updateProject(String.valueOf(position+1), );
    }*/
}
