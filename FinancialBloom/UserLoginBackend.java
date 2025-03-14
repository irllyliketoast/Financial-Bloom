/* 
Script - not implemented 

Author: Daniela Luna

Description: This Java code implements an authentication service within an application. 
It provides functionalities for user login and password reset. The service is designed to manage user 
accounts by verifying credentials, locking accounts after too many failed login attempts, and sending 
password reset emails with temporary passwords.

Purpose:
  - Login functionality: The login method authenticates users by checking their username and password. 
  It handles the scenario where the account gets locked after three consecutive failed login attempts 
  and resets the counter after a successful login.
  - Password reset functionality: The resetPassword method allows users to reset their password by 
  sending them a temporary password to their registered email address.
  - Email service: The sendResetEmail method handles sending an email to the user with their temporary 
  password, enabling them to regain access to their account.
  
Key Components:
  - UserRepository: Interacts with the database to fetch and update user data.
  - JavaMailSender: Sends email notifications to the user.
  - BCryptPasswordEncoder: Ensures passwords are securely stored and validated during login.
*/ 

/* 
Update Log:
Last updated on ...
Last updated on 3.13.2025 by Laura Estremera: Adjusted file placement.
Created on 2.20.2025 by Daniela Luna: Initial code setup for user authentication.
*/

package com.example.auth.service;

import java.util;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return "User not found.";
        }

        User user = userOptional.get();

        if (user.isLocked()) {
            return "Account is locked. Click 'Forgot Password?' to reset.";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setFailedAttempts(user.getFailedAttempts() + 1);

            if (user.getFailedAttempts() >= 3) {
                user.setLocked(true);
                userRepository.save(user);
                return "Too many failed attempts. Forgot Password?";
            }

            userRepository.save(user);
            return "Invalid credentials. Attempt " + user.getFailedAttempts() + "/3.";
        }

        user.setFailedAttempts(0);
        userRepository.save(user);
        return "Login successful!";
    }

    public String resetPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "Email not found.";
        }

        User user = userOptional.get();
        String tempPassword = "Temp1234"; // You can generate a more secure temp password

        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setLocked(false);
        user.setFailedAttempts(0);
        userRepository.save(user);

        sendResetEmail(email, tempPassword);
        return "Password reset email sent.";
    }

    private void sendResetEmail(String email, String tempPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Password Reset Request");
            helper.setText("Your temporary password is: " + tempPassword + ". Please change it after logging in.");

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
