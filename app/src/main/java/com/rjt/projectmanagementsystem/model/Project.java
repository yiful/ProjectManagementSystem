package com.rjt.projectmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rashmi on 12/4/2017.
 */

public class Project {

    @SerializedName("projectname")
    private String projectname;

    @SerializedName("endstart")
    private String endstart;

    @SerializedName("projectdesc")
    private String projectdesc;

    @SerializedName("id")
    private String id;

    @SerializedName("startdate")
    private String startdate;

    @SerializedName("projectstatus")
    private String projectstatus;

    @SerializedName("assignto")
    private String assignto;

    public void setProjectname(String projectname){
        this.projectname = projectname;
    }

    public String getProjectname(){
        return projectname;
    }

    public void setEndstart(String endstart){
        this.endstart = endstart;
    }

    public String getEndstart(){
        return endstart;
    }

    public void setProjectdesc(String projectdesc){
        this.projectdesc = projectdesc;
    }

    public String getProjectdesc(){
        return projectdesc;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setStartdate(String startdate){
        this.startdate = startdate;
    }

    public String getStartdate(){
        return startdate;
    }

    public void setProjectstatus(String projectstatus){
        this.projectstatus = projectstatus;
    }

    public String getProjectstatus(){
        return projectstatus;
    }

    public void setAssignto(String assignto){
        this.assignto = assignto;
    }

    public String getAssignto(){
        return assignto;
    }

    @Override
    public String toString(){
        return
                "Project{" +
                        "projectname = '" + projectname + '\'' +
                        ",endstart = '" + endstart + '\'' +
                        ",projectdesc = '" + projectdesc + '\'' +
                        ",id = '" + id + '\'' +
                        ",startdate = '" + startdate + '\'' +
                        ",projectstatus = '" + projectstatus + '\'' +
                        ",assignto = '" + assignto + '\'' +
                        "}";
    }
}
