package company.Query;

import company.Connection.JDBC;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static company.Connection.JDBC.connection;

public class Ex3 {
    public static void main(String[] args) {
        Statement stmt = null;
        try {
            JDBC.connect();
            Scanner in = new Scanner(System.in);
            stmt = connection.createStatement();
            String sql = "SELECT * FROM publishers";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("publisherID");
                String publisherName = resultSet.getString("publisherName");
                System.out.println(id + "\t" + publisherName);
            }
            System.out.println("Введите id издателя, которому надо изменить имя");
            int choose = in.nextInt();
            System.out.println("Введите новое имя: ");
            String temp = in.next();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publishers SET publisherName = ? WHERE publisherID = ?");
            preparedStatement.setString(1, temp);
            preparedStatement.setInt(2, choose);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}
