/*
Code and Description Author: Temo Galindo
This code is the driver file for the Milestone class. Shows off many of the classes functionality such as multiple constructors for default values,
correct implementation of setters and getters, and the right rollout of mehtods like balance and calculateRemaining.

Update Log
Created on 4.14.2025 by Temo Galindo
*/
package csc450;

public class milestone_driver {
    static Milestone milestone1; //Instatiating a milestone with no amount paid/monthly contribution.
    static Milestone milestone2 = new Milestone("Concert Weekend", 650.00, 283.05, 102.47); //Instantiating a milestone with those added.

    public static void main(String[] args) {
        System.out.println("Testing for milestone class.");
        String name = null;
        Double value = 4500.00;
        if(name != null && value != null){
            milestone1 = new Milestone(name, value);
        }
        else{
            System.out.println("Error - please make sure to at least include a name and total amount for milestones.");
            name = "Car Loan";
            milestone1 = new Milestone(name, value);
        }
        Milestone[] milestones = {milestone1, milestone2};
        System.out.println("Test 1 - Instantiating with the overloaded constructors.");
        System.out.printf("%s:\n Name: %s\n Total Amount: $%.2f\n Amount Paid: $%.2f\n Monthly Contribution: $%.2f\n","Milestone 1 - no amount paid/monthly contribution", milestone1.getName(), milestone1.getAmount(), milestone1.getAmountPaid(), milestone1.getContribution());
        System.out.printf("%s:\n Name: %s\n Total Amount: $%.2f\n Amount Paid: $%.2f\n Monthly Contribution: $%.2f\n","Milestone 2 - Amount paid and monthly contribution set", milestone2.getName(), milestone2.getAmount(), milestone2.getAmountPaid(), milestone2.getContribution());
        System.out.println("Test 2 - Balance Method: Total amount - Amount Paid");
        for(Milestone mile: milestones){
            System.out.printf("%s: %.2f\n",mile.getName(),mile.getBalance());
        }
        System.out.println("Test 3 - Setting new amount paid and contribution.");
        System.out.printf("%s:\n Name: %s\n Total Amount: $%.2f\n Amount Paid: $%.2f\n Monthly Contribution: $%.2f\n","No amount paid/monthly contribution", milestone1.getName(), milestone1.getAmount(), milestone1.getAmountPaid(), milestone1.getContribution());
        milestone1.setAmountPaid(1248.97);
        milestone1.setContribution(378.56);
        System.out.printf("%s:\n Name: %s\n Total Amount: $%.2f\n Amount Paid: $%.2f\n Monthly Contribution: $%.2f\n","Newly set amount paid/monthly contribution", milestone1.getName(), milestone1.getAmount(), milestone1.getAmountPaid(), milestone1.getContribution());
        System.out.println("Test 4 - Adding amount paid using monthly contribution.");
        System.out.printf("Name: %s\n Total Amount: $%.2f\n Monthly Contribution: $%.2f\n Amount Paid: $%.2f\n Balance: %.2f\n", milestone2.getName(), milestone2.getAmount(), milestone2.getContribution(), milestone2.getAmountPaid(),milestone2.getBalance());
        milestone2.addContribution();
        System.out.printf("Name: %s\n Total Amount: $%.2f\n Monthly Contribution: $%.2f\n Amount Paid: $%.2f\n Balance: %.2f\n", milestone2.getName(), milestone2.getAmount(), milestone2.getContribution(), milestone2.getAmountPaid(),milestone2.getBalance());
        System.out.println("Test 5 - Adding amount paid using any amount contribution.");
        System.out.printf("Name: %s\n Total Amount: $%.2f\n Amount Paid: $%.2f\n Balance: $%.2f\n", milestone1.getName(), milestone1.getAmount(), milestone1.getAmountPaid(), milestone1.getBalance());
        System.out.println("---Adding $2000 to amount paid.---");
        milestone1.addAmounPaid(2000.00);
        System.out.printf("Name: %s\n Total Amount: $%.2f\n Amount Paid: $%.2f\n Balance: $%.2f\n", milestone1.getName(), milestone1.getAmount(), milestone1.getAmountPaid(), milestone1.getBalance());
        System.out.println("Test 6 - Using Calculate Remaining Months method.");
        for(Milestone mile: milestones){
            System.out.printf("Name: %s\n Total Amount: %.2f\n Amount Paid: %.2f\n Monthly Contribution: %.2f\n Months Until Milestone is Reached: %d\n", mile.getName(), mile.getAmount(), mile.getAmountPaid(), mile.getContribution(), mile.calculateRemaining());
        }
        System.out.println("--Testing Complete--");
        

        
        
    }
}
