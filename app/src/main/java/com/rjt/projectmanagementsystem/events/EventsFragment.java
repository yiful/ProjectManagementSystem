package com.rjt.projectmanagementsystem.events;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rjt.projectmanagementsystem.R;

import com.rjt.projectmanagementsystem.model.Events;
import com.rjt.projectmanagementsystem.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements EventClickListener{
    TabLayout tabLayout;
    RecyclerView recyclerView;
    EventsRecyclerViewAdapter adapter;
    Util util;
    Events myEvents;

    public EventsFragment() {
        // Required empty public constructor
        util = new Util();
        util.getRecentEvents("recent", new Util.EventsCallback() {
            @Override
            public void onResponse(Events events) {
                myEvents = events;
                setEventsRecyclerView(myEvents);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Drawable dividerDrawabler = getActivity().getDrawable(R.drawable.decoration_drawable);
        RecyclerView.ItemDecoration decoration = new RecyclerViewItemDecoration(dividerDrawabler);
        recyclerView.addItemDecoration(decoration);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("recent")) {
                    util.getRecentEvents("recent", new Util.EventsCallback() {
                        @Override
                        public void onResponse(Events events) {
                            Log.d("RECENT", events.getEvents().size() + "");
                            myEvents = events;
                            setEventsRecyclerView(events);
                        }
                    });
                } else if (tab.getText().equals("current")) {
                    util.getCurrentEvents("current", new Util.EventsCallback() {
                        @Override
                        public void onResponse(Events events) {
                            Log.d("current", events.getEvents().size() + "");
                            myEvents = events;
                            setEventsRecyclerView(events);
                        }
                    });
                } else {
                    util.getAllEvents("all", new Util.EventsCallback() {
                        @Override
                        public void onResponse(Events events) {
                            Log.d("all", events.getEvents().size() + "");
                            myEvents = events;
                            setEventsRecyclerView(events);
                        }
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setEventsRecyclerView(Events events) {

        adapter = new EventsRecyclerViewAdapter(getActivity(), events);
        adapter.setEventClickListener(this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEventClick(View view, int position) {
        Log.d("position", position + "");
        //Toast.makeText(getActivity(), myEvents.getEvents().get(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
