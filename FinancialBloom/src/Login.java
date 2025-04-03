/* 
Code Author: Temo Galinda
Description Author: Laura Estremera

Description: This Login class simulates a login experience for the Financial Garden system. It interacts 
with the User class to validate user credentials by prompting the user for an email and password. The 
user has up to three attempts to enter the correct credentials before being locked out. 

Purpose: The purpose of this class is to demonstrate a basic authentication mechanism, enforcing login 
security through limited attempts. It ensures that only users with valid credentials can access their 
account details. Future implementations may introduce account locking or email verification for enhanced security.

Key Components:
    - testUser: A predefined user instance used for login simulation.
    - attempts: Tracks the number of login attempts, limiting them to three.
    - successful: A boolean flag indicating whether login was successful.
    - emailScanner, passScanner: Scanner objects for user input.

Methods:
    - main(): The entry point of the program that simulates user login attempts.
    - testUser.login(): Calls the login method from the User class to validate credentials.
    - Console Outputs: Provides feedback on successful login or failed attempts.

Security Considerations:
    - The system currently stores user credentials in plaintext, which should be replaced with 
      secure password hashing in real-world applications.
    - A future version may implement account locking or email-based recovery after multiple failed attempts.
*/

/* 
Update Log:
Last updated on 3.19.2025 by Laura Estremera: Adjusted file structure for better organization.
Created on 3.17.2025 by Temo Galinda: Initial login implementation.
*/


package csc450;
import java.util.Scanner;

public class Login{
    public static void main(String[] args){
        User testUser = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", "RyBallday113","2/23/2025");
        System.out.println("This will simulate the login experience for Financial Garden.");
        System.out.println("The given example user login email is: RyB@gmail.com");
        System.out.println("The given example user login password is: RyBallday113");
        System.out.println("You will be given three tries to login to the account - after which you will be locked out.");
        int attempts = 1;
        boolean successful = false;
        Scanner emailScanner = new Scanner(System.in);
        Scanner passScanner = new Scanner(System.in);
        while(attempts < 4 && !successful){
            System.out.println("Email attempt " + attempts + ". Please enter an email.");
            String emailAttempt = emailScanner.nextLine();
            System.out.println("Password attempt " + attempts + ". Please enter a password.");
            String passAttempt = passScanner.nextLine();
            if(testUser.login(emailAttempt, passAttempt)){
                successful = true;
            }
            else{
                System.out.println("Invalid Credentials.");
                attempts += 1;
            }
            
        }
        emailScanner.close();
        passScanner.close();
        if(successful){
            System.out.println("Login Successful!");
            System.out.println("Information for user " + testUser.getFname() + " " + testUser.getLname());
            System.out.println("Email: " + testUser.getEmail());
            System.out.println("Date Created: " + testUser.getDatecreated());
            System.out.println("User Id:  " + testUser.getUserid());
        }
        else{
            System.out.println("Sorry, but you have reached the maximum attempts of " + attempts + ".");
            System.out.println("We may lock the account or simply require an email unlock in the future implementation.");

        }
        
    }
}
