package com.example.cab302.dbmodelling;

import java.util.List;

/**
 * Interface for the User Data Access Object that handles
 * the CRUD operations for the User class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new User to the database
     * @param user The user to add
     */
    public void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The contact to update.
     */
    public void updateUser(User user);
    /**
     * Deletes a user from the database.
     * @param user The user to delete.
     */
    public void deleteUser(User user);
    /**
     * Retrieves a users from the database.
     * @param id The id of the contact to retrieve.
     * @return The contact with the given id, or
     */
    public User getUser(int id);
    /**
     * Retrieves all contacts from the database.
     * @return A list of all contacts in the database.
     */
    public List<User> getAllUsers();
}
