package com.example.cab302.dbmodelling;

import java.util.Date;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private Date DoB;
    private String SecQ;
    private String SecA;
    private Integer Achieves;
    private Integer userPractitioner;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

    public String getSecQ() {
        return SecQ;
    }

    public void setSecQ(String secQ) {
        SecQ = secQ;
    }

    public String getSecA() {
        return SecA;
    }

    public void setSecA(String secA) {
        SecA = secA;
    }

    public Integer getAchieves() {
        return Achieves;
    }

    public void setAchieves(Integer achieves) {
        Achieves = achieves;
    }

    public Integer getUserPractitioner() {
        return userPractitioner;
    }

    public void setUserPractitioner(Integer userPractitioner) {
        this.userPractitioner = userPractitioner;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}