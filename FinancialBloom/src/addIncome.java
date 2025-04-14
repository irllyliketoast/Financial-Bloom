package csc450;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Code Author: Mikaela Riggan
 * Description Author: Mikaela Riggan
 * 
 * Description: The Income class represents a user's income entry with amount, source, and date.
 * The IncomeRepository class manages a collection of income entries associated with users.
 * 
 * Purpose: To provide functionality for adding, storing, and retrieving income data for users.
 */
public class addIncome {
    private final User user;
    private final double amount;
    private final String source;
    private final String date;
    
    public addIncome(User user, double amount, String source, String date) {
        this.user = user;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }
    
    public User getUser() { return this.user; }
    public double getAmount() { return this.amount; }
    public String getSource() { return this.source; }
    public String getDate() { return this.date; }
    
    @Override
    public String toString() {
        return String.format("User: %s %s, Amount: $%.2f, Source: %s, Date: %s",
                user.getFName(), user.getLName(), amount, source, date);
    }
}

/**
 * Repository class for managing income entries
 */
class IncomeRepository {
    private static List<addIncome> incomeEntries = new ArrayList<>();
    
    /**
     * Adds an income entry to the repository
     * @param income The income object to add
     */
    public static void addIncome(addIncome income) {
        incomeEntries.add(income);
        System.out.println("Income successfully added:");
        System.out.println(income);
    }
    
    /**
     * Gets all income entries for a specific user
     * @param user The user to filter by
     * @return List of income entries for the user
     */
    public static List<addIncome> getIncomeByUser(User user) {
        List<addIncome> userIncomes = new ArrayList<>();
        for (addIncome income : incomeEntries) {
            if (income.getUser().getUserID() == user.getUserID()) {
                userIncomes.add(income);
            }
        }
        return userIncomes;
    }
    
    /**
     * Interactive method to add income through user input
     * @param user The user to associate with the income
     */
    public static void addIncomeInteractive(User user) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== Add New Income ===");
        System.out.println("User: " + user.getFName() + " " + user.getLName());
        
        // Get amount
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Enter amount: $");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount > 0) {
                    validAmount = true;
                } else {
                    System.out.println("Amount must be positive. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount format. Please enter a number.");
            }
        }
        
        // Get source
        System.out.print("Enter income source: ");
        String source = scanner.nextLine();
        
        // Get date
        System.out.print("Enter date (format MM/DD/YYYY): ");
        String date = scanner.nextLine();
        
        // Create and add income
        addIncome newIncome = new addIncome(user, amount, source, date);
        addIncome(newIncome);
    }
}

