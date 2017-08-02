package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("000-0001", "password");
    }

    @Test
    public void should_save_book_when_check_out_a_book() throws Exception {
        Book book = new Book("bookName", "Author", 2016);
        user.checkOutBook(book);
        assertEquals(book, user.getCheckOutBooks().get(0));
    }

    @Test
    public void should_return_true_when_return_book_success() throws Exception {
        Book book = new Book("bookName", "author", 2016);
        user.checkOutBook(book);
        assertTrue(user.returnBook(book));
    }

    @Test
    public void should_remove_book_from_checkOutBooks_list_when_return_book_success() throws Exception {
        Book book = new Book("bookName", "author", 2016);
        user.checkOutBook(book);
        user.returnBook(book);
        assertEquals(emptyList(), user.getCheckOutBooks());
    }

    @Test
    public void should_return_false_when_return_book_fail() throws Exception {
        Book book = new Book("bookName", "author", 2016);
        assertFalse(user.returnBook(book));
    }

    @Test
    public void should_save_movie_when_check_out_a_movie() throws Exception {
        Movie movie = new Movie("movieName", 2014, "director", 8);
        user.checkOutMovie(movie);
        assertEquals(movie, user.getCheckOutMovies().get(0));
    }
}