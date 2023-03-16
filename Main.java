package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;


public class Main {
    private BookServer BookServer;
    private Scanner sc;
    private ViewBook ViewBook;

    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();

    }

@SneakyThrows
    private void run() {
    Properties props = new Properties();
    try (BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"))){
    props.load(reader);
        Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
        BookServer = new BookServer(connection);
        ViewBook  = new ViewBook();
    }

            sc = new Scanner(System.in);
        int m;

        while ((m = menu()) != 0) {
            switch (m) {
                case 1 -> {
                    showAll();
                }
                case 2 -> {
                    searchByAuthor();
                }
                case 3 -> {
                    searchByPublisher();
                }
                case 4 -> {
                    searchAfterYear();
                }
                case 5 -> {
                    sortAuthors();
                }
                case 6 -> {
                        publishersSort();
                }
                case 7 -> {
                    eachBookOfPublishers();
                }
            }
        }
    }

            private int menu () {
                System.out.println("""
                        1. Show All
                        2. Search by Author
                        3. Search by Publisher
                        4. Search after Year
                        5. Sort Authors
                        6. Sort Publishers
                        7. Search publishers' books
                        0. Exit
                        """
                );
                return Integer.parseInt(sc.nextLine());
            }
            private void showAll () {
                List<Book> books = BookServer.findList();
                ViewBook.showList(books);
            }
            private void searchByAuthor(){
        List<Book> books = BookServer.getBooksByAuthor(sc);
                ViewBook.showList(books);
        }
        private void searchByPublisher(){
        List<Book> books = BookServer.getBooksByPublisher(sc);
            ViewBook.showList(books);

        }
        private void searchAfterYear(){
        List<Book> books = BookServer.getBooksAfterYear(sc);
            ViewBook.showList(books);

        }
    private void sortAuthors(){
        List<String> books = BookServer.alphabetSort();
        ViewBook.showListt(books);

    }
private void publishersSort(){
        List<String> books = BookServer.publisherSort();
        ViewBook.showListt(books);
}
public void eachBookOfPublishers(){
    Map<String, List<Book>> books = BookServer.getBooksByPublisherMap();
    ViewBook.showListtt(books);

}
}
