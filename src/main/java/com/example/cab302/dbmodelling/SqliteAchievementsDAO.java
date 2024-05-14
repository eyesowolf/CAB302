package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteAchievementsDAO implements IAchievementsDAO {
    private Connection connection;

    public SqliteAchievementsDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS achievements ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "achievementName VARCHAR NOT NULL,"
                    + "achievementDescription VARCHAR NOT NULL,"
                    + ")";
            statement.execute(query);
            // pre-populate with achievements
            String insertQuery = "INSERT INTO achievements (achievementName, achievementDescription) VALUES "
                    + "('Really Feeling It', 'Input mood data 5 days in a row'),"
                    + "('I Got a Feeling', 'Input data for the first time')";
            statement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Achievement> getAllAchievements() {
        List<Achievement> achievements = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM achievements";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String achievementName = resultSet.getString("achievementName");
                String achievementDescription = resultSet.getString("achievementDescription");
                Achievement achievement = new Achievement(achievementName,  achievementDescription);
                achievement.setID(id);
                achievements.add(achievement);
            }
            return achievements;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Achievement getAchievementByID(int id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM achievements WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String achievementName = resultSet.getString("achievementName");
                String achievementDescription = resultSet.getString("achievementDescription");
                Achievement achievement = new Achievement(achievementName,  achievementDescription);
                achievement.setID(id);
                return achievement;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
