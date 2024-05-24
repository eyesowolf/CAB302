package com.example.cab302;

import com.example.cab302.dbmodelling.Achievement;
import com.example.cab302.dbmodelling.SqliteAchievementsDAO;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class will handle the setting and getting of user achievements.
 */
public class AchievementHandler{
    private int userID;

    private String achievements;

    /**
     * Constructor for the Achievement Hanadler class
     * @param user The user for which this instance of AchievementHandler has been created
     */
    public AchievementHandler(User user){
        userID = user.getID();
        achievements = user.getAchieves();
    }

    public void updateAchievements(){
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        User user = userDAO.getUserById(userID);
        achievements = user.getAchieves();
    }

    public void setAchievement(int id){
        updateAchievements();
        int currentAchievements = Integer.valueOf(achievements, 16);
        int idToAdd = (10^(id-1));
        int newAchievements = currentAchievements + idToAdd;
        Pattern pattern = Pattern.compile("^[01]+$");
        Matcher matcher = pattern.matcher(Integer.toString(newAchievements));
        if (!matcher.find()) newAchievements = currentAchievements;
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        User user = userDAO.getUserById(userID);
        user.setAchieves(Integer.toString(newAchievements,16));
    }

    public List<Achievement> getAllUserAchievements(){
        updateAchievements();
        SqliteAchievementsDAO achievementsDAO = new SqliteAchievementsDAO();
        List<Achievement> achievements = new ArrayList<>();
        // Create loop that goes through user's achievements and pulls them from the DB
        return achievements;
    }
}
