package com.example.cab302.dbmodelling;

/**
 * Interface for the Practitioner Data Access Object that handles
 * the CRUD operations for the Practitioner class with the database.
 */
public interface IPractitionerDAO {

    /**
     * Creates a new practitioner entry in the practitioner table of the database
     * @param practitioner The practitioner to be added to the database.
     */
    public void addPractitioner(Practitioner practitioner);

    /**
     * Removes a practitioner entry from the practitioner table of the database
     * @param practitioner The practitioner to be removed from the database
     */

    public void removePractitioner(Practitioner practitioner);

    /**
     * Updates the practitioner preferences from the practitioners entry in the practitioner table of the database.
     * @param practitioner The practitioner whose preferences are to be updated
     */
    public void updatePrefs(Practitioner practitioner);

    /**
     * Gets the appropriate practitioner profile if a user is a practitioner
     * @param user The user to check if they have a practitioner profile
     * @return A practitioner object that matches the user or null if a user is not a practitioner.
     */
    public Practitioner getPractitioner(User user);
}
