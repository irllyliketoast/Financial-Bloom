/*
Code Author: Daniela Luna  
Description Author: Laura Estremera  

Description: The budget_driver class is a test driver for validating the functionality of the Budget and Category classes.  
It verifies the integrity of category data, constructs category lists, and tests budget calculations.  

Purpose: This driver serves as a test environment to ensure that the Budget class correctly processes income and expense  
categories. It helps identify discrepancies in category data and confirms that the budget calculation logic functions  
as intended.  

Key Components:  
    - Predefined Category Names & Amounts:  
        - Includes both successful and unsuccessful income and expense category sets.  
    - categoryCheck(String[], double[]):  
        - Ensures that each category name has a corresponding numerical value.  
    - categoryListMaker(String[], double[]):  
        - Generates an array of Category objects from given names and amounts.  
    - errorCheck(Boolean):  
        - Returns a message indicating whether category data is correctly paired.  
    - main(String[]):  
        - Conducts structured tests to validate category pairing, category creation, and budget calculations.  

Test Scenarios:  
    1. Validating Category-Amount Pairing:  
        - Ensures that every category name has an associated amount.  
    2. Constructing Expense and Income Categories:  
        - Converts predefined category data into Category objects and prints their details.  
    3. Budget Creation and Calculation:  
        - Instantiates a Budget object and verifies its total income, expenses, and balance.  

Expected Outputs:  
    - Success message when category names and amounts match.  
    - Error message when category data is mismatched.  
    - Display of all created categories with corresponding amounts.  
    - Validation of the budget’s income, expense, and balance calculations.  

Future Enhancements:  
    - Implement additional test cases to cover edge scenarios (e.g., negative income, zero values).  
    - Improve error handling to prevent budget creation with incomplete data.  
    - Integrate with a graphical interface for more user-friendly budget analysis.  

Security Considerations:  
    - Ensure data consistency by validating user input before category and budget creation.  
    - Prevent negative balance miscalculations by properly handling expenses in budget logic.  
*/

/*  
Update Log:  
Last updated on 4.08.2025 by Laura Estremera: Included description.  
Created on 4.05.2025 by Daniela Luna: Initial implementation of budget_driver for Budget class validation.  
*/


package csc450;

public class budget_driver {
    static String[] sucessfulIncomeCatNames = {"Monthly Wage", "Side Hustle"};
    static String[] sucessfulExpenseCatNames = {"Rent + utilities", "Gas", "Food", "Car Payment"};
    static String[] unSucessfulIncomeCatNames = {"Monthly Wage"};
    static double[] successfulIncomeCatAmounts = {4160.00, 400.00};
    static double[] successfulExpenseCatAmounts = {-1200.00, -150.00, -300.00, -400.00};
    static double[] unSuccessfulExpenseCatAmounts = {-1200.00, -150.00, -300.00};
    
    public static boolean categoryCheck(String[] catNames, double[] catAmnts){
        //This is for making sure the name list
        if(catNames.length == catAmnts.length)
            return true;
        else
            return false;
    }
    public static Category[] categoryListMaker(String[] catNames, double[] catAmnts){
        int size = catNames.length;
        Category[] cats = new Category[size];
        int index = 0;
        while(index < size ){
            Category addCat = new Category(catNames[index], catAmnts[index]);
            cats[index] = addCat;
            index++;
        }
        return cats;
    }
    public static String errorCheck(Boolean check){
        if(check)
            return "Categories and numbers match.";
        else
            return "Error - categories and numbers do not match.";
    }
    public static void main(String[] args){
        System.out.println("Testing for budget and category classes.");
        System.out.println("Test 1 - Making sure categories and amounts are equivalent.");
        System.out.println("Testing correct lists - should print success message.");
        System.out.println(errorCheck(categoryCheck(sucessfulIncomeCatNames, successfulIncomeCatAmounts)));
        System.out.println("Testing incorrect lists - Category names < category amounts - should print error message.");
        System.out.println(errorCheck(categoryCheck(unSucessfulIncomeCatNames, successfulIncomeCatAmounts)));
        System.out.println("Testing incorrect lists - Category names > category amounts - should print error message.");
        System.out.println(errorCheck(categoryCheck(sucessfulExpenseCatNames, unSuccessfulExpenseCatAmounts)));
        System.out.println("Test 2 - Creating the categories for the budgets.");
        Category[] expenseList = categoryListMaker(sucessfulExpenseCatNames, successfulExpenseCatAmounts);
        System.out.println("Printing Expense Categories");

        for(Category cat: expenseList){
            System.out.printf("%s: $%.2f\n", cat.getName(), cat.getAmount());
        }

        System.out.println("Printing Income Categories");
        Category[] incomeList = categoryListMaker(sucessfulIncomeCatNames, successfulIncomeCatAmounts);
        for(Category cat: incomeList){
            System.out.printf("%s: $%.2f\n", cat.getName(), cat.getAmount());
        }

        System.out.println("Test 3 - Creating the budget.");
        Budget budge = new Budget("Test Budget", incomeList, expenseList);
        System.out.printf("%s: %s\n", "Budget Name", budge.getName());
        System.out.printf("%s: $%.2f\n", "Budget Total Income", budge.getIncome());
        System.out.printf("%s: $%.2f\n", "Budget Total Expense", budge.getExpense());
        System.out.printf("%s: $%s\n", "Budget Balance", budge.getBalance());
        System.out.println("Testing done.");
    }

}
