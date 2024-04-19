package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.Statement;

public class dbUsers {
    private Connection connection;

    public dbUsers() {
        connection = SqliteConnection.getInstance();
        createTable();
    }
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "gender VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL"
                    + "password NVARCHAR NOT NULL,"
                    + "passwordSalt NVARCHAR NOT NULL,"
                    + "dob DATE NOT NULL,"
                    + "securityQuestion VARCHAR NOT NULL,"
                    + "securityQuestionANS VARCHAR NOT NULL,"
                    + "prefs BLOB"
                    + "achievements INT,"
                    + "practitioner INT,"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
