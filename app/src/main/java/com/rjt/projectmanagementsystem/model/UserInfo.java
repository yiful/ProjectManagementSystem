package com.rjt.projectmanagementsystem.model;

/**
 * Created by Jinming on 12/2/17.
 */

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    public static final String MESSAGE = "msg";
    public static final String LAST_NAME = "userlastname";
    public static final String FIRST_NAME = "userfirstname";
    public static final String API_KEY = "appapikey ";
    public static final String ID = "userid";
    public static final String EMAIL = "useremail";

    @SerializedName("msg")
    private String msg;

    @SerializedName("userlastname")
    private String userlastname;

    @SerializedName("userfirstname")
    private String userfirstname;

    @SerializedName("appapikey ")
    private String appapikey;

    @SerializedName("userid")
    private String userid;

    @SerializedName("useremail")
    private String useremail;

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setUserlastname(String userlastname){
        this.userlastname = userlastname;
    }

    public String getUserlastname(){
        return userlastname;
    }

    public void setUserfirstname(String userfirstname){
        this.userfirstname = userfirstname;
    }

    public String getUserfirstname(){
        return userfirstname;
    }

    public void setAppapikey(String appapikey){
        this.appapikey = appapikey;
    }

    public String getAppapikey(){
        return appapikey;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getUserid(){
        return userid;
    }

    public void setUseremail(String useremail){
        this.useremail = useremail;
    }

    public String getUseremail(){
        return useremail;
    }

    @Override
    public String toString(){
        return
                "UserInfo {" +
                        "msg = '" + msg + '\'' +
                        ",userlastname = '" + userlastname + '\'' +
                        ",userfirstname = '" + userfirstname + '\'' +
                        ",appapikey  = '" + appapikey + '\'' +
                        ",userid = '" + userid + '\'' +
                        ",useremail = '" + useremail + '\'' +
                        "}";
    }
}
