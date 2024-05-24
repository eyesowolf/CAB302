package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SQLITE DAO class which handles the communication between the application and the database
 */

public class SqliteUserDataDAO implements IUserDataDAO {
    private Connection connection;
    private int userID;
    private String databaseName;

    /**
     * Initialises an instance of the database connection. It's important that a userID is passed here such that the
     * application knows which table to access. Ultimately all user data stored in the database should be encrypted with
     * a user specific key to prevent an unauthorised user from accessing another users data however this is currently
     * out of scope.
     * @param userID The user ID of the user whose data is to be accessed
     */

    public SqliteUserDataDAO(int userID) {
        connection = SqliteConnection.getInstance();
        this.userID = userID;
        databaseName = "userData"+userID;
        createTable();
    }

    /**
     * Attempts to create a new table to hold a user's data if the table doesn't exist
     * A user table includes:
     * id (AUTOINCREMENT INT)
     * entryName VARCHAR
     * entryDate VARCHAR
     * entryMood VARCHAR
     * entryDescription TEXT
     * entryUserID INT
     */

    private void createTable() {
        // Create table if not exists
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+databaseName+" (id INTEGER PRIMARY KEY AUTOINCREMENT, entryName VARCHAR NOT NULL, entryDate VARCHAR NOT NULL, entryMood VARCHAR NOT NULL, entryDescription TEXT, entryUserID INT)");
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to add a new user data point to the database. This method required a UserData object which will then
     * be published to the user's UserData table
     * @param data An object of type UserData which is to be added to the Database
     */

    public void addUserData(UserData data){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO "+databaseName+" (entryName, entryDate, entryMood, entryDescription, entryUserID) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, data.getName());
            statement.setInt(2, data.getDate());
            statement.setString(3, data.getMood());
            statement.setString(4, data.getDescription());
            statement.setInt(5, data.getUserID());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                data.setID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a user data object that has been modified by the program
     * @param data The Data object which is to be updated in the database.
     */
    public void updateUserData(UserData data) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE "+databaseName+" SET entryName=?, entryDate=?, entryMood=?, entryDescription=? WHERE id=?");
            updateStatement.setString(1, data.getName());
            updateStatement.setInt(2, data.getDate());
            updateStatement.setString(3, data.getMood());
            updateStatement.setString(4, data.getDescription());
            updateStatement.setInt(5, data.getID());
            updateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets user data that is within the specified range
     * @param startDate The date at which to start looking for data as a string.
     * @param endDate The date at which to stop looking for data as a string.
     * @return
     */
    public List<UserData> getUserDataInRange(int startDate, int endDate) {
        List<UserData> dataPoints = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM userData"+userID+" WHERE entryDate BETWEEN "+startDate+" AND "+endDate;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String entryName = resultSet.getString("entryName");
                int entryDate =resultSet.getInt("entryDate");
                String entryMood = resultSet.getString("entryMood");
                String entryDescription = resultSet.getString("entryDescription");
                int entryUserID = resultSet.getInt("entryUserID");
                UserData data = new UserData(entryName, entryDate, entryMood, entryDescription, entryUserID);
                data.setID(id);
                dataPoints.add(data);
            }
            return dataPoints;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * A function which returns all a user's data... this is likely to never be used however as grabbing specific time
     * periods makes more sense and would keep compute times down
     * @return
     */

    public List<UserData> getAllUserData() {
        List<UserData> dataPoints = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM userData"+userID;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String entryName = resultSet.getString("entryName");
                int entryDate = resultSet.getInt("entryDate");
                String entryMood = resultSet.getString("entryMood");
                String entryDescription = resultSet.getString("entryDescription");
                int entryUserID = resultSet.getInt("entryUserID");
                UserData data = new UserData(entryName, entryDate, entryMood, entryDescription, entryUserID);
                data.setID(id);
                dataPoints.add(data);
            }
            return dataPoints;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes an entry that the user no longer wants to store
     * @param data The data element that is to be removed.
     */
    public void deleteEntry(UserData data){
        int id = data.getID();
        try{
            Statement deleteStatement = connection.createStatement();
            String deleteQuery = "DELETE FROM userData"+userID+" WHERE id="+id;
            deleteStatement.execute(deleteQuery);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Drops the user data table if the user selects to clear all their user data
     * @param user The user who is initiating the action.
     */
    public void deleteAllUserData(User user){
        int id = user.getID();
        try {
            connection.close();
            SqliteConnection.clearInstance();
            Connection conn = SqliteConnection.getInstance();
            Statement deleteStatement = conn.createStatement();
            String deleteQuery = "DROP TABLE userData"+id;
            deleteStatement.execute(deleteQuery);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
