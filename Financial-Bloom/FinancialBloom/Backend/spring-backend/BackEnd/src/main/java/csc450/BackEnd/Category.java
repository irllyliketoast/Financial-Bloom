package csc450.BackEnd;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double amount;

    public Category() {}

    public Category(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return this.name; }

    public double getAmount() { return this.amount; }

    public void setName(String newName) { this.name = newName; }

    public void setAmount(double newAmount) { this.amount = newAmount; }

    @Override
    public String toString() {
        return String.format("%s: %f", getName(), getAmount());
    }
}
