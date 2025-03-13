package csc450;

public class User {
    private final int userid; // This will never be changed
    private String fname; // This will be malleable
    private String lname; // This will be malleable
    private String email; // This will be malleable
    private String password; // Will be a hash of the password in the final implementation
    private final String datecreated; //This will also never change
    public User(int userid, String fname, String lname, String email, String password, String datecreated){
        this.userid = userid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.datecreated = datecreated;
    }
    public int getUserid() {return this.userid;}
    public String getFname() {return this.fname;}
    public String getLname() {return this.lname;}
    public String getEmail() {return this.email;}
    public String getDatecreated() {return this.datecreated;}
    public void setNewFname(String newFname){this.fname = newFname;}

    public void setNewLname(String newLname){this.lname = newLname;}

    public void setNewEmail(String newEmail){this.email = newEmail;}

    public boolean login(String emailAttempt, String passAttempt){
        if(emailAttempt.equals(this.email) && passAttempt.equals(this.password)){return true;}
        else{return false;}
    }

}
