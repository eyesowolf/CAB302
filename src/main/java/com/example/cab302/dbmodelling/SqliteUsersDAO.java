package com.example.cab302.dbmodelling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteUsersDAO implements IUserDAO {
    private Connection connection;

    public SqliteUsersDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        //insertSammpleData();
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
                    + "dob VARCHAR NOT NULL,"
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

    private void insertSammpleData() {
        try {
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (firstName, lastName, gender, email, password, dob, securityQuestion, securityQuestionANS, achievements, practitioner) VALUES "
                    + "('John','Doe','Male','john.doe@gmail.com','P@ssw0rd','2000-02-23','question','answer','1','0'),"
                    + "('Jane','Doe','Female','jane.doe@gmail.com','P@ssw0rd','2000-02-23','question','answer','1','0')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
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
            statement.setString(6, user.getDoB());
            statement.setString(7, user.getSecQ());
            statement.setString(8, user.getSecA());
            statement.setString(10, user.getAchieves());
            statement.setInt(11, user.getUserPractitioner());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
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
            updateStatement.setString(6, user.getDoB());
            updateStatement.setString(7, user.getSecQ());
            updateStatement.setString(8, user.getSecA());
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
            String deleteQuery = "DELETE FROM users WHERE id=" + id;
            deleteStatement.execute(deleteQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String dob = resultSet.getString("dob");
                String secQ = resultSet.getString("securityQuestion");
                String secA = resultSet.getString("securityQuestionANS");
                String achievements = resultSet.getString("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName, lastName, gender, email, password, dob, secQ, secA, achievements, practitioner);
                user.setID(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String userEmail) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String dob = resultSet.getString("dob");
                String secQ = resultSet.getString("securityQuestion");
                String secA = resultSet.getString("securityQuestionANS");
                String achievements = resultSet.getString("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName, lastName, gender, email, password, dob, secQ, secA, achievements, practitioner);
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
                String dob = resultSet.getString("dob");
                String secQ = resultSet.getString("securityQuestion");
                String secA = resultSet.getString("securityQuestionANS");
                String achievements = resultSet.getString("achievements");
                int practitioner = resultSet.getInt("practitioner");
                User user = new User(firstName, lastName, gender, email, password, dob, secQ, secA, achievements, practitioner);
                user.setID(id);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<moodData> getMoodDataForVisualization() {
        List<moodData> moodDataList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT id, moodType, date, description FROM moods";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String moodType = resultSet.getString("moodType");
                String date = resultSet.getString("date");
                String description = resultSet.getString("description");
                moodData mood = new moodData(id, "", new SimpleDateFormat("yyyy-MM-dd").parse(date), moodType, description, 0);
                moodDataList.add(mood);
            }
            return moodDataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}