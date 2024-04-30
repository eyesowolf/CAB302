package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.Statement;

public class dbUserData {
    private Connection connection;

    public dbUserData() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS userData ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "entryName VARCHAR NOT NULL,"
                    + "entryDate DATE NOT NULL,"
                    + "entryMood VARCHAR NOT NULL,"
                    + "entryDescription TEXT,"
                    + "entryUserID INT,"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
