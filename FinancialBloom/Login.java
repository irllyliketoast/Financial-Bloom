package csc450;
import java.util.Scanner;

public class Login{
    public static void main(String[] args){
        User testUser = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", "RyBallday113","2/23/2025");
        System.out.println("This will simulate the login experience for Financial Garden.");
        System.out.println("The given example user login email is: RyB@gmail.com");
        System.out.println("The given example user login password is: RyBallday113");
        System.out.println("You will be given three tries to login to the account - after which you will be locked out.");
        int attempts = 1;
        boolean successful = false;
        Scanner emailScanner = new Scanner(System.in);
        Scanner passScanner = new Scanner(System.in);
        while(attempts < 4 && !successful){
            System.out.println("Email attempt " + attempts + ". Please enter an email.");
            String emailAttempt = emailScanner.nextLine();
            System.out.println("Password attempt " + attempts + ". Please enter a password.");
            String passAttempt = passScanner.nextLine();
            if(testUser.login(emailAttempt, passAttempt)){
                successful = true;
            }
            else{
                System.out.println("Invalid Credentials.");
                attempts += 1;
            }
            
        }
        emailScanner.close();
        passScanner.close();
        if(successful){
            System.out.println("Login Successful!");
            System.out.println("Information for user " + testUser.getFname() + " " + testUser.getLname());
            System.out.println("Email: " + testUser.getEmail());
            System.out.println("Date Created: " + testUser.getDatecreated());
            System.out.println("User Id:  " + testUser.getUserid());
        }
        else{
            System.out.println("Sorry, but you have reached the maximum attempts of " + attempts + ".");
            System.out.println("We may lock the account or simply require an email unlock in the future implementation.");

        }
        
    }
}
