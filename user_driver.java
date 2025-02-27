package csc450;

public class user_driver {
    public static void main(String[] args){
        User test1 = new User(001, "Ryan", "Ballillionaire", "RyB@gmail.com", "RyBallday113","2/23/2025");
        System.out.println("This is the user information for: "+ test1.getFname() + ' ' + test1.getLname());
        System.out.println("The email of this user is: " + test1.getEmail());
        System.out.println("The date this account was created is: " + test1.getDatecreated());
        System.out.println("The password of this user is 'RyBallday113', but it does not have a get function!");
        System.out.println("We will now simulate a failed login using the email 'RuB@gmail.com' but the password 'RyBallday113.'");
        System.out.println("This should return and print false.");
        System.out.println(test1.login("RuB@gmail.com","RyBallday113"));
        System.out.println("Now to simulate another failed login using the correct email, 'RyB@gmail.com' but the incorrect password 'RyNallday113.'");
        System.out.println("This should also return and print false.");
        System.out.println(test1.login("RyB@gmail.com","RyNallday113"));
        System.out.println("Now to a successful login using the correct data - should return and print true.");
        System.out.println(test1.login("RyB@gmail.com","RyBallday113"));
        System.out.println("We will now change the email to 'RBG123@gmail.com'.");
        test1.setNewEmail("RBG123@gmail.com");
        System.out.println("Printing new email: " + test1.getEmail());
        System.out.println("Testing of creating a user, changing information and logging in complete.");



    }
}
