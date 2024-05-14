package com.example.cab302.dbmodelling;

import java.sql.*;

public class SqlitePractitionersDAO implements IPractitionerDAO {
    private Connection connection;

    public SqlitePractitionersDAO() {
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

    /**
     * Adds a practitioner to the database
     * @param practitioner The practitioner to be added to the database.
     */
    public void addPractitioner(Practitioner practitioner) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO practitioners (BaseUserID, prefs) VALUES (?, ?)");
            statement.setInt(1, practitioner.getBaseUserID());
            statement.setBlob(2, practitioner.getPrefs());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                practitioner.setID(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a practitioner from the database
     * @param practitioner The practitioner to be removed from the database
     */
    public void removePractitioner(Practitioner practitioner) {
        int id = practitioner.getID();
        try{
            Statement deleteStatement = connection.createStatement();
            String deleteQuery = "DELETE FROM practitioners WHERE id="+id;
            deleteStatement.execute(deleteQuery);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Updates a practitioners preferences in the database
     * @param practitioner The practitioner whose preferences are to be updated
     */
    public void updatePrefs(Practitioner practitioner) {
        try {
            Statement updateStatement = connection.createStatement();
            String updateQuery = "UPDATE practitioners SET prefs="+practitioner.getPrefs()+" WHERE id="+practitioner.getID();
            updateStatement.execute(updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a practitioner entry if one exists for the user
     * @param user The user to check if they have a practitioner profile
     * @return A practitioner object if one exists or Null
     */
    public Practitioner getPractitioner(User user) {
        int userID = user.getID();
        try{
            PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM practitioners WHERE BaseUserID="+userID);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                int BaseUserID = resultSet.getInt("BaseUserID");
                Blob prefs = resultSet.getBlob("prefs");
                Practitioner practitioner = new Practitioner(BaseUserID,prefs);
                practitioner.setID(id);
                return practitioner;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
