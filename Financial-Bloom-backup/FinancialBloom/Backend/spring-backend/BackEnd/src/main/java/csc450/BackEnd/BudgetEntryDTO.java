/*
 * Author: Daniela Luna
 *
 * Description:
 * The BudgetEntryDTO class serves as a data transfer object for transferring
 * budget entry data between the frontend and backend layers.
 * It allows the client to send new entries without exposing sensitive or
 * unnecessary fields from the actual BudgetEntry entity (like User object or ID).
 *
 * Purpose:
 * Ensures clean and secure separation of concerns when handling HTTP requests.
 * The controller uses this DTO to parse JSON and create BudgetEntry objects.
 *
 * Fields:
 * - type: Type of entry ("Income", "Expense", etc.)
 * - name: Label for the transaction
 * - amount: Dollar amount of the transaction
 * - userId: ID of the user creating the entry (linked via FK)
 */

package csc450.BackEnd;

import java.math.BigDecimal;

public class BudgetEntryDTO {
    private String type;
    private String name;
    private BigDecimal amount;
    private Integer userId;

    public BudgetEntryDTO() {}

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
