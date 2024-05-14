package com.example.cab302.dbmodelling;

import java.util.List;

/**
 * Interface for the Achievements Data Access Object that handles
 * the CRUD operations for the Practitioner class with the database.
 */
public interface IAchievementsDAO {
    /**
     * Gets a list of all achievements from the database
     * @return A list of all achievements stored in the database
     */
    public List<Achievement> getAllAchievements();

    /**
     * Gets a single achievement object by its ID;
     */
    public Achievement getAchievementByID(int achievementID);
}
