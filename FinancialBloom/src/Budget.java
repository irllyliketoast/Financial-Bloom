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
Updated on 4.28.2025 by Mikaela Riggan: Added debt methods and savings methods to the Budget class. Tracks debt as negative values and allows payments to reduce it. Savings has posiitive values and allows withdrawals.
Updated on 4.14.2025 by Temo Galindo: Added the instance variable goal, which is a milestone, associated settors and getters, as well a second constructor so that if goal is left off then it is initialized to null.
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
    private double totalDebt;
    private double totalSavings;
    private Milestone goal;
    private ArrayList<Category> incomeStreams;
    private ArrayList<Category> expenseStreams;
    private ArrayList<Category> debtStreams;
    private ArrayList<Category> savingsStreams;

    public Budget(String nName, Category[] incomes, Category[] expenses) {
        this(nName, incomes, expenses, null);
    }

    public Budget(String nName, Category[] incomes, Category[] expenses, Milestone newGoal) {
        this.incomeStreams = new ArrayList<>();
        this.expenseStreams = new ArrayList<>();
        this.debtStreams = new ArrayList<>();
        this.savingsStreams = new ArrayList<>();
        this.name = nName;
        
        // Initialize totals
        this.totalIncome = 0.0;
        this.totalExpense = 0.0;
        this.totalDebt = 0.0;
        this.totalSavings = 0.0;

        // Process incomes
        for (Category income : incomes) {
            addIncome(income);
        }
        
        // Process expenses
        for (Category expense : expenses) {
            addExpense(expense);
        }
        
        this.goal = newGoal;
    }

    // ========== Income Methods ==========
    public void addIncome(Category income) {
        if (income.getAmount() <= 0) {
            throw new IllegalArgumentException("Income amount must be positive");
        }
        this.incomeStreams.add(income);
        this.totalIncome += income.getAmount();
    }

    // ========== Expense Methods ==========
    public void addExpense(Category expense) {
        if (expense.getAmount() >= 0) {
            throw new IllegalArgumentException("Expense amount must be negative");
        }
        this.expenseStreams.add(expense);
        this.totalExpense += expense.getAmount();
    }

    // ========== Debt Methods ==========
    public void addDebt(Category debt) {
        if (debt.getAmount() >= 0) {
            throw new IllegalArgumentException("Debt amount should be negative");
        }
        this.debtStreams.add(debt);
        this.totalDebt += debt.getAmount();
        // Debt is also considered an expense
        addExpense(debt);
    }

    public void makeDebtPayment(Category payment) {
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount should be positive");
        }
        // Record as expense (money leaving your account)
        Category paymentExpense = new Category("Debt Payment: " + payment.getName(), -payment.getAmount());
        addExpense(paymentExpense);
        
        // Reduce the debt (since we're paying it down)
        this.totalDebt += payment.getAmount(); // Debt is negative, so adding positive reduces it
    }

    // ========== Savings Methods ==========
    public void addToSavings(Category savings) {
        if (savings.getAmount() <= 0) {
            throw new IllegalArgumentException("Savings amount must be positive");
        }
        this.savingsStreams.add(savings);
        this.totalSavings += savings.getAmount();
        // Savings come from income, so we need to record it as an expense (money leaving available funds)
        Category savingsExpense = new Category("Savings: " + savings.getName(), -savings.getAmount());
        addExpense(savingsExpense);
    }

    public void withdrawFromSavings(Category withdrawal) {
        if (withdrawal.getAmount() <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (withdrawal.getAmount() > this.totalSavings) {
            throw new IllegalArgumentException("Cannot withdraw more than available savings");
        }
        this.totalSavings -= withdrawal.getAmount();
        // Withdrawal becomes available as income
        Category withdrawalIncome = new Category("Savings Withdrawal: " + withdrawal.getName(), withdrawal.getAmount());
        addIncome(withdrawalIncome);
    }

    // ========== Getters ==========
    public String getName() { return this.name; }
    public double getIncome() { return this.totalIncome; }
    public double getExpense() { return this.totalExpense; }
    public double getDebt() { return this.totalDebt; }
    public double getSavings() { return this.totalSavings; }
    public double getBalance() { return this.totalIncome + this.totalExpense; }
    public Milestone getGoal() { return this.goal; }
    public ArrayList<Category> getIncomeStreams() { return this.incomeStreams; }
    public ArrayList<Category> getExpenseStreams() { return this.expenseStreams; }
    public ArrayList<Category> getDebtStreams() { return this.debtStreams; }
    public ArrayList<Category> getSavingsStreams() { return this.savingsStreams; }

    // ========== Setters ==========
    public void setName(String newName) { this.name = newName; }
    public void setGoal(Milestone newGoal) { this.goal = newGoal; }

    public double getAvailableFunds() {
        return this.totalIncome + this.totalExpense; // Expenses are negative
    }

    // ========== Bulk Operations ==========
    public void setIncomeStream(Category[] newIncomeStream) {
        this.incomeStreams.clear();
        this.totalIncome = 0.0;
        for (Category income : newIncomeStream) {
            addIncome(income);
        }
    }

    public void setExpenseStream(Category[] newExpenseStream) {
        this.expenseStreams.clear();
        this.totalExpense = 0.0;
        for (Category expense : newExpenseStream) {
            addExpense(expense);
        }
    }

    // Helper method to add any category (determines type based on amount)
    public void addCategory(Category newCat) {
        if (newCat.getAmount() > 0) {
            addIncome(newCat);
        } else {
            addExpense(newCat);
        }
    }
}
