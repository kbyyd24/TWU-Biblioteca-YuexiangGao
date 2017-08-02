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

    public boolean returnBook(Book book) {
        return checkOutBooks.remove(book);
    }

    public void checkOutMovie(Movie movie) {
        checkOutMovies.add(movie);
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

    public List<Movie> getCheckOutMovies() {
        return checkOutMovies;
    }

    public boolean returnMovie(Movie movie) {
        return checkOutMovies.remove(movie);
    }
}
