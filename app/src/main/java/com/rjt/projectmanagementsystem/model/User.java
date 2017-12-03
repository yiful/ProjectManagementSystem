package com.rjt.projectmanagementsystem.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rashmi on 12/2/2017.
 */

public class User implements Parcelable {

    private String msg;
    private String userid;
    private String userfirstname;
    private String userlastname;
    private String useremail;
    //private String usermobile;
    @SerializedName("appapiKey")
    private String appapiKey;

    public User(String msg, String userid, String userfirstname, String userlastname, String useremail, String appapiKey) {
        this.msg = msg;
        this.userid = userid;
        this.userfirstname = userfirstname;
        this.userlastname = userlastname;
        this.useremail = useremail;
        this.appapiKey = appapiKey;
    }

    protected User(Parcel in) {
        msg = in.readString();
        userid = in.readString();
        userfirstname = in.readString();
        userlastname = in.readString();
        useremail = in.readString();
        appapiKey = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAppapiKey() {
        return appapiKey;
    }

    public void setAppapiKey(String appapiKey) {
        this.appapiKey = appapiKey;
    }

    public String toString(){
        String s = null;
        s = msg + " "+userid +" "+userfirstname+" "+userlastname+" "+useremail+" "+appapiKey;
        return s;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(userid);
        dest.writeString(userfirstname);
        dest.writeString(userlastname);
        dest.writeString(useremail);
        dest.writeString(appapiKey);
    }
}
