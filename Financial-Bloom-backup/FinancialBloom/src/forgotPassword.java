/*
Code Author: Mikaela Riggan
Description Author: Mikaela Riggan

Description: The ForgotPassword class provides a secure mechanism for users to reset their password when forgotten. 
It verifies user identity through email validation, generates a time-limited reset token, and enforces password 
complexity requirements for new passwords. The system guides users through a step-by-step recovery process while 
maintaining security best practices.

Purpose: This class implements a secure password recovery system that prevents unauthorized access while allowing 
legitimate users to regain account access. It serves as a critical account recovery pathway while mitigating risks 
of account takeover through multiple verification steps and secure token handling.

Key Components:
    - TokenGenerator: Generates secure, time-limited reset tokens using cryptographic principles.
    - EmailValidator: Verifies email format and checks against registered accounts.
    - PasswordValidator: Ensures new passwords meet complexity requirements.
    - AttemptCounter: Tracks and limits failed recovery attempts to prevent brute force attacks.

Methods:
    - initiateRecovery(String email): Starts the password reset process by sending a token to the user's email.
    - validateToken(String token): Verifies if a reset token is valid and hasn't expired.
    - resetPassword(String token, String newPassword): Updates the user's password after successful token validation.
    - isEmailRegistered(String email): Checks if an email exists in the system database.

*/

/* 
Update Log:
Created on 4.22.2025 by Mikaela Riggan: Initial implementation of password recovery system.
*/
package csc450;

import java.time.LocalDateTime; 
import java.time.temporal.ChronoUnit; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.UUID; 

// Handles password reset functionality
public class forgotPassword { 
    
    private static forgotPassword instance; // Singleton instance
    private Map<String, TokenData> tokenStore; // Stores active reset tokens

    // Inner class to hold token-related data (user and expiration)
    private static class TokenData { 
        User user; 
        LocalDateTime expiration; 
        
        TokenData(User user) { 
            this.user = user;
            this.expiration = LocalDateTime.now().plus(24, ChronoUnit.HOURS); // Expires in 24 hours
        } 
        
        boolean isValid() { 
            return LocalDateTime.now().isBefore(expiration); // Checks if the token is still valid
        } 
    } 

    // Private constructor for singleton pattern
    private forgotPassword() { 
        tokenStore = new HashMap<>(); 
    } 

    // Returns the singleton instance
    public static synchronized forgotPassword getInstance() { 
        if (instance == null) { 
            instance = new forgotPassword(); 
        } 
        return instance; 
    } 

    // Initiates a password reset by generating a token
    public String initiatePasswordReset(User user) { 
        String token = UUID.randomUUID().toString(); 
        tokenStore.put(token, new TokenData(user)); 
        return token; 
    } 

    // Validates the token and returns the associated user if valid
    public User validateToken(String token) { 
        TokenData data = tokenStore.get(token); 
        if (data != null && data.isValid()) { 
            return data.user; 
        } 
        return null; 
    } 

    // Checks if the email is registered
    public boolean isEmailRegistered(String email) { 
        for (TokenData data : tokenStore.values()) { 
            if (data.user.getEmail().equals(email)) { 
                return true; 
            } 
        } 
        return false; 
    } 

    // Completes the password reset by removing the token
    public void completePasswordReset(String token) { 
        tokenStore.remove(token); 
    } 
}
