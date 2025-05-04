/*
Code Author: Temo Galinda
Description Author: Laura Estremera

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
Last updated on 4.3.2025 by Laura Estremera and Daniela Luna: Naming conventions.
Last updated on 3.17.2025 by Temo Galinda: Adjusted to set email.
Last updated on 3.13.2025 by Laura Estremera: Adjusted file placement.
Created on 2.20.2025 by Temo Galinda: Initializes user as an object.
*/

package csc450.BackEnd;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userID; // Primary key

    @Column(name = "f_name", nullable = false)
    private String fName;

    @Column(name = "l_name", nullable = false)
    private String lName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String dateCreated;

    // Default constructor required by JPA
    public User() {}

    // This constructor is used by Hibernate during save- it autogenerates the UserID
    public User(String username, String fName, String lName, String email, String password, String dateCreated) {
        this.username = username;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    // Getters
    public int getUserID() { return userID; }
    public String getFName() { return fName; }
    public String getLName() { return lName; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getDateCreated() { return dateCreated; }

    // Setters
    public void setNewFName(String newFName) { this.fName = newFName; }
    public void setNewLName(String newLName) { this.lName = newLName; }
    public void setNewUsername(String username) { this.username = username; }
    public void setNewEmail(String newEmail) { this.email = newEmail; }
    public void setNewPassword(String newPass) { this.password = newPass; }
    public String getPassword() {
        return this.password;
    }


    // NOTE: login logic is normally handled by a service layer or Spring Security
    public boolean login(String emailAttempt, String passAttempt) {
        return emailAttempt.equals(this.email) && passAttempt.equals(this.password);
    }
}
