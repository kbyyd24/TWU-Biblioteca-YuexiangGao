package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("000-0001", "password", "name", "email", "phone");
    }

    private Book buildBook() {
        return new Book("bookName", "author", 2016);
    }

    @Test
    public void should_save_book_when_check_out_a_book() throws Exception {
        Book book = buildBook();
        user.checkOutBook(book);
        assertEquals(book, user.getCheckOutBooks().get(0));
    }

    @Test
    public void should_return_true_when_return_book_success() throws Exception {
        Book book = buildBook();
        user.checkOutBook(book);
        assertTrue(user.returnBook(book));
    }

    @Test
    public void should_remove_book_from_checkOutBooks_list_when_return_book_success() throws Exception {
        Book book = buildBook();
        user.checkOutBook(book);
        user.returnBook(book);
        assertEquals(emptyList(), user.getCheckOutBooks());
    }

    @Test
    public void should_return_false_when_return_book_fail() throws Exception {
        Book book = buildBook();
        assertFalse(user.returnBook(book));
    }

    private Movie buildMovie() {
        return new Movie("movieName", 2014, "director", 8);
    }

    @Test
    public void should_save_movie_when_check_out_a_movie() throws Exception {
        Movie movie = buildMovie();
        user.checkOutMovie(movie);
        assertEquals(movie, user.getCheckOutMovies().get(0));
    }

    @Test
    public void should_return_true_when_return_movie_success() throws Exception {
        Movie movie = buildMovie();
        user.checkOutMovie(movie);
        assertTrue(user.returnMovie(movie));
    }

    @Test
    public void should_remove_movie_from_checkOutMovies_list_when_return_movie_success() throws Exception {
        Movie movie = buildMovie();
        user.checkOutMovie(movie);
        user.returnMovie(movie);
        assertEquals(emptyList(), user.getCheckOutMovies());
    }

    @Test
    public void should_return_false_when_return_movie_fail() throws Exception {
        Movie movie = buildMovie();
        assertFalse(user.returnMovie(movie));
    }

    @Test
    public void should_return_user_info_string_when_load_user_info() throws Exception {
        String infoStr = String.format("%s, %s, %s", user.getName(), user.getEmail(), user.getPhone());
        assertEquals(infoStr, user.loadInfo());
    }
}