package csc450;

public class budget_driver {
    static String[] sucessfulIncomeCatNames = {"Monthly Wage", "Side Hustle"};
    static String[] sucessfulExpenseCatNames = {"Rent + utilities", "Gas", "Food", "Car Payment"};
    static String[] unSucessfulIncomeCatNames = {"Monthly Wage"};
    static double[] successfulIncomeCatAmounts = {4160.00, 400.00};
    static double[] successfulExpenseCatAmounts = {-1200.00, -150.00, -300.00, -400.00};
    
    public static boolean categoryCheck(String[] catNames, double[] catAmnts){
        //This is for making sure the name list
        if(catNames.length == catAmnts.length)
            return true;
        else
            return false;
    }
    public static Category[] categoryListMaker(String[] catNames, double[] catAmnts){
        int size = catNames.length;
        Category[] cats = new Category[size];
        int index = 0;
        while(index < size ){
            Category addCat = new Category(catNames[index], catAmnts[index]);
            cats[index] = addCat;
            index++;
        }
        return cats;
    }
    public static void main(String[] args){
        System.out.println("Testing for budget and category classes.");
        System.out.println("Test 1 - Making sure categories and amounts are equivalent.");
        System.out.println("Testing 2 correct lists - should print true.");
        System.out.println(categoryCheck(sucessfulIncomeCatNames, successfulIncomeCatAmounts));
        System.out.println("Testing 2 incorrect lists - should print false.");
        System.out.println(categoryCheck(unSucessfulIncomeCatNames, successfulIncomeCatAmounts));
        System.out.println("Test 2 - Creating the categories for the budgets.");
        Category[] expenseList = categoryListMaker(sucessfulExpenseCatNames, successfulExpenseCatAmounts);
        System.out.println("Printing Expense Categories");

        for(Category cat: expenseList){
            System.out.printf("%s: $%.2f\n", cat.getName(), cat.getAmount());
        }

        System.out.println("Printing Income Categories");
        Category[] incomeList = categoryListMaker(sucessfulIncomeCatNames, successfulIncomeCatAmounts);
        for(Category cat: incomeList){
            System.out.printf("%s: $%.2f\n", cat.getName(), cat.getAmount());
        }

        System.out.println("Test 3 - Creating the budget.");
        Budget budge = new Budget("Test Budget", incomeList, expenseList);
        System.out.printf("%s: %s\n", "Budget Name", budge.getName());
        System.out.printf("%s: $%.2f\n", "Budget Total Income", budge.getIncome());
        System.out.printf("%s: $%.2f\n", "Budget Total Expense", budge.getExpense());
        System.out.printf("%s: $%s\n", "Budget Balance", budge.getBalance());
        System.out.println("Testing done.");
    }

}
