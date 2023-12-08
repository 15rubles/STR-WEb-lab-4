package company.TestOfTables;

import company.Connection.JDBC;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

public class GetAllRows {

    public static void main(String[] args) {
        Statement stmt = null;
        try {
            JDBC.connect();

            stmt = JDBC.connection.createStatement();

            String exampleQuery1 = "SELECT * FROM Authors";
            System.out.println("\nAuthors:");
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next()) {
                int id = rs1.getInt("authorID");
                String firstName = rs1.getString("firstName");
                String lastName = rs1.getString("lastName");
                System.out.println(id + "\t" + firstName + "\t" + lastName);
            }

            String exampleQuery2 = "SELECT * FROM authorisbn";
            System.out.println("\nAuthorsISBN:");
            ResultSet rs2 = stmt.executeQuery(exampleQuery2);
            while (rs2.next()) {
                int id = rs2.getInt("authorID");
                String isbn = rs2.getString("isbn");
                System.out.println(id + "\t" + isbn);
            }

            String exampleQuery3 = "SELECT * FROM publishers";
            System.out.println("\nPublishers:");
            rs1 = stmt.executeQuery(exampleQuery3);
            while (rs1.next()) {
                int id = rs1.getInt("publisherID");
                String publisherName = rs1.getString("publisherName");
                System.out.println(id + "\t" + publisherName);
            }

            String exampleQuery4 = "SELECT * FROM titles";
            System.out.println("\nTitles:");
            rs1 = stmt.executeQuery(exampleQuery4);
            while (rs1.next()) {
                String isbn = rs1.getString("isbn");
                String title = rs1.getString("title");
                int editionNumber = rs1.getInt("editionNumber");
                String year = rs1.getString("year");
                int id = rs1.getInt("publisherID");
                BigDecimal price = rs1.getBigDecimal("price");
                System.out.println(isbn + "\t" + title + "\t" + editionNumber + "\t" + year + "\t" + id + "\t" + price);
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block, used to close resources
            JDBC.close();
        }
    }
}
