/* Code and Description Author: Temo Galindo
Purpose of Code
Allows user to create Milestones, which are an extenstion of the category class. The main reson for this is to
not only track how much they cost in total but to also track how much has been paid off towards them as well.

Key Componets
name: name of the mielstone.
amount: total amount that it costs.
amount paid: how much of the milestone has been paid off.
monthly cont: short for monthly contribution, as in how much per month is benig put towards the milestone.
Methods of Note:
getBalance(): returns total amount - amount paid, so shows how much is left on the milestone
calculateRemaining(): calculates how many more months are needed until the total amount is reached
based off of the monthly contribution. Assumes both monthlyCont is set and amount paid has not yet
met total amount.
Update Log:
Created and updated on 4.14.2025 by Temo Galindo
*/
package csc450;

public class Milestone extends Category{
    private double amountPaid;
    private double monthlyCont;
    public Milestone(String nName, double nAmount){
        super(nName, nAmount);
        this.amountPaid = 0.0;
        this.monthlyCont = 0.0;
    }
    public Milestone(String nName, double nAmount, double nAmountPaid, double nMonthlyCont){
        super(nName, nAmount);
        this.amountPaid = nAmountPaid;
        this.monthlyCont = nMonthlyCont;
    }
    public double getAmountPaid(){return this.amountPaid;}
    public void setAmountPaid(double newPaid){this.amountPaid = newPaid;}
    public void addAmounPaid(double addPay){this.amountPaid += addPay;}
    public double getBalance(){return super.getAmount()-this.amountPaid;}
    public double getContribution(){return this.monthlyCont;}
    public void setContribution(double contribution){this.monthlyCont = contribution;}
    public void addContribution(){this.amountPaid += this.monthlyCont;}
    public int calculateRemaining(){
        //assume monthlyCont is set and amount paid is not yet > milestone amount
        double remaining = (super.getAmount() - this.amountPaid)/this.monthlyCont;
        return (int) Math.ceil(remaining);
    }
}
