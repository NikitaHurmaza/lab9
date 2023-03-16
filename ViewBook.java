package org.example;

import java.util.List;
import java.util.Map;


public class ViewBook {
    public static void showList(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        System.out.println("--------------------");
    }
    public static void showListt(List<String> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        System.out.println("--------------------");
    }
    public static void showListtt (Map<String, List<Book>> books){
        for (Map.Entry entry : books.entrySet()) {
            System.out.println(entry.getKey()+" - "+ entry.getValue());
        }
    }
}
