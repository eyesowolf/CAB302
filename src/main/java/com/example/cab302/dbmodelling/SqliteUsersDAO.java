package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SqliteUsersDAO implements IUserDAO{
    private Connection connection;

    public SqliteUsersDAO() {
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
                    + "email VARCHAR NOT NULL,"
                    + "password NVARCHAR NOT NULL,"
                    + "passwordSalt NVARCHAR NOT NULL,"
                    + "dob DATE NOT NULL,"
                    + "securityQuestion VARCHAR NOT NULL,"
                    + "securityQuestionANS VARCHAR NOT NULL,"
                    + "prefs BLOB,"
                    + "achievements INT,"
                    + "practitioner INT"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public User getUser(int id) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }
}
