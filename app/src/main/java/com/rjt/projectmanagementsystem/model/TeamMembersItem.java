package com.rjt.projectmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

public class TeamMembersItem{

	@SerializedName("memberemail")
	private String memberemail;

	@SerializedName("id")
	private String id;

	@SerializedName("membername")
	private String membername;

	public void setMemberemail(String memberemail){
		this.memberemail = memberemail;
	}

	public String getMemberemail(){
		return memberemail;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMembername(String membername){
		this.membername = membername;
	}

	public String getMembername(){
		return membername;
	}

	@Override
 	public String toString(){
		return 
			"TeamMembersItem{" + 
			"memberemail = '" + memberemail + '\'' + 
			",id = '" + id + '\'' + 
			",membername = '" + membername + '\'' + 
			"}";
		}
}