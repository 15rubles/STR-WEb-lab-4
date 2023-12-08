package company.Query;

import company.Connection.JDBC;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static company.Connection.JDBC.connection;

public class Ex7 {
    public static void main(String[] args) {
        try {
            JDBC.connect();
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя издателя: ");
            String publisherName = in.next();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publishers (publisherName) VALUES (?)");
            preparedStatement.setString(1, publisherName);
            preparedStatement.executeUpdate();
            System.out.println("Издатель добавлен");

            String isbn = "0553418028";
            String firstName = "Ronald";
            String lastName = "Mackdonald";
            preparedStatement = connection.prepareStatement("INSERT INTO authorisbn (authorID, isbn) VALUES ((SELECT authorID FROM authors WHERE firstName = ? and lastName = ?), ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, isbn);
            preparedStatement.executeUpdate();
            System.out.println("добавлены данные в authorsISBN");

            System.out.println("Введите название книги");
            String title = in.next();
            System.out.println("Введите номер издания");
            int editionNumber = in.nextInt();
            System.out.println("Введите год издания");
            String year = in.next();
            System.out.println("Введите цену книги");
            BigDecimal price = in.nextBigDecimal();

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO titles (isbn, title, editionNumber, year, publisherID, price)  VALUES (?, ?, ?, ?,(SELECT publisherID FROM publishers WHERE publisherName = ?), ? )");
            preparedStatement1.setString(1, isbn);
            preparedStatement1.setString(2, title);
            preparedStatement1.setInt(3, editionNumber);
            preparedStatement1.setString(4, year);
            preparedStatement1.setString(5, publisherName);
            preparedStatement1.setBigDecimal(6, price);
            preparedStatement1.executeUpdate();
            System.out.println("книга добавлена");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}
