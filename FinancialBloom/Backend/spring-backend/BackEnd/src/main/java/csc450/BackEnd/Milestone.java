/**
 * Authors: Temo Galindo (java class), Daniela Luna (Spring Boot conversion)
 * Entity representing a user-defined savings milestone.
 * Purpose: A Milestone tracks a long-term financial goal, including the amount
 * a user has saved toward that goal and the target amount they aim to reach.
 * Each milestone is linked to a specific user.
 * This model supports the visualization of user progress toward goals
 * on the dashboard, helping users stay motivated by seeing their
 * achievements grow over time.
 * Milestones track gradual savings toward specific, named objectives.
 * Keeping this data in its own entity allows for more focused business logic,
 * cleaner UI interactions, and better database normalization.
 */

package csc450.BackEnd;

import jakarta.persistence.*;

@Entity
@Table(name = "milestone")
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double amountSaved;

    private double goalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Milestone() {}

    public Milestone(String name, double amountSaved, double goalAmount, User user) {
        this.name = name;
        this.amountSaved = amountSaved;
        this.goalAmount = goalAmount;
        this.user = user;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
