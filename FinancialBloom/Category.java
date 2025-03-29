package csc450;

public class Category {
    private String name;
    private double amount;
    public Category(String nName, double nAmount){
        this.name = nName;
        this.amount = nAmount;
    }
    public String getName() {return this.name;}
    public double getAmount() {return this.amount;}
    public void setName(String newName) {this.name = newName;}
    public void setAmount(float newAmount) {this.amount = newAmount;}
    @Override
    public String toString() {
        return String.format("%s: %f", getName(), getAmount());
    }
}
