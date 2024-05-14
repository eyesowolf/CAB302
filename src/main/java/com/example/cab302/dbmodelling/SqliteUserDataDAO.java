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
        createTable();
    }

    /**
     * Attempts to create a new table to hold a user's data if the table doesn't exist
     * A user table includes:
     * id (AUTOINCREMENT INT)
     * entryName VARCHAR
     * entryDate DATE
     * entryMood VARCHAR
     * entryDescription TEXT
     * entryUserID INT
     */

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS userData"+userID+" ("
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

    /**
     * Attempts to add a new user data point to the database. This method required a UserData object which will then
     * be published to the user's UserData table
     * @param data An object of type UserData which is to be added to the Database
     */

    public void addUserData(UserData data){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO userData"+userID+" (entryName, entryDate, entryMood, entryDescription, entryUserID) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, data.getName());
            statement.setDate(2, new java.sql.Date(data.getDate().getTime()));
            statement.setString(3, data.getMood());
            statement.setString(4, data.getDescription());
            statement.setInt(5, data.getUserID());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                data.setUserID(generatedKeys.getInt(1));
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
            java.sql.Date newDate = new java.sql.Date(data.getDate().getTime());
            Statement updateStatement = connection.createStatement();
            String updateQuery = "UPDATE userData"+data.getUserID()+" SET entryName="+data.getName()+", entryDate="+newDate+", entryMood="+data.getMood()+", entryDescription="+data.getDescription()+" WHERE id="+data.getID();
            updateStatement.execute(updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets user data that is within the specified range
     * @param start The date at which to start looking for data.
     * @param end The date at which to stop looking for data.
     * @return
     */
    public List<UserData> getUserDataInRange(Date start, Date end) {
        List<UserData> dataPoints = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            java.sql.Date startDate = new java.sql.Date(start.getTime());
            java.sql.Date endDate = new java.sql.Date(end.getTime());
            String query = "SELECT * FROM userData"+userID+" WHERE entryDate BETWEEN "+startDate+" AND "+endDate;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String entryName = resultSet.getString("entryName");
                java.util.Date entryDate = new java.util.Date(resultSet.getDate("entryDate").getTime());
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
                java.util.Date entryDate = new java.util.Date(resultSet.getDate("entryDate").getTime());
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
            Statement deleteStatement = connection.createStatement();
            String deleteQuery = "DROP TABLE userData"+id;
            deleteStatement.execute(deleteQuery);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
