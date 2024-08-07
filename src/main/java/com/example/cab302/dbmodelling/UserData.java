package com.example.cab302.dbmodelling;

import java.text.ParseException;
import java.util.Date;

/**
 * UserData class will store all the information that the application will be required to process
 */

public class UserData {
    private int ID;
    private String name;
    private int date;
    private String mood;
    private String description;
    private int userID;

    /**
     * UserData class constructor used to initialise a new UserData object
     * @param name the short name of a data object to be displayed to the user
     * @param date the date and time of the data entry note: this should be of the format "yyyy-MM-dd HH:mm:ss"
     * @param mood the Mood that is being recorded
     * @param description A description of the data being stored
     * @param userID the user ID of the user who is storing this data
     */
    public UserData(String name, int date, String mood, String description,int userID){
        this.name = name;
        this.date = date;
        this.mood = mood;
        this.description = description;
        this.userID = userID;
    }

    public void setID(int ID) {this.ID = ID;}

    public int getID(){return ID;}

    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setDate(int date){this.date = date;}
    public int getDate(){return date;}
    public void setMood(String mood){this.mood = mood;}
    public String getMood(){return mood;}
    public void setDescription(String description){this.description = description;}
    public String getDescription(){return description;}
    public void setUserID(int userID){this.userID = userID;}
    public int getUserID(){return  userID;}
}
