package com.example.cab302.dbmodelling;

import java.util.Date;
import java.util.List;

/**
 * Interface for the UserData Data Access Object that handles
 * the CRUD operations for the UserData class with the database.
 */

public interface IUserDataDAO
{
    /**
     * Adds a new UserData Object ot the database
     * @param data is the user data object to be committed to the database
     */
    public void addUserData(UserData data);

    /**
     * Updates an existing data entry.
     * @param data The Data object which is to be updated in the database.
     */
    public void updateUserData(UserData data);

    /**
     * returns a complete list of the user's data.
     * @return Returns a list of user data entries
     */
    public List<UserData> getAllUserData();

    /**
     * returns a list of all the users data between the dates given
     * @param start The date at which to start looking for data.
     * @param end The date at which to stop looking for data.
     * @return Returns a list of user data entries
     */
    public List<UserData> getUserDataInRange(String startDate, String endDate);

    /**
     * Deletes a datapoint from the table of userData.
     * @param data The data element that is to be removed.
     */
    public void deleteEntry(UserData data);

    /**
     * Drops the whole user data table if a user decides to delete all their data.
     * @param user The user who is initiating the action.
     */
    public void deleteAllUserData(User user);
}
