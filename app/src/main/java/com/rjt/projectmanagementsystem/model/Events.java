package com.rjt.projectmanagementsystem.model;

/**
 * Created by Jinming on 12/2/17.
 */

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Events{

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
