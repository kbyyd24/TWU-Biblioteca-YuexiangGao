package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

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
}