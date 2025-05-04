/**
 * Author: Daniela Luna
 * Combines multiple data sources (budget entries + milestones) into a
 * single structured response for the dashboard frontend.
 * Purpose: Used by the DashboardController to deliver all relevant user data
 * needed to render charts, stats, and milestone progress in one API call.</p>
 *
 * Why separate from BudgetEntry:
 * Dashboard needs more than just budget data—it needs both budget entries
 * and milestone savings to calculate savings percentages. This DTO
 * combines those two models into one response tailored for the frontend.</p>
 */

package csc450.BackEnd;

import java.util.List;

public class DashboardDataDTO {
    private List<BudgetEntry> budgetData;
    private List<Milestone> milestone;

    public DashboardDataDTO(List<BudgetEntry> budgetData, List<Milestone> milestone) {
        this.budgetData = budgetData;
        this.milestone = milestone;
    }

    public List<BudgetEntry> getBudgetData() {
        return budgetData;
    }

    public void setBudgetData(List<BudgetEntry> budgetData) {
        this.budgetData = budgetData;
    }

    public List<Milestone> getMilestone() {
        return milestone;
    }

    public void setMilestones(List<Milestone> milestone) {
        this.milestone = milestone;
    }
}
