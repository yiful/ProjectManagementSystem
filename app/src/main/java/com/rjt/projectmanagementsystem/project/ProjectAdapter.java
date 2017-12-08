package com.rjt.projectmanagementsystem.project;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.model.Projects;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yifu on 12/3/2017.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private Context context;
    private List<Projects.ProjectsBean> list;

    public ProjectAdapter(Context context, List<Projects.ProjectsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(list.get(position).getProjectname());
        holder.tvDes.setText(list.get(position).getProjectdesc());
        holder.tvStart.setText(list.get(position).getStartdate());
        String btnText = list.get(position).getProjectstatus();
        switch (btnText){
            case "New":
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorNew));
                break;
            case "completed":
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorCompleted));
                break;
            case "started":
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorStarted));
                break;
        }
        holder.btnStatus.setText(btnText);
        holder.tvEnd.setText(list.get(position).getEndstart());
        holder.tvAssign.setText(list.get(position).getAssignto());
        holder.btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show();
                UpdateStatusFragment fragment = UpdateStatusFragment.newInstance(position);
                Activity activity = (Activity) context;
                FragmentManager ft = activity.getFragmentManager();
                fragment.setTargetFragment(ft.findFragmentByTag("ProjectFragment"),1);
                fragment.show(ft,"showDialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDes)
        TextView tvDes;
        @BindView(R.id.btnStatus)
        Button btnStatus;
        @BindView(R.id.tvAssign)
        TextView tvAssign;
        @BindView(R.id.tvStart)
        TextView tvStart;
        @BindView(R.id.tvEnd)
        TextView tvEnd;
        @BindView(R.id.cardView)
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
