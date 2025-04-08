//Proposed changes to createAccount file :)

//-sanitized inputs (already partially done, TY Temo!)
// -passwords salted and stored using SHA255 hashing
// -increased security and flexibility for email validation 
// -added maximum inputs for security 
// -user ID generation now uses secureRandom which is a less predictable
// library

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

