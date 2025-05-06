/**
 * Author: Daniela Luna
 * Data Transfer Object for Milestone data sent from the frontend.
 * Purpose: This DTO is used to encapsulate and safely transfer user input when
 * saving new milestone entries via a POST request. It separates the raw
 * request payload from the database entity (Milestone), providing a
 * cleaner interface and validation layer.</p>
 *
 * Why separate from BudgetEntry:
 * Milestones represent long-term savings goals (e.g., "Vacation Fund"),
 * not recurring financial transactions. Keeping them distinct allows
 * separate logic, UI behavior, and database handling.</p>
 */

package csc450.BackEnd;

public class MilestoneDTO {
    private String name;
    private double amountSaved;
    private double goalAmount;
    private Integer userId;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountSaved() {
        return amountSaved;
    }

    public void setAmountSaved(double amountSaved) {
        this.amountSaved = amountSaved;
    }

    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
