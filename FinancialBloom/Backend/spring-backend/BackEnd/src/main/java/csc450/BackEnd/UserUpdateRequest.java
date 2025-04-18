/* 
Code Author: Temo Galinda
Description Author: Laura Estremera

Description: The UserUpdateRequest class allows a user to update their first name, last name, and email while
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


package csc450.BackEnd;

public class UserUpdateRequest {
    private String newFName;
    private String newLName;
    private String newEmail;

    public UserUpdateRequest() {}

    public String getNewFName() { return newFName; }
    public void setNewFName(String newFName) { this.newFName = newFName; }

    public String getNewLName() { return newLName; }
    public void setNewLName(String newLName) { this.newLName = newLName; }

    public String getNewEmail() { return newEmail; }
    public void setNewEmail(String newEmail) { this.newEmail = newEmail; }
}
