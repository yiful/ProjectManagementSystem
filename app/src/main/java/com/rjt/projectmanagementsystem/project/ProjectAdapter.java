package com.rjt.projectmanagementsystem.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getProjectname());
        holder.tvDes.setText(list.get(position).getProjectdesc());
        holder.tvStatus.setText(list.get(position).getProjectstatus());
        holder.tvStart.setText(list.get(position).getStartdate());
        holder.tvEnd.setText(list.get(position).getEndstart());
        holder.tvAssign.setText(list.get(position).getAssignto());
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
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvAssign)
        TextView tvAssign;
        @BindView(R.id.tvStart)
        TextView tvStart;
        @BindView(R.id.tvEnd)
        TextView tvEnd;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
