/* 
Author: Temo Galinda

Description: The Changeinfo class allows a user to update their first name, last name, and email while 
ensuring name inputs contain only alphabetical characters. The system prompts the user for new values, 
validates the input, and updates the corresponding user attributes.

Purpose: This class demonstrates a basic user information update system with input validation. The 
first name and last name must contain only letters, while email validation is currently a placeholder 
for future implementation. The goal is to ensure data integrity while allowing users to modify 
their personal details.

Key Components:
    - isAlpha(): A helper method that checks if a string contains only alphabetic characters.
    - testUser: A predefined user instance used for testing updates.
    - fail: A boolean flag used to control input validation loops.
    - attemptScanner: A Scanner object for user input.

Methods:
    - isAlpha(String name): Returns true if the input contains only letters; false otherwise.
    - main(): The entry point of the program that prompts the user for input and updates their 
      information accordingly.

Future Enhancements:
    - Implement robust email validation using a regex pattern or a dedicated library.
    - Add further constraints for name inputs (e.g., length restrictions, case formatting).
    - Allow modifications for additional user attributes beyond name and email.

Security Considerations:
    - The system currently lacks validation for email format, which should be addressed in future updates.
    - User inputs are not sanitized or stored securely, so future versions should consider 
      preventing malicious input.
*/

/* 
Update Log:
Last updated on 3.19.2025 by Laura Estremera: Adjusted file structure for better organization.
Created on 3.17.2025 by Temo Galinda: Initial implementation of user information updates.
*/


package csc450;
import java.util.Scanner;

public class Changeinfo {
    public static boolean isAlpha(String name) {
            char[] chars = name.toCharArray();
        
            for (char c : chars) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }
        
            return true;
        }
    
        public static void main(String[] args){
            User testUser = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", "RyBallday113","2/23/2025");
            System.out.println("For this functionality demonstration, the user will go down the available information to change and the system will check if the given infromation is valid.");
            System.out.println("If not the system will ask the user to change the information.");
            System.out.println("Starting with first name - only letters are allowed.");
            boolean fail = true;
            Scanner attemptScanner = new Scanner(System.in);
            while(fail){
                System.out.println("Provide a new first name.");
                String attempt = attemptScanner.nextLine();
                if(isAlpha(attempt)){
                    fail = false;
                    testUser.setNewFname(attempt);
                    System.out.println("New first name successfully set: " + testUser.getFname());
                }
                else{
                    System.out.println("Attempt unsuccessful - invalid characters in first name form.");
                }
            }
            fail = true;
            while(fail){
                System.out.println("Provide a new last name.");
                String attempt = attemptScanner.nextLine();
                if(isAlpha(attempt)){
                    fail = false;
                    testUser.setNewLname(attempt);
                    System.out.println("New last name successfully set: " + testUser.getLname());
                }
                else{
                    System.out.println("Attempt unsuccessful - invalid characters in first name form.");
                }
            }
            fail = true;
            while(fail){
                System.out.println("Provide a new email.");
                String attempt = attemptScanner.nextLine();
                testUser.setNewEmail(attempt);
                System.out.println("New email successfully set: " + testUser.getEmail());
                fail = false;
                // In the future email will correctly be validated, but it appears to require a library.
            }
            System.out.println("End of testing.");
            System.out.println(testUser.getFname());
            System.out.println(testUser.getLname());
            System.out.println(testUser.getEmail());
    }
}
