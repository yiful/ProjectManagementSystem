package com.rjt.projectmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

public class Event {
	public static final String CONCLUSION = "conclusion";
    public static final String MEMBER_NAME = "eventsattendedmembersnames";
    public static final String NAME = "eventsname";
    public static final String PROJECT_NAME = "eventsprojectname";
    public static final String TEAM_MANAGER = "eventsteammanager";
    public static final String PROJECT_MANAGER = "eventsprojectmanager";
    public static final String STATUS = "eventsstatus";
    public static final String ID = "id";

	@SerializedName("conclusion")
	private String conclusion;

	@SerializedName("eventsattendedmembersnames")
	private String eventsattendedmembersnames;

	@SerializedName("eventsname")
	private String eventsname;

	@SerializedName("eventsprojectname")
	private String eventsprojectname;

	@SerializedName("id")
	private String id;

	@SerializedName("eventsteammanager")
	private String eventsteammanager;

	@SerializedName("eventsprojectmanager")
	private String eventsprojectmanager;

	@SerializedName("eventsstatus")
	private String eventsstatus;

	public void setConclusion(String conclusion){
		this.conclusion = conclusion;
	}

	public String getConclusion(){
		return conclusion;
	}

	public void setEventsattendedmembersnames(String eventsattendedmembersnames){
		this.eventsattendedmembersnames = eventsattendedmembersnames;
	}

	public String getEventsattendedmembersnames(){
		return eventsattendedmembersnames;
	}

	public void setEventsname(String eventsname){
		this.eventsname = eventsname;
	}

	public String getEventsname(){
		return eventsname;
	}

	public void setEventsprojectname(String eventsprojectname){
		this.eventsprojectname = eventsprojectname;
	}

	public String getEventsprojectname(){
		return eventsprojectname;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEventsteammanager(String eventsteammanager){
		this.eventsteammanager = eventsteammanager;
	}

	public String getEventsteammanager(){
		return eventsteammanager;
	}

	public void setEventsprojectmanager(String eventsprojectmanager){
		this.eventsprojectmanager = eventsprojectmanager;
	}

	public String getEventsprojectmanager(){
		return eventsprojectmanager;
	}

	public void setEventsstatus(String eventsstatus){
		this.eventsstatus = eventsstatus;
	}

	public String getEventsstatus(){
		return eventsstatus;
	}

	@Override
 	public String toString(){
		return 
			"Event{" +
			"conclusion = '" + conclusion + '\'' + 
			",eventsattendedmembersnames = '" + eventsattendedmembersnames + '\'' + 
			",eventsname = '" + eventsname + '\'' + 
			",eventsprojectname = '" + eventsprojectname + '\'' + 
			",id = '" + id + '\'' + 
			",eventsteammanager = '" + eventsteammanager + '\'' + 
			",eventsprojectmanager = '" + eventsprojectmanager + '\'' + 
			",eventsstatus = '" + eventsstatus + '\'' + 
			"}";
		}
}