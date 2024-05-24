package com.example.cab302.dbmodelling;

/**
 * The User class hold the data for a specified user which gets retrieved from the DAO
 */

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private String passwordSalt;
    private int DoB;
    private String secQ;
    private String secA;
    private String Achieves;
    private int userPractitioner;
    // private Blob prefs;

    public User(String firstName, String lastName, String gender, String email, String password, int DoB, String secQ, String secA, String Achieves, int userPractitioner) {
    /**
     * User constructor class which is used to initialise the User class object
     * @param firstName String: The first name of the user
     * @param lastName String: The last name of the user
     * @param gender String: The Gender of the user
     * @param email String: The email of the user (Later used as their username)
     * @param password String: The password of the user
     * @param DoB int: The Date of Birth of the user in the format "yyyy-MM-dd"
     * @param secQ String: The chosen security question
     * @param secA String: The answer to the chosen security question
     * @param Achieves String: The users awarded achievements stored as Hexadecimal
     * @param userPractitioner int: The practitioner ID that this user has chosen to share their data with
     */
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.DoB = DoB;
        this.secQ = secQ;
        this.secA = secA;
        this.Achieves = Achieves;
        this.userPractitioner = userPractitioner;
    }

    public int getID() {
        return userID;
    }

    public void setID(int userID) {
        this.userID = userID;
    }

    public String getFirstName(){ return firstName; }

    public void setFirstName(String firstName){this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName){ this.lastName = lastName; }

    public String getGender() { return gender; }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDoB() {
        return DoB;
    }

    public void setDoB(int dob) {
        DoB = dob;
    }

    public String getSecQ() {
        return secQ;
    }

    public void setSecQ(String securityQuestion) {
        secQ = securityQuestion;
    }

    public String getSecA() {
        return secA;
    }

    public void setSecA(String securityQuestionANS) {
        secA = securityQuestionANS;
    }

    public String getAchieves() {
        return Achieves;
    }

    public void setAchieves(String achieves) {
        Achieves = achieves;
    }

    public Integer getUserPractitioner() {
        return userPractitioner;
    }

    public void setUserPractitioner(Integer userPractitioner) {
        this.userPractitioner = userPractitioner;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}