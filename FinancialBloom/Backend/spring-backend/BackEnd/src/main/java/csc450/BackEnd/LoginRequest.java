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

package csc450.BackEnd;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
