package com.rjt.projectmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rashmi on 12/4/2017.
 */

public class TeamMembers {
    @SerializedName("Team Members")
    private List<TeamMembersItem> teamMembers;

    public void setTeamMembers(List<TeamMembersItem> teamMembers){
        this.teamMembers = teamMembers;
    }

    public List<TeamMembersItem> getTeamMembers(){
        return teamMembers;
    }

    @Override
    public String toString(){
        return
                "TeamMembers{" +
                        "team Members = '" + teamMembers + '\'' +
                        "}";
    }
}
