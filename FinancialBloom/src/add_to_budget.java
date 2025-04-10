/*
Code Author: Temo Galindo
Description Author: Temo Galindo

Description: This file is to test the addCategory functionality of the Budget class. 

Purpose: This driver serves as a test environment to ensure that the Budget class correctly adds new income and 
expense streams. It shows that the method correctly adds the category to either income or expense, and updates the
budget total as well.


Test Scenarios:  
    1. Adding another income stream 
        - Ensures that total income and balance are correctly affected.
    2. Adding another expense stream
        - Ensures that total expense and balance are correctly affected.

Expected Outputs:  
    After making the budget:
    Total Income: $4160.00
    Total Expense: $-1750.00
    Balance: $2410.0
    After adding a category with an income of 250.00 to the budget:
    Total Income: $4410.00
    Total Expense: $-1750.00
    Balance: $2660.0
    After adding a category with an expense of -450.00 to the budget:
    Total Income: $4410.00
    Total Expense: $-2200.00
    Balance: $2210.0
    
Future Enhancements:  
    - Implement error handling for categories with 0.00 income/expense (frontend handling?)

Security Considerations:  
    - I'm not sure if there are any to be honest.
*/

/*  
Update Log:  
Created on 4.10.2025 by Temo Galindo: Initial implementation of add_to_budget for Budget class validation.  
*/
package csc450;

public class add_to_budget {
    public static void main(String[] args){
        Category income1 = new Category("Monthly Salary", 4160.00);
        Category income2 = new Category("Soap Business", 250.00);
        Category expense1 = new Category("Monthly Bills", -1750.00);
        Category expense2 = new Category("Car Repair", -450.00);
        Category[] incomes = {income1};
        Category[] expenses = {expense1};
        Budget baseBudget = new Budget("Test Budget", incomes, expenses);
        System.out.printf("%s: %s\n", "Base Budget Name", baseBudget.getName());
        System.out.printf("%s: $%.2f\n", "Base Budget Total Income", baseBudget.getIncome());
        System.out.printf("%s: $%.2f\n", "Base Budget Total Expense", baseBudget.getExpense());
        System.out.printf("%s: $%s\n", "Base Budget Balance", baseBudget.getBalance());
        System.out.println("Test 1: Adding an income.");
        System.out.println("--Now Adding--");
        System.out.printf("%s: %s $%.2f\n", "Income", income2.getName(), income2.getAmount());
        baseBudget.addCategory(income2);
        System.out.printf("%s: $%.2f\n", "Modified Budget Total Income", baseBudget.getIncome());
        System.out.printf("%s: $%.2f\n", "Modified Budget Total Expense", baseBudget.getExpense());
        System.out.printf("%s: $%s\n", "Modified Budget Balance", baseBudget.getBalance());
        System.out.println("Test 2: Adding an expense.");
        System.out.println("--Now Adding--");
        System.out.printf("%s: %s -$%.2f\n", "Expense", expense2.getName(), expense2.getAmount());
        baseBudget.addCategory(expense2);
        System.out.printf("%s: $%.2f\n", "Final Budget Total Income", baseBudget.getIncome());
        System.out.printf("%s: $%.2f\n", "Final Budget Total Expense", baseBudget.getExpense());
        System.out.printf("%s: $%s\n", "Final Budget Balance", baseBudget.getBalance());

    }
    
}
