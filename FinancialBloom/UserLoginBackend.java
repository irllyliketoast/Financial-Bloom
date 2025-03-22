/* 
Script - not implemented 

Author: Daniela Luna

Description: This Java code allows a user to create a New Account. User will be directed here if they
enter login credentials and email is not found within database.

Purpose:
  - 
  
Key Components:
  - UserRepository: Interacts with the User class to create a new user object and stores the new
  User object in the database.
*/ 

/* 
Update Log:
Last updated on ...
Last updated on 3.21.2025 by Daniela Luna: Overrode former unimplemented class into CreateAccount class.
Created on 2.20.2025 by Daniela Luna: Initial code setup for user authentication.
*/

import java.util.Scanner;
import csc450.User; // is this the correct path?
import java.util.Random;
import java.time.LocalDate;

public class CreateAccount {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        System.out.println("This page will allow a non-existing user to create a new account\n" +
                "Please enter the information required.");

        Random rand = new Random();
        int userID = rand.nextInt(900000) + 100000;
        System.out.println("Your generated user ID is: " + userID);

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter your email address: ");
        String email = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        String dateCreated = LocalDate.now().toString();

        User newUser = new User(userID, firstName, lastName, email, password, dateCreated);

        System.out.println("\nAccount successfully created!");
        System.out.println("Welcome, " + newUser.getFirstName() + " " + newUser.getLastName() + "!");
    }
}
