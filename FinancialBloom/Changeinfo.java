package csc450;
import java.util.Scanner;

public class Changeinfo {
    public static boolean isAlpha(String name) {
            char[] chars = name.toCharArray();
        
            for (char c : chars) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }
        
            return true;
        }
    
        public static void main(String[] args){
            User testUser = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", "RyBallday113","2/23/2025");
            System.out.println("For this functionality demonstration, the user will go down the available information to change and the system will check if the given infromation is valid.");
            System.out.println("If not the system will ask the user to change the information.");
            System.out.println("Starting with first name - only letters are allowed.");
            boolean fail = true;
            Scanner attemptScanner = new Scanner(System.in);
            while(fail){
                System.out.println("Provide a new first name.");
                String attempt = attemptScanner.nextLine();
                if(isAlpha(attempt)){
                    fail = false;
                    testUser.setNewFname(attempt);
                    System.out.println("New first name successfully set: " + testUser.getFname());
                }
                else{
                    System.out.println("Attempt unsuccessful - invalid characters in first name form.");
                }
            }
            fail = true;
            while(fail){
                System.out.println("Provide a new last name.");
                String attempt = attemptScanner.nextLine();
                if(isAlpha(attempt)){
                    fail = false;
                    testUser.setNewLname(attempt);
                    System.out.println("New last name successfully set: " + testUser.getLname());
                }
                else{
                    System.out.println("Attempt unsuccessful - invalid characters in first name form.");
                }
            }
            fail = true;
            while(fail){
                System.out.println("Provide a new email.");
                String attempt = attemptScanner.nextLine();
                testUser.setNewEmail(attempt);
                System.out.println("New email successfully set: " + testUser.getEmail());
                fail = false;
                // In the future email will correctly be validated, but it appears to require a library.
            }
            System.out.println("End of testing.");
            System.out.println(testUser.getFname());
            System.out.println(testUser.getLname());
            System.out.println(testUser.getEmail());
    }
}
