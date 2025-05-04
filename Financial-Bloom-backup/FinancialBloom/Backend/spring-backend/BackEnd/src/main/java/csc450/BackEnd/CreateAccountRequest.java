/*  
Code Author: Temo Galinda  
Description Author: Laura Estremera  

Description: The CreateAccount class facilitates the creation of new user accounts with enhanced security measures.  
It validates user input for names, emails, and passwords while enforcing constraints to improve data integrity.  
The class also generates secure user IDs and hashes passwords using PBKDF2WithHmacSHA256 with salting for added security.  

Purpose: This class ensures that user registration follows best practices for input validation and security.  
It prevents common vulnerabilities such as weak passwords, predictable user IDs, and excessive input lengths.  
The system also verifies that email addresses are formatted correctly and prevents duplicate accounts.  

Key Components:  
    - Input Validation: Ensures names contain only alphabetical characters and adhere to a maximum length.  
    - Email Validation: Enforces structure constraints and prevents duplicate account creation.  
    - Secure Password Handling: Implements password hashing with a unique salt and PBKDF2WithHmacSHA256 encryption.  
    - Secure User ID Generation: Uses SecureRandom to generate less predictable user IDs.  

Methods:  
    - promptValidatedName(Scanner input, String fieldName): Validates and sanitizes user-provided names.  
    - promptValidatedEmail(Scanner input): Validates the email format and checks for existing accounts.  
    - promptValidatedPassword(Scanner input): Ensures password complexity requirements are met.  
    - hashPassword(String password): Hashes and securely stores passwords using salting and PBKDF2WithHmacSHA256.  
    - generateSecureUserID(): Generates a six-digit secure user ID.  
    - emailExists(String email): Placeholder for checking whether an email is already registered.  
    - returnToLogin(): Simulates a redirection to the login page.  
    - getUser(): Retrieves the newly created user instance.  

Future Enhancements:  
    - Implement database integration to store user accounts securely.   
    - Introduce multi-factor authentication (MFA) for additional security.   

Security Considerations:  
    - Inputs are sanitized to mitigate injection attacks.  
    - Passwords are never stored in plain text, reducing exposure to breaches.  
    - The SecureRandom class is used instead of traditional random number generation to prevent predictable user IDs.  
    - The class avoids common regex vulnerabilities while maintaining flexibility in email validation.  
*/

/*  
Update Log:  
Last updated on 4.08.2025 by Mikaela Riggan: Enhanced password security, improved input validation, and updated documentation.  
Created on 4.05.2025 by Temo Galinda: Initial implementation of account creation with input validation.  
*/

package csc450.BackEnd;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAccountRequest {
    @JsonProperty("fName")
    private String fName;
    @JsonProperty("lName")
    private String lName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public CreateAccountRequest() {}

    public String getFName() { return fName; }
    public void setFName(String fName) {
        System.out.println("Setting fName: " + fName); // for testing purposes. delete after testing
        this.fName = fName; }

    public String getLName() { return lName; }
    public void setLName(String lName) {
        System.out.println("Setting lName: " + lName); // for testing purposes. delete after testing
        this.lName = lName; }

    public String getUsername() {
        return username;
    }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
