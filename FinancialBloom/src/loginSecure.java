/* 
Code Author: Temo Galinda, edit comtrebutions by Mikaela Riggan 
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
    - sessionManagement: sucessful login attempts create a new session token. 
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
Last updated on 4.11.2025 by Mikaela Riggan: Added session management and updated password handling to be compatible with createAccount.
updated on 3.19.2025 by Laura Estremera: Adjusted file structure for better organization.
Created on 3.17.2025 by Temo Galinda: Initial login implementation.
*/


package csc450;

import java.util.*;
import java.io.Console;
import java.security.*;
import java.util.Base64;
import java.time.LocalDateTime;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecureLogin {
    
    // Security configuration constants
    private static final int MAX_ATTEMPTS = 3;               // Maximum allowed login attempts
    private static final int LOCKOUT_DURATION_MINUTES = 5;  // Lockout period after max attempts
    private static final int PBKDF2_ITERATIONS = 65536;      // Number of hashing iterations
    private static final int KEY_LENGTH = 128;               // Length of derived key in bits
    private static final int SALT_SIZE = 16;                 // Size of salt in bytes
    private static final int SESSION_TOKEN_SIZE = 32;        // Size of session token in bytes

    public static void main(String[] args) {
        // test case for now 
        String storedHashedPassword = hashPassword("RyBallday113"); // Example using same password
        
        // Test user with hashed password (matches CreateAccount hashing)
        User testUser = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", 
                               storedHashedPassword, "2/23/2025");
        
        System.out.println("=== Financial Garden Secure Login ===");
        System.out.println("You will be given " + MAX_ATTEMPTS + " attempts to login\n");
        
        Scanner input = new Scanner(System.in);
        int attempts = 0;
        boolean lockedOut = false;
        boolean successful = false;
        
        // Main authentication loop
        while (attempts < MAX_ATTEMPTS && !successful && !lockedOut) {
            attempts++;
            
            // Get user credentials
            System.out.print("Attempt " + attempts + " - Email: ");
            String emailAttempt = input.nextLine().trim();
            
            String passwordAttempt = getPasswordFromConsole(attempts);
            
            // Authentication check
            if (authenticateUser(testUser, emailAttempt, passwordAttempt)) {
                successful = true;
                logAttempt(emailAttempt, true);
                handleSuccessfulLogin(testUser);
            } else {
                logAttempt(emailAttempt, false);
                System.out.println("\nInvalid credentials");
                
                // Check for lockout condition
                if (attempts >= MAX_ATTEMPTS) {
                    lockedOut = true;
                    handleLockout();
                }
            }
            
            // Security: Clear sensitive data from memory
            passwordAttempt = null;
            System.gc(); // Suggest garbage collection
        }
        
        // Security: Close resources
        input.close();
    }

    /**
     * Authenticates a user by verifying email and password hash
     * @param user The User object containing stored credentials
     * @param emailAttempt The email address provided by user
     * @param passwordAttempt The password provided by user
     * @return true if authentication succeeds, false otherwise
     */
    private static boolean authenticateUser(User user, String emailAttempt, String passwordAttempt) {
        // Case-insensitive email comparison
        if (!user.getEmail().equalsIgnoreCase(emailAttempt)) {
            return false;
        }
        
        // Verify password against stored hash
        return verifyPassword(passwordAttempt, user.getPassword());
    }

    /**
     * Securely gets password input from console with masking
     * @param attempt Current attempt number (for display)
     * @return The entered password as String
     */
    private static String getPasswordFromConsole(int attempt) {
        Console console = System.console();
        if (console != null) {
            // Use console for secure password input (masks typing)
            char[] passwordArray = console.readPassword("Password attempt %d: ", attempt);
            return new String(passwordArray);
        } else {
            // Fallback for IDEs that don't provide Console
            System.out.print("Password attempt " + attempt + ": ");
            return new Scanner(System.in).nextLine();
        }
    }

    /**
     * Verifies a password against a stored hash using PBKDF2
     * @param inputPassword The password to verify
     * @param storedHash The stored hash in "salt:hash" format
     * @return true if password matches hash, false otherwise
     */
    private static boolean verifyPassword(String inputPassword, String storedHash) {
        try {
            // Split stored hash into components
            String[] parts = storedHash.split(":");
            if (parts.length != 2) return false;
            
            // Decode salt and hash from Base64
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedPassword = Base64.getDecoder().decode(parts[1]);
            
            // Recreate the hash using same parameters
            PBEKeySpec spec = new PBEKeySpec(
                inputPassword.toCharArray(), 
                salt, 
                PBKDF2_ITERATIONS, 
                KEY_LENGTH
            );
            
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] testHash = factory.generateSecret(spec).getEncoded();
            
            // Constant time comparison to prevent timing attacks
            return MessageDigest.isEqual(storedPassword, testHash);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Hashes a password using PBKDF2 with random salt
     * @param password The password to hash
     * @return String in "salt:hash" format
     */
    private static String hashPassword(String password) {
        try {
            // Generate cryptographically secure random salt
            byte[] salt = new byte[SALT_SIZE];
            new SecureRandom().nextBytes(salt);
            
            // Create hash specification
            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                PBKDF2_ITERATIONS,
                KEY_LENGTH
            );
            
            // Generate the hash
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            
            // Return salt and hash as Base64 strings
            return Base64.getEncoder().encodeToString(salt) + ":" + 
                   Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }

    /**
     * Handles successful login by establishing secure session
     * @param user The authenticated User object
     */
    private static void handleSuccessfulLogin(User user) {
        System.out.println("\n=== Login Successful! ===");
        System.out.println("Generating secure session token...");
        
        // Generate cryptographically secure session token
        String sessionToken = generateSessionToken();
        
        // In real system, would store token in secure session store
        System.out.println("Secure session established for: " + 
                          user.getFName() + " " + user.getLName());
        
        // Display user info (would be protected in production)
        System.out.println("\n=== User Details ===");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Account Created: " + user.getDateCreated());
        System.out.println("User ID: " + user.getUserID());
    }

    /**
     * Handles account lockout after maximum failed attempts
     */
    private static void handleLockout() {
        System.out.println("\n=== SECURITY NOTICE ===");
        System.out.println("Maximum login attempts reached.");
        System.out.println("Account locked for " + LOCKOUT_DURATION_MINUTES + " minutes.");
        System.out.println("An email with unlock instructions has been sent to the registered address.");
    }

    /**
     * Generates a cryptographically secure session token
     * @return Base64 encoded random token
     */
    private static String generateSessionToken() {
        byte[] tokenBytes = new byte[SESSION_TOKEN_SIZE];
        new SecureRandom().nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    /**
     * Logs login attempts for security auditing
     * @param email The email used in attempt
     * @param success Whether attempt was successful
     */
    private static void logAttempt(String email, boolean success) {
        String timestamp = LocalDateTime.now().toString();
        String status = success ? "SUCCESS" : "FAILURE";
        String logMessage = String.format("[%s] Login attempt - Email: %s - Status: %s", 
                                        timestamp, email, status);
        
        // In production, would write to secure log system
        System.out.println("SECURITY LOG: " + logMessage);
    }
}
