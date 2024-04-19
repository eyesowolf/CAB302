package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.Statement;

public class dbPractioners {
    private Connection connection;

    public dbPractioners() {
        connection = SqliteConnection.getInstance();
        createTable();
    }
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS practitioners ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "BaseUserID INT NOT NULL,"
                    + "prefs BLOB,"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
