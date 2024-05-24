package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                    + "dob INTTEGER NOT NULL,"
                    + "securityQuestion VARCHAR NOT NULL,"
                    + "securityQuestionANS VARCHAR NOT NULL,"
                    + "prefs BLOB,"
                    + "achievements VARCHAR,"
                    + "practitioner INT"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertSammpleData(){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM users)");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int result = resultSet.getInt(1);
                if (result == 0) {
                    Statement insertStatement = connection.createStatement();
                    // Initialize prefs with default values
                    byte[] defaultPrefs = new byte[]{0}; // Example initialization, adjust as necessary
                    String insertQuery = "INSERT INTO users (firstName, lastName, gender, email, password, dob, securityQuestion, securityQuestionANS, achievements, practitioner) VALUES "
                            + "('John','Doe','Male','john.doe@gmail.com','P@ssw0rd','958867200','question','answer','1','0'),"
                            + "('Jane','Doe','Female','jane.doe@gmail.com','P@ssw0rd','958867200','question','answer','1','0')";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setBytes(1, defaultPrefs);
                    preparedStatement.setBytes(2, defaultPrefs);

                    preparedStatement.execute();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (firstName, lastName, gender, email, password, dob, securityQuestion, securityQuestionANS, prefs, achievements, practitioner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getDoB());
            statement.setString(7, user.getSecQ());
            statement.setString(8, user.getSecA());
            statement.setBytes(9, user.getPrefs());
            statement.setString(10, user.getAchieves());
            statement.setInt(11, user.getUserPractitioner());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                user.setID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE users SET firstName=?, lastName=?, gender=?, email=?, password=?, dob=?, securityQuestion=?, securityQuestionANS=?, prefs=?, achievements=?, practitioner=? WHERE id=?");
            updateStatement.setString(1, user.getFirstName());
            updateStatement.setString(2, user.getLastName());
            updateStatement.setString(3, user.getGender());
            updateStatement.setString(4, user.getEmail());
            updateStatement.setString(5, user.getPassword());
            updateStatement.setInt(6, user.getDoB());
            updateStatement.setString(7, user.getSecQ());
            updateStatement.setString(8, user.getSecA());
            updateStatement.setBytes(9, user.getPrefs());
            updateStatement.setString(10, user.getAchieves());
            updateStatement.setInt(11, user.getUserPractitioner());
            updateStatement.setInt(12, user.getID());
            updateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        int id = user.getID();
        try {
            Statement deleteStatement = connection.createStatement();
            String deleteQuery = "DELETE FROM users WHERE id="+id;
            deleteStatement.execute(deleteQuery);
        } catch (Exception e){
            e.printStackTrace();
        }
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
                int dob = resultSet.getInt("dob");
                String secQ = resultSet.getString("securityQuestion");
                String secA = resultSet.getString("securityQuestionANS");
                byte[] prefs = resultSet.getBytes("prefs");
                String achievements = resultSet.getString("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName,  lastName,  gender,  email,  password, dob,  secQ, secA ,achievements, practitioner);
                user.setID(id);
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
            DateFormat formatter = new SimpleDateFormat("yyyy-M-d");
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int dob = resultSet.getInt("dob");
                String secQ = resultSet.getString("securityQuestion");
                String secA = resultSet.getString("securityQuestionANS");
                byte[] prefs = resultSet.getBytes("prefs");
                String achievements = resultSet.getString("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName,  lastName, gender,  email,  password, dob, secQ, secA, achievements, practitioner);
                user.setID(id);
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
                int dob = resultSet.getInt("dob");
                String secQ = resultSet.getString("securityQuestion");
                String secA = resultSet.getString("securityQuestionANS");
                byte[] prefs = resultSet.getBytes("prefs");
                String achievements = resultSet.getString("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName,  lastName,  gender,  email,  password, dob, secQ, secA,  achievements, practitioner);
                user.setID(id);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
