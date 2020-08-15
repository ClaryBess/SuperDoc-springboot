package com.example.test.bean;

import java.util.Date;

public class Team {
    private Integer TeamID;
    private String TeamName;
    private Integer MemberNumber;
    private Integer Privilege;
    private Date DateTime;

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(Integer teamID) {
        TeamID = teamID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public int getMemberNumber() {
        return MemberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        MemberNumber = memberNumber;
    }

    public int getPrivilege() {
        return Privilege;
    }

    public void setPrivilege(Integer privilege) {
        Privilege = privilege;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }
}
