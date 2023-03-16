package org.example;

import lombok.AllArgsConstructor;
import java.sql.Date;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@AllArgsConstructor
public class BookServer {
    private Connection connection;

    public List<Book> findList() {
        ArrayList<Book> book = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from book")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int yearOfPublication = rs.getInt("yearOfPublication");
                int numberOfPages = rs.getInt("numberOfPages");
                double price = rs.getDouble("price");
                book.add(new Book(id, bookName, author, publisher, yearOfPublication, numberOfPages, price));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    // Список книг заданого автора в порядку зростання року видання
    public List<Book> getBooksByAuthor(Scanner sc) {
        System.out.println("Please, enter author:");

        String authorSearch = sc.nextLine();
        ArrayList<Book> book = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from book where author = ?")) {
            ps.setString(1, authorSearch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int yearOfPublication = rs.getInt("yearOfPublication");
                int numberOfPages = rs.getInt("numberOfPages");
                double price = rs.getDouble("price");
                book.add(new Book(id, bookName, author, publisher, yearOfPublication, numberOfPages, price));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    //Cписок книг, що видані заданим видавництвом
    public List<Book> getBooksByPublisher(Scanner sc) {
        System.out.println("Please, enter publisher:");

        String publisherSearch = sc.nextLine();
        ArrayList<Book> book = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from book where publisher = ?")) {
            ps.setString(1, publisherSearch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int yearOfPublication = rs.getInt("yearOfPublication");
                int numberOfPages = rs.getInt("numberOfPages");
                double price = rs.getDouble("price");
                book.add(new Book(id, bookName, author, publisher, yearOfPublication, numberOfPages, price));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    //список книг, що випущені після заданого року
    public List<Book> getBooksAfterYear(Scanner sc) {
        System.out.println("Please, enter year:");

        String year = sc.nextLine();
        ArrayList<Book> book = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from book where yearOfPublication > ?")) {
            ps.setString(1, String.valueOf(year));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int yearOfPublication = rs.getInt("yearOfPublication");
                int numberOfPages = rs.getInt("numberOfPages");
                double price = rs.getDouble("price");
                book.add(new Book(id, bookName, author, publisher, yearOfPublication, numberOfPages, price));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    // Список авторів в алфавітному порядку
    public List<String> alphabetSort() {
        ArrayList<String> books = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select distinct author from book order by author asc ")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String author = rs.getString("author");
                books.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    //список видавництв, книги яких зареєстровані в системі без повторів
    public ArrayList<String> publisherSort() {
        ArrayList<String> books = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select distinct publisher from book")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String publisher = rs.getString("publisher");
                books.add(publisher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    //для кожного видавництва визначити список книг, виданих ним
    public Map<String, List<Book>> getBooksByPublisherMap() {
        Map<String, List<Book>> books = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, bookName, author, publisher, yearOfPublication, numberOfPages, price FROM book ")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int yearOfPublication = rs.getInt("yearOfPublication");
                int numberOfPages = rs.getInt("numberOfPages");
                double price = rs.getDouble("price");
                Book book = new Book(id, bookName, author, publisher, yearOfPublication, numberOfPages, price);
                List<Book> publisherBooks = books.getOrDefault(publisher, new ArrayList<>());
                publisherBooks.add(book);
                books.put(publisher, publisherBooks);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
