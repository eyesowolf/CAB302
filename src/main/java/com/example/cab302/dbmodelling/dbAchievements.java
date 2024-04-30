package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.Statement;

public class dbAchievements {
    private Connection connection;

    public dbAchievements() {
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
}
