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

package csc450.BackEnd;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BudgetID")
    private Long id;

    @Column(nullable = false)
    private String name;

    private double totalIncome;
    private double totalExpense;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id") // for unidirectional mapping
    private List<Category> incomeStreams = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id") // for unidirectional mapping
    private List<Category> expenseStreams = new ArrayList<>();

    public Budget() {}

    public Budget(String name, List<Category> incomes, List<Category> expenses) {
        this.name = name;
        this.incomeStreams = incomes;
        this.expenseStreams = expenses;
        calculateTotals();
    }

    private void calculateTotals() {
        this.totalIncome = incomeStreams.stream().mapToDouble(Category::getAmount).sum();
        this.totalExpense = expenseStreams.stream().mapToDouble(Category::getAmount).sum();
    }

    public String getName() { return this.name; }
    public double getIncome() { return this.totalIncome; }
    public double getExpense() { return this.totalExpense; }
    public double getBalance() { return this.totalIncome - this.totalExpense; }

    public void setName(String newName) { this.name = newName; }

    public void setIncomeStream(List<Category> newIncomeStream) {
        this.incomeStreams.clear();
        this.incomeStreams.addAll(newIncomeStream);
        this.totalIncome = incomeStreams.stream().mapToDouble(Category::getAmount).sum();
    }

    public void setExpenseStream(List<Category> newExpenseStream) {
        this.expenseStreams.clear();
        this.expenseStreams.addAll(newExpenseStream);
        this.totalExpense = expenseStreams.stream().mapToDouble(Category::getAmount).sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }


    public void addCategory(Category newCat) {
        if (newCat.getAmount() > 0) {
            this.incomeStreams.add(newCat);
            this.totalIncome += newCat.getAmount();
        } else {
            this.expenseStreams.add(newCat);
            this.totalExpense += newCat.getAmount();
        }
    }

    public List<Category> getIncomeStreams() { return this.incomeStreams; }
    public List<Category> getExpenseStreams() { return this.expenseStreams; }
}
