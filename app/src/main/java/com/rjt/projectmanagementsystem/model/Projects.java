package com.rjt.projectmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Projects{

	@SerializedName("Projects")
	private List<Project> projects;

	public void setProjects(List<Project> projects){
		this.projects = projects;
	}

	public List<Project> getProjects(){
		return projects;
	}

	@Override
 	public String toString(){
		return 
			"Projects{" + 
			"projects = '" + projects + '\'' + 
			"}";
		}
}