package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BibliotecaLibrary {

    private List<Book> books;
    private List<Movie> movies;

    public BibliotecaLibrary() {
        books = buildPreExistingBooks();
        movies = buildPreExistingMovies();
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

    public boolean checkOutBook(String bookName) {
        Optional<Book> findBook = books.stream()
                .filter(book -> !book.isCheckOut() && book.getName().equals(bookName))
                .findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckOut(true);
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookName) {
        Optional<Book> findBook = books.stream()
                .filter(book -> book.isCheckOut() && book.getName().equals(bookName))
                .findFirst();
        if (findBook.isPresent()) {
            findBook.get().setCheckOut(false);
            return true;
        }
        return false;
    }

    public boolean checkOutMovie(String movieName) {
        Optional<Movie> findMovie = movies.stream()
                .filter(movie -> !movie.isCheckOut() && movie.getName().equals(movieName))
                .findFirst();
        findMovie.ifPresent(Movie::checkOut);
        return findMovie.isPresent();
    }

    public boolean returnMovie(String movieName) {
        Optional<Movie> findMovie = movies.stream()
                .filter(movie -> movie.isCheckOut() && movie.getName().equals(movieName))
                .findFirst();
        findMovie.ifPresent(movie -> movie.setCheckOut(false));
        return findMovie.isPresent();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
