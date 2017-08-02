package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String libraryNumber;
    private String password;

    private List<Book> checkOutBooks;
    private List<Movie> checkOutMovies;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        checkOutBooks = new ArrayList<>();
        checkOutMovies = new ArrayList<>();
    }

    public void checkOutBook(Book book) {
        checkOutBooks.add(book);
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getCheckOutBooks() {
        return checkOutBooks;
    }
}
