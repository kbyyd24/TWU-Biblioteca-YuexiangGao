package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phone;

    private List<Book> checkOutBooks;
    private List<Movie> checkOutMovies;

    public User(String libraryNumber, String password, String name, String email, String phone) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public boolean returnMovie(Movie movie) {
        return checkOutMovies.remove(movie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getLibraryNumber() != null ? !getLibraryNumber().equals(user.getLibraryNumber()) : user.getLibraryNumber() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getPhone() != null ? getPhone().equals(user.getPhone()) : user.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getLibraryNumber() != null ? getLibraryNumber().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
