package com.example.test.bean;

import java.util.Date;

public class User {
    private Integer UserID;
    private String UserName;
    private String Password;
    private String Email;
    private String Gender;
    private Date Birthday;
    private String ProfileUrl;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getProfileUrl() {
        return ProfileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        ProfileUrl = profileUrl;
    }
}
