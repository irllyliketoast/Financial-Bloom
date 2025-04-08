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

package csc450;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import java.time.LocalDate;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class CreateAccount {
    private User newUser;
    //add maxiumum length for inputs
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_EMAIL_LENGTH = 100;
    private static final int MIN_PASSWORD_LENGTH = 7;

    public CreateAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Create a New Account:");
        String fname = promptValidatedName(input, "First Name");
        String lname = promptValidatedName(input, "Last Name");
        String email = promptValidatedEmail(input);
        String rawPassword = promptValidatedPassword(input);
        String hashedPassword = hashPassword(rawPassword);

        int userID = generateSecureUserID();
        String dateCreated = LocalDate.now().toString();

        newUser = new User(userID, Fname, Lname, email, hashedPassword, dateCreated);

        System.out.println("\nAccount successfully created!");
        System.out.println("Welcome, " + newUser.getFName() + " " + newUser.getLName() + "!");
    }

    private String promptValidatedName(Scanner input, String fieldName) {
        while (true) {
            System.out.print("Enter " + fieldName + ": ");
            String name = input.nextLine().trim();

            if (!name.matches("[a-zA-Z]+")) {
                System.out.println(fieldName + " must only contain letters.");
            } else if (name.length() > MAX_NAME_LENGTH) {  //add max length
                System.out.println(fieldName + " is too long (max " + MAX_NAME_LENGTH + " characters).");
            } else {
                return name;
            }
        }
    }

    private String promptValidatedEmail(Scanner input) {
        while (true) {
            System.out.print("Enter Email Address: ");
            String email = input.nextLine().trim();

            if (email.length() > MAX_EMAIL_LENGTH) {
                System.out.println("Email is too long (max " + MAX_EMAIL_LENGTH + " characters).");
                continue;
            }

            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) { // allow for more emails without compromizing security
                System.out.println("Invalid email format.");
                continue;
            }

            if (emailExists(email)) {
                System.out.println("An account with this email already exists.");
                returnToLogin();
                return null;
            }

            return email;
        }
    }

    private String promptValidatedPassword(Scanner input) {
        while (true) {
            System.out.print("Enter your password: ");
            String password = input.nextLine();

            if (password.length() < MIN_PASSWORD_LENGTH) {
                System.out.println("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.");
                continue;
            }

            if (!password.matches(".*[A-Z].*")) {
                System.out.println("Password must contain at least one uppercase letter.");
                continue;
            }

            if (!password.matches(".*[@!\\-.$].*")) {
                System.out.println("Password must contain at least one special character: @, !, -, ., $");
                continue;
            }

            return password;
        }
    }

    private String hashPassword(String password) { //hash password using salting and SHA256 encryption
        try {
            byte[] salt = new byte[16];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(salt);

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }

    private int generateSecureUserID() { //changed random to secure random
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(900000) + 100000;
    }

    private boolean emailExists(String email) {
        // Replace with actual database check in production
        return false;
    }

    private void returnToLogin() {
        System.out.println("Redirecting to login page...");
        // Simulate navigation to login
    }

    public User getUser() {
        return newUser;
    }
}

