package csc450;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDate;


public class CreateAccount {

    private User newUser;

    public CreateAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("This page will allow a non-existing user to create a new account\n" +
                "Please enter the information required.");

        String fname;
        while (true) {
            System.out.print("Enter First Name: ");
            fname = input.nextLine();
            if (fname.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("First name must only contain letters. Please try again.");
            }
        }

        String lname;
        while (true) {
            System.out.print("Enter Last Name: ");
            lname = input.nextLine();
            if (lname.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Last name must only contain letters. Please try again.");
            }
        }

        // Email validation
        String email;
        while (true) {
            System.out.print("Enter Email Address: ");
            email = input.nextLine();

            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.com$")) {
                System.out.println("Invalid format. Email must contain '@' and end with '.com'");
                continue;
            }

            if (emailExists(email)) {
                System.out.println("An account with this email address already exists.");
                System.out.println("Directing you to the login page...");
                return;
            }

            break; // Email is valid and not a duplicate
        }


        // Password input + validation
        String password;
        while (true) {
            System.out.print("Enter your password: ");
            password = input.nextLine();

            if (password.length() < 7) {
                System.out.println("Password must be at least 7 characters long.");
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
            while (true) {
                String passwordConfirm;
                System.out.print("Enter your password confirmation: ");
                passwordConfirm = input.nextLine();
                if (!passwordConfirm.equals(password)) {
                    System.out.println("Passwords do not match. Please try again.");
                    continue;
                }
                break;
            }
            break;
        }

        Random rand = new Random();
        int userID = rand.nextInt(900000) + 100000;

        String dateCreated = LocalDate.now().toString();
        newUser = new User(userID, fname, lname, email, password, dateCreated);

        System.out.println("\nAccount successfully created!");
        System.out.println("Welcome, " + newUser.getFname() + " " + newUser.getLname() + "!");
    }

    // Placeholder method — to be replaced with DB check
    private boolean emailExists(String email) {
        // This should later query the database for the email
        // For now, it always returns false so testing can continue
        return false;
    }
    public User getUser() {
        return newUser;
    }

    // The save method
    private void saveUserToDatabase(User user) {
        String insertQuery = "INSERT INTO User (userid, fname, lname, email, password, datecreated) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertQuery)
        ) {
            stmt.setInt(1, user.getUserid());
            stmt.setString(2, user.getFname());
            stmt.setString(3, user.getLname());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.password); // no getter, use direct access if allowed
            stmt.setString(6, user.getDatecreated());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        saveUserToDatabase(newUser);
    }
}


