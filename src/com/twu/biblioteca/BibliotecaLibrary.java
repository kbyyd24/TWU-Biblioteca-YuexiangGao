package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BibliotecaLibrary {

    private List<Book> books;
    private List<Movie> movies;
    private List<User> users;
    private User loginUser;

    public BibliotecaLibrary() {
        books = buildPreExistingBooks();
        movies = buildPreExistingMovies();
        users = predifiendUsers();
    }

    private List<Book> buildPreExistingBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("book1", "author1", 1998));
        books.add(new Book("book2", "author2", 2016));
        books.add(new Book("book3", "author3", 2014));
        return books;
    }

    private List<Movie> buildPreExistingMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("movie1", 2006, "director1", 8));
        movies.add(new Movie("movie2", 1980, "director2", 9));
        movies.add(new Movie("movie3", 2015, "director3", 2));
        return movies;
    }

    private List<User> predifiendUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("000-0001", "p1"));
        users.add(new User("000-0002", "p2"));
        users.add(new User("000-0003", "p3"));
        return users;
    }

    public boolean checkOutBook(String bookName) {
        if (loginUser == null) {
            return false;
        }
        Optional<Book> findBook = books.stream()
                .filter(book -> !book.isCheckOut() && book.getName().equals(bookName))
                .findFirst();
        findBook.ifPresent(book -> {
            book.setCheckOut(true);
            loginUser.checkOutBook(book);
        });
        return findBook.isPresent();
    }

    public boolean returnBook(String bookName) {
        if (loginUser == null) {
            return false;
        }
        Optional<Book> findBook = books.stream()
                .filter(book -> book.isCheckOut() && book.getName().equals(bookName))
                .findFirst();
        if (findBook.isPresent()) {
            Book book = findBook.get();
            if (loginUser.returnBook(book)) {
                book.setCheckOut(false);
                return true;
            }
        }
        return false;
    }

    public boolean checkOutMovie(String movieName) {
        if (loginUser == null) {
            return false;
        }
        Optional<Movie> findMovie = movies.stream()
                .filter(movie -> !movie.isCheckOut() && movie.getName().equals(movieName))
                .findFirst();
        findMovie.ifPresent(movie -> {
            loginUser.checkOutMovie(movie);
            movie.checkOut();
        });
        return findMovie.isPresent();
    }

    public boolean returnMovie(String movieName) {
        if (loginUser == null) {
            return false;
        }
        Optional<Movie> findMovie = movies.stream()
                .filter(movie -> movie.isCheckOut() && movie.getName().equals(movieName))
                .findFirst();
        if (findMovie.isPresent()) {
            Movie movie = findMovie.get();
            if (loginUser.returnMovie(movie)) {
                movie.setCheckOut(false);
                return true;
            }
        }
        return false;
    }

    public boolean login(String libraryNumber, String password) {
        Optional<User> findUser = users.stream()
                .filter(user -> user.getLibraryNumber().equals(libraryNumber))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
        findUser.ifPresent(user -> loginUser = user);
        return findUser.isPresent();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
