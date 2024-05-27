package com.example.cab302;

public class PersistentThread implements Runnable{
    private volatile int userID;
    public void setUserID(int value){this.userID = value;}
    public int getUserID(){return userID;}
    public void run() {
    }
}
