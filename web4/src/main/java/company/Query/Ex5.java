package company.Query;

import company.Connection.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static company.Connection.JDBC.connection;

public class Ex5 {
    public static void main(String[] args) {
        try {
            JDBC.connect();
            String firstName = "Fedor";
            String lastName = "Dostoevskiy";
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO authors (firstName, lastName) VALUES  (?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate();
            System.out.println("Добавлен новый автор");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}
