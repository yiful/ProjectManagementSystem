package com.rjt.projectmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rashmi on 12/4/2017.
 */

public class Events {

    public static final String EVENTS = "Events";

    @SerializedName("Events")
    private List<Event> events;

    public void setEvents(List<Event> events){
        this.events = events;
    }

    public List<Event> getEvents(){
        return events;
    }

    @Override
    public String toString(){
        return
                "Events {" +
                        "events = '" + events + '\'' +
                        "}";
    }
}
