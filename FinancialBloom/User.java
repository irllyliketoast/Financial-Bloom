/* 
Author: Temo Galinda

Description: This User class is part of a user management system. It defines a user with properties 
like userid, fname (first name), lname (last name), email, password, and datecreated. The class 
encapsulates these attributes and provides getter and setter methods for modifying and accessing them. 
It also includes a login method that validates user login by comparing the provided email and password 
with the stored ones.

Purpose: The purpose of this class is to represent a user in the system, allowing for secure storage 
and modification of user data while also enabling login functionality. The class ensures that certain 
attributes (like userid and datecreated) are immutable, reinforcing data integrity. The login method 
helps verify user credentials.

Key Components:
    - userid: A unique identifier for the user (immutable).
    - fname, lname, email, password: Attributes that store user data (mutable except for userid and datecreated).
    - datecreated: The date when the user was created (immutable).
    
Methods:
    - getUserid(), getFname(), getLname(), getEmail(), getDatecreated(): Getters for accessing user data.
    - setNewFname(), setNewLname(), setNewEmail(): Setters for updating mutable user data.
    - login(): Validates user credentials by comparing email and password attempts with the stored data.
*/ 

/* 
Update Log:
Last updated on 3.17.2025 by Temo Galinda to set email
Last updated on 3.13.2025 by Laura Estremera: Adjusted file placement.
Created on 2.20.2025 by Temo Galinda: Initializes user as an object.
*/

package csc450;

public class User {
    private final int userid; // This will never be changed
    private String fname; // This will be malleable
    private String lname; // This will be malleable
    private String email; // This will be malleable
    private String password; // Will be a hash of the password in the final implementation
    private final String datecreated; //This will also never change
    public User(int userid, String fname, String lname, String email, String password, String datecreated){
        this.userid = userid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.datecreated = datecreated;
    }
    public int getUserid() {return this.userid;}
    public String getFname() {return this.fname;}
    public String getLname() {return this.lname;}
    public String getEmail() {return this.email;}
    public String getDatecreated() {return this.datecreated;}
    public void setNewFname(String newFname){this.fname = newFname;}

    public void setNewLname(String newLname){this.lname = newLname;}

    public void setNewEmail(String newEmail){this.email = newEmail;}

    public void setNewPassword(String newPass){this.password = newPass;}

    public boolean login(String emailAttempt, String passAttempt){
        if(emailAttempt.equals(this.email) && passAttempt.equals(this.password)){return true;}
        else{return false;}
    }

}
