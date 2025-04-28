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
Updated on 4.27.2025 by Temo Galindo - added "Complete" instance variable, which returns if the milestone has been paid for or not.
Created and updated on 4.14.2025 by Temo Galindo.
*/
package csc450;

public class Milestone extends Category{
    private double amountPaid; //amount paid on milestone so far
    private double monthlyCont; //monthly contribution to milestone
    private boolean complete; //shows whether this milestone is complete
    public Milestone(String nName, double nAmount){ //constructor for if you just have a name and total for the milestone
        super(nName, nAmount);
        this.amountPaid = 0.0;
        this.monthlyCont = 0.0;
        this.complete = false;
    }
    public Milestone(String nName, double nAmount, double nAmountPaid, double nMonthlyCont){ //constructor if you've already put money into the milestone
        super(nName, nAmount);
        this.amountPaid = nAmountPaid;
        this.monthlyCont = nMonthlyCont;
        if(amountPaid >= nAmount)
            this.complete = true;
        else
            this.complete = false;
    }
    public double getAmountPaid(){return this.amountPaid;}
    public double getBalance(){return super.getAmount()-this.amountPaid;}
    public double getContribution(){return this.monthlyCont;}
    public boolean isComplete(){return this.complete;}
    public void setAmountPaid(double newPaid){
        this.amountPaid = newPaid;
        this.updateCompletion();
    }
    public void addAmountPaid(double addPay){
        this.amountPaid += addPay;
        this.updateCompletion();
    }
    public void setContribution(double contribution){this.monthlyCont = contribution;}
    public void addContribution(){
        this.amountPaid += this.monthlyCont;
        this.updateCompletion();
    }
    public int calculateRemaining(){
        //assume monthlyCont is set and amount paid is not yet > milestone amount
        double remaining = (super.getAmount() - this.amountPaid)/this.monthlyCont;
        return (int) Math.ceil(remaining);
    }
    private void updateCompletion(){ // private function which is used in the changing of amount paid to always calculate status
        this.complete = this.amountPaid >= super.getAmount();
    }
}
