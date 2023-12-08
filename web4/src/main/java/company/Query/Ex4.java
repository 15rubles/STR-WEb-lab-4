package company.Query;

import company.Connection.JDBC;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static company.Connection.JDBC.connection;

public class Ex4 {
    public static void main(String[] args) {
        Statement stmt = null;
        try {
            JDBC.connect();
            Scanner in = new Scanner(System.in);
            stmt = connection.createStatement();

            System.out.println("Введите id издателя");
            int choose = in.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM titles WHERE publisherID = ? ORDER BY title");
            preparedStatement.setInt(1, choose);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                int editionNumber = resultSet.getInt("editionNumber");
                String year = resultSet.getString("year");
                int id = resultSet.getInt("publisherID");
                BigDecimal price = resultSet.getBigDecimal("price");
                System.out.println(isbn + "\t" + title + "\t" + editionNumber + "\t" + year + "\t" + id + "\t" + price);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}
