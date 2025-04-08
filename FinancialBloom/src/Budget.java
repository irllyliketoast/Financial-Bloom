/*  
Code Author: Daniela Luna  
Description Author: Laura Estremera  

Description: The Budget class represents a financial budget, tracking income and expenses through categorized streams.  
It calculates the total income, total expenses, and balance based on provided categories. The class allows for retrieval  
and modification of budget attributes, ensuring accurate financial tracking.  

Purpose: This class provides a structured way to manage and analyze financial data. By categorizing income and expenses,  
users can easily track their financial standing. The system maintains an up-to-date balance by automatically recalculating  
totals when income or expense streams are modified.  

Key Components:  
    - Budget Name: A user-defined name for the budget instance.  
    - Income & Expense Streams: Arrays of Category objects representing financial transactions.  
    - Total Income & Total Expense: Automatically calculated based on provided income and expense categories.  
    - Balance: Derived by summing total income and total expenses.  

Methods:  
    - getName(): Retrieves the name of the budget.  
    - getIncome(): Returns the total income calculated from income streams.  
    - getExpense(): Returns the total expenses calculated from expense streams.  
    - getBalance(): Retrieves the current balance of the budget.  
    - setName(String newName): Updates the budget’s name.  
    - setIncomeStream(Category[] newIncomeStream): Updates the income stream and recalculates total income.  
    - setExpenseStream(Category[] newExpenseStream): Updates the expense stream and resets total expenses.  
    - getIncomeStreams(): Returns the array of income categories.  
    - getExpenseStreams(): Returns the array of expense categories.  

Future Enhancements:  
    - Implement error handling for null or empty category arrays.  
    - Improve balance calculation by properly subtracting expenses from income.  
    - Introduce methods for adding/removing individual income or expense categories.  
    - Integrate with a database or file system for persistent budget storage.  

Security Considerations:  
    - Ensure proper validation when modifying income or expense streams to prevent incorrect calculations.  
    - Consider implementing data encryption for sensitive financial information when integrated with storage systems.  
*/

/*  
Update Log:  
Last updated on 4.08.2025 by Laura Estremera: Included description.  
Created on 4.05.2025 by Daniela Luna: Initial implementation of Budget class with category-based financial tracking.  
*/

package csc450;

public class Budget {
    private String name;
    private double totalIncome;
    private double totalExpense;
    private double balance;
    private Category[] incomeStream;
    private Category[] expenseStream;
    public Budget(String nName, Category[] incomes, Category[] expenses){
        this.name = nName;
        this.incomeStream = incomes;
        this.expenseStream = expenses;
        for(Category income: this.incomeStream){
            this.totalIncome += income.getAmount();
        }
        for(Category expense: this.expenseStream){
            this.totalExpense += expense.getAmount();
        }
        this.balance = this.totalIncome + this.totalExpense;
    }
    public String getName() {return this.name;}
    public double getIncome() {return this.totalIncome;}
    public double getExpense() {return this.totalExpense;}
    public double getBalance() {return this.balance;}
    public void setName(String newName) {this.name = newName;}
    public void setIncomeStream(Category[] newIncomeStream) {
        this.incomeStream = newIncomeStream;
        this.totalIncome = 0.0f;
        for(Category income: this.incomeStream){
            this.totalIncome += income.getAmount();
        }
    }
    public void setExpenseStream(Category[] newExpenseStream){
        this.expenseStream = newExpenseStream;
        this.totalExpense = 0.0f;
    }
    public Category[] getIncomeStreams() {return this.incomeStream;}
    public Category[] getExpenseStreams() {return this.expenseStream;}

}
