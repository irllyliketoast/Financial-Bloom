package csc450;
import java.sql.*;
// connection and statement have to be explicitly closed

public class DatabaseConnection {
    private static final String USERNAME = "mmr4541@uncw.edu"; // your MySQL user
    private static final String PASSWORD = "Softwareengineering450"; // your MySQL password
    private static final String URL = "jdbc:mysql://192.168.1.25:3306/database_name"; // update IP and DB name

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // load JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Use this method to get a connection from anywhere in your app
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Optional: You can still run this class standalone to test DB connection
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}
