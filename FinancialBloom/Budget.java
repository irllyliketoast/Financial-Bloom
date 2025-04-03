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
