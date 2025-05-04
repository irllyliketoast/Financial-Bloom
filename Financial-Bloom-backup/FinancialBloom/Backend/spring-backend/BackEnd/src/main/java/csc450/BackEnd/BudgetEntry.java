/*
 * Author: Daniela Luna
 *
 * Description:
 * The BudgetEntry class represents an individual budget-related entry
 * (such as an income, expense, debt, or savings item) in a user's financial log.
 * Each entry is linked to a specific user, and captures the transaction type,
 * descriptive name, amount, and timestamp for later reporting or visualizations.
 *
 * Purpose:
 * This class enables fine-grained tracking of financial transactions and is
 * intended for storing dynamic, persistent budget data in the database.
 * It integrates with JPA and Spring Boot for full-stack interaction.
 *
 * Key Fields:
 * - user: Linked User entity (foreign key)
 * - type: Type of entry ("Income", "Expense", "Debt", "Savings")
 * - name: Name/label of the transaction (e.g., "Scholarship", "Groceries")
 * - amount: The amount of money associated with the entry
 * - createdAt: Timestamp of when the entry was added
 */

package csc450.BackEnd;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget_entries")
public class BudgetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String type; // e.g., "Income", "Expense", "Debt", "Savings"

    private String name;

    private BigDecimal amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    public BudgetEntry() {}

    // Getters and Setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
