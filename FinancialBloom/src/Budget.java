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
    - Income & Expense Streams: ArrayLists of Category objects representing financial transactions.  
    - Total Income & Total Expense: Automatically calculated based on provided income and expense categories.   

Methods:  
    - getName(): Retrieves the name of the budget.  
    - getIncome(): Returns the total income calculated from income streams.  
    - getExpense(): Returns the total expenses calculated from expense streams.  
    - getBalance(): Calculates the current balance of the budget.  
    - setName(String newName): Updates the budget’s name.  
    - setIncomeStream(Category[] newIncomeStream): Updates the income stream and recalculates total income.  
    - setExpenseStream(Category[] newExpenseStream): Updates the expense stream and resets total expenses.  
    - getIncomeStreams(): Returns the array of income categories.  
    - getExpenseStreams(): Returns the array of expense categories.
    - addCategory(Category newCat): adds an income or expense to the appropriate ArrayList and increments the associated value.

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
Updated on 4.10.2025 by Temo Galindo: Changed the arrays to arraylists for easier addition of income or expense, added addCategory method, changed balance from an instance variable to a method.
Updated on 4.08.2025 by Laura Estremera: Included description.  
Created on 4.05.2025 by Daniela Luna: Initial implementation of Budget class with category-based financial tracking.  
*/

package csc450;
import java.util.ArrayList;

public class Budget {
    private String name;
    private double totalIncome;
    private double totalExpense;
    private ArrayList<Category> incomeStreams;
    private ArrayList<Category> expenseStreams;
    public Budget(String nName, Category[] incomes, Category[] expenses){
        this.incomeStreams = new ArrayList<>();
        this.expenseStreams = new ArrayList<>();
        this.name = nName;
        for(Category income: incomes){
            this.incomeStreams.add(income);
        }
        for(Category expense: expenses){
            this.expenseStreams.add(expense);
        }
        for(Category income: this.incomeStreams){
            this.totalIncome += income.getAmount();
        }
        for(Category expense: this.expenseStreams){
            this.totalExpense += expense.getAmount();
        }
    }
    public String getName() {return this.name;}
    public double getIncome() {return this.totalIncome;}
    public double getExpense() {return this.totalExpense;}
    public double getBalance() {return this.totalIncome + this.totalExpense;} // Changed to a method so it is always using the updated values.
    public void setName(String newName) {this.name = newName;}
    public void setIncomeStream(Category[] newIncomeStream) {
        this.incomeStreams.clear();
        for(Category income: newIncomeStream){
            this.incomeStreams.add(income);
        }
        this.totalIncome = 0.0;
        for(Category income: this.incomeStreams){
            this.totalIncome += income.getAmount();
        }
    }
    public void setExpenseStream(Category[] newExpenseStream){
        this.expenseStreams.clear();
        for(Category expense: newExpenseStream){
            this.expenseStreams.add(expense);
        }
        this.totalExpense = 0.0;
        for(Category expense: this.expenseStreams){
            this.totalIncome += expense.getAmount();
        }
    }
    public void addCategory(Category newCat){
        if(newCat.getAmount() > 0){
            this.incomeStreams.add(newCat);
            this.totalIncome += newCat.getAmount();
        }else{
            this.expenseStreams.add(newCat);
            this.totalExpense += newCat.getAmount();
        }
        
    }
    public ArrayList<Category> getIncomeStreams() {return this.incomeStreams;}
    public ArrayList<Category> getExpenseStreams() {return this.expenseStreams;}

}
