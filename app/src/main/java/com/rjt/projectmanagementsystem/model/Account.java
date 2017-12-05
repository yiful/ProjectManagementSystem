package com.rjt.projectmanagementsystem.model;


import com.google.gson.annotations.SerializedName;

public class Account{
	public static final String MESSAGE = "msg";
	public static final String USER_EMAIL = "useremail";
	public static final String USER_PASSWORD = "userpassword";

	@SerializedName("msg")
	private String msg;

	@SerializedName("useremail")
	private String useremail;

	@SerializedName("userpassword")
	private String userpassword;


	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setUseremail(String useremail){
		this.useremail = useremail;
	}

	public String getUseremail(){
		return useremail;
	}

	public void setUserpassword(String userpassword){
		this.userpassword = userpassword;
	}

	public String getUserpassword(){
		return userpassword;
	}

	@Override
 	public String toString(){
		return 
			"Account{" + 
			"msg = '" + msg + '\'' + 
			",useremail = '" + useremail + '\'' + 
			",userpassword = '" + userpassword + '\'' + 
			"}";
		}
}