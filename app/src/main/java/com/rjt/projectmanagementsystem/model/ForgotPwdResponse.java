package com.rjt.projectmanagementsystem.model;

/**
 * Created by rashmi on 12/2/2017.
 */

public class ForgotPwdResponse {

    String msg;
    String useremail;
    String userpassword;

    public ForgotPwdResponse(String msg, String useremail, String userpassword) {
        this.msg = msg;
        this.useremail = useremail;
        this.userpassword = userpassword;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
    public String toString(){
        String s = null;
        s = msg +" "+useremail+" "+userpassword;
        return s;
    }
}

