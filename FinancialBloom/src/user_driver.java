/* 
Code Author: Temo Galinda
Description Author: Laura Estremera

Description: This Java code defines a user_driver class, which is part of the csc450 package. 
The program demonstrates how to interact with a User class by creating a User object (test1) 
and performing operations such as displaying user information, simulating failed and successful 
login attempts, and updating the user's email.

Purpose: The purpose of this code is to simulate the behavior of a user account system where:
A user object is created with specific information (e.g., user ID, name, email, password, and account creation date).
The program showcases various methods available for interacting with the User object, such as:
    - Retrieving the user's first name, last name, email, and account creation date.
    - Simulate login attempts with correct and incorrect credentials.
    - Updating the user's email address.
This example highlights how to manage user data, perform login checks, and update user information in a system.

Key Components:
    - User object (test1): A representation of a user with details such as ID, first name, last name, email, password, 
    and account creation date.
    - Methods called on test1:
        - getFname(), getLname(), getEmail(), getDatecreated(): Retrieve specific user information.
        - login(): Simulates a login attempt and checks if the provided email and password match the stored credentials.
        setNewEmail(): Updates the email address of the user.
*/ 

/* 
Update Log:
Last updated on 3.27.2025 by Daniela Luna: Implemented further error checking & updated user driver to account for create account testing.
Last updated on 3.13.2025 by Laura Estremera: Adjusted file placement.
Created on 2.20.2025 by Temo Galinda: Initial code setup for user management system.
*/

package csc450;

import java.util.ArrayList;

public class user_driver {

    public static void main(String[] args){
        // ----------- Login Testing ------------------------
//        User test1 = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", "RyBallday113","2/23/2025");
//        System.out.println("This is the user information for: "+ test1.getFname() + ' ' + test1.getLname());
//        System.out.println("The email of this user is: " + test1.getEmail());
//        System.out.println("The date this account was created is: " + test1.getDatecreated());
//        System.out.println("The password of this user is 'RyBallday113', but it does not have a get function!");
//        System.out.println("We will now simulate a failed login using the email 'RuB@gmail.com' but the password 'RyBallday113.'");
//        System.out.println("This should return and print false.");
//        System.out.println(test1.login("RuB@gmail.com","RyBallday113"));
//        System.out.println("Now to simulate another failed login using the correct email, 'RyB@gmail.com' but the incorrect password 'RyNallday113.'");
//        System.out.println("This should also return and print false.");
//        System.out.println(test1.login("RyB@gmail.com","RyNallday113"));
//        System.out.println("Now to a successful login using the correct data - should return and print true.");
//        System.out.println(test1.login("RyB@gmail.com","RyBallday113"));
//        System.out.println("We will now change the email to 'RBG123@gmail.com'.");
//        test1.setNewEmail("RBG123@gmail.com");
//        System.out.println("Printing new email: " + test1.getEmail());
//        System.out.println("Testing of creating a user, changing information and logging in complete.");

        // ------------ Create Account Testing -----------------

        System.out.println("Welcome to Financial Bloom!");
        CreateAccount createAccount = new CreateAccount();
        User user = createAccount.getUser();

        if (user != null) {
            System.out.println("Testing account data:");
            System.out.println("User's name: " + user.getFname() + " " + user.getLname());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Account Created: " + user.getDatecreated());
            System.out.println("User ID: " + user.getUserid());
        }
    }
}
