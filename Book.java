package org.example;

import java.util.Objects;

    public class Book {
        protected int id;

        protected String bookName;
        protected String author;
        protected String publisher;
        protected int yearOfPublishing;
        protected int numberOfPages;
        protected double price;

        public Book(int id, String bookName, String author, String publisher, int yearOfPublishing, int numberOfPages, double price) {
            this.id = id;
            this.bookName = bookName;
            this.author = author;
            this.publisher = publisher;
            this.yearOfPublishing = yearOfPublishing;
            this.numberOfPages = numberOfPages;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Book " +
                    "id "+ id +
                    " bookName='" + bookName + '\'' +
                    ", author='" + author + '\'' +
                    ", publisher='" + publisher + '\'' +
                    ", yearOfPublishing=" + yearOfPublishing +
                    ", numberOfPages=" + numberOfPages +
                    ", price=" + price;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return id == book.id && yearOfPublishing == book.yearOfPublishing && numberOfPages == book.numberOfPages && Double.compare(book.price, price) == 0 && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id ,bookName, author, publisher, yearOfPublishing, numberOfPages, price);
        }

    }

