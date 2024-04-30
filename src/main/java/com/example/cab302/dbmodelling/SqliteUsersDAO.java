package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqliteUsersDAO implements IUserDAO{
    private Connection connection;

    public SqliteUsersDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        insertSammpleData();
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

    private void insertSammpleData(){
        try{
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (firstName, lastName, gender, email, password, passwordSalt, dob, securityQuestion, securityQuestionANS, achievements, practitioner) VALUES "
                    + "('John','Doe','Male','john.doe@gmail.com','P@ssw0rd','1','2000-02-23','question','answer','1','0'),"
                    + "('Jane','Doe','Female','jane.doe@gmail.com','P@ssw0rd','1','2000-02-23','question','answer','1','0')";
            insertStatement.execute(insertQuery);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public User getUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String passwordSalt = resultSet.getString("passwordSalt");
                Date dob = resultSet.getDate("dob");
                int achievements = resultSet.getInt("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName,  lastName,  gender,  email,  password,  passwordSalt, dob,  achievements, practitioner);
                user.setUserID(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String userEmail){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1,userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String passwordSalt = resultSet.getString("passwordSalt");
                User user = new User(firstName,  lastName, null,  email,  password,  passwordSalt, null, 0, 0);
                user.setUserID(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String passwordSalt = resultSet.getString("passwordSalt");
                Date dob = resultSet.getDate("dob");
                int achievements = resultSet.getInt("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName,  lastName,  gender,  email,  password,  passwordSalt, dob,  achievements, practitioner);
                user.setUserID(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
