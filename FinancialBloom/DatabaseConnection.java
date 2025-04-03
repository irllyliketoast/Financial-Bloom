package csc450;
import java.sql.*;
// connection and statement have to be explicitly closed

public class JDBC {
    public static void main(String[] args) throws SQLException {
        String userName = "mmr4541@uncw.edu";
        String password = "Softwareengineering450";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Use this method to get a connection from anywhere in your app
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        try {
            // check the actual ip addr, what is the port num, and what is the db name?
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.01:3306/database_name",
                    userName, password);
            System.out.println("Connected to the database!");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
