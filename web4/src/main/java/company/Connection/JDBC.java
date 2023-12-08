package company.Connection;

import java.sql.*;

public class JDBC {
    public static Connection connection = null;

    public static void connect() throws SQLException {
        String url = "jdbc:mysql://localhost/books3";
        connection = DriverManager.getConnection(url, "root", "qwerty");
        if (connection == null) {
            throw new SQLException();
        } else {
            System.out.println("connected");
        }
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }
}
