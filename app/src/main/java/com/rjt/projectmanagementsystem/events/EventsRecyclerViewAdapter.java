package com.rjt.projectmanagementsystem.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.model.Event;
import com.rjt.projectmanagementsystem.model.Events;

/**
 * Created by Jinming on 12/2/17.
 */

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.CustomViewHolder> {
    private Events events;
    private Context context;
    private LayoutInflater inflater;
    private EventClickListener eventClickListener;

    public EventsRecyclerViewAdapter(Context context, Events events) {
        this.context = context;
        this.events = events;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.events_item_layout, parent,false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        Event event = events.getEvents().get(position);
        Log.d("onBindView", position + event.toString());
        holder.id.setText("Event: " + event.getEventsname());
        //holder.name.setText(event.getEventsname());
        holder.member_name.setText("Member: " + event.getEventsattendedmembersnames());
        holder.project_name.setText("Name: " + event.getEventsprojectname());
        holder.project_manager.setText("Manager: " + event.getEventsprojectmanager());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context.getApplicationContext(), "sdf" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClickListener.onEventClick(holder.itemView, position);
            }
        });
    }

    public void setEventClickListener(EventClickListener eventClickListener) {
        this.eventClickListener = eventClickListener;
    }

    @Override
    public int getItemCount() {
        return events.getEvents().size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView project_name;
        TextView project_manager;
        TextView member_name;

        CustomViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.event_id);
            project_name = view.findViewById(R.id.project_name);
            project_manager = view.findViewById(R.id.project_manager);
            member_name = view.findViewById(R.id.member_name);
        }
    }
}
