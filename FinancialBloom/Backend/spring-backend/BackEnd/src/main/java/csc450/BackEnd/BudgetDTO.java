/*
* Data Transfer Object Class
* Purpose: this class carries data from the frontend to the backend(controller)
* in order to prevent exposing the entire entity (Budget) directly.
* Instead of binding raw JSON to the Budget entity directly (which could expose or
* overwrite sensitive fields like id, userId, etc.),it binds to a BudgetDTO.
* */

package csc450.BackEnd;

import java.util.List;

public class BudgetDTO {
    private String name;
    private List<Category> incomeStreams;
    private List<Category> expenseStreams;

    public BudgetDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Category> getIncomeStreams() { return incomeStreams; }
    public void setIncomeStreams(List<Category> incomeStreams) { this.incomeStreams = incomeStreams; }

    public List<Category> getExpenseStreams() { return expenseStreams; }
    public void setExpenseStreams(List<Category> expenseStreams) { this.expenseStreams = expenseStreams; }
}
