package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForCheckOutBook {

    private BibliotecaLibrary library;
    private String number;
    private String password;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        number = "000-0001";
        password = "p1";
    }

    @Test
    public void should_set_book_isCheckOut_to_be_true_when_check_out_success() throws Exception {
        library.login(number, password);
        library.checkOutBook("book1");
        assertTrue(library.getBooks().get(0).isCheckOut());
    }

    @Test
    public void should_check_out_failed_when_book_isCheckOut_is_true() throws Exception {
        library.login(number, password);
        library.getBooks().get(0).setCheckOut(true);
        assertFalse(library.checkOutBook("book1"));
    }

    @Test
    public void should_return_true_when_check_out_success() throws Exception {
        library.login(number, password);
        assertTrue(library.checkOutBook("book1"));
    }

    @Test
    public void should_return_false_when_check_out_failed() throws Exception {
        library.login(number, password);
        assertFalse(library.checkOutBook("bookName"));
    }

    @Test
    public void should_do_nothing_and_return_false_when_login_user_is_null() throws Exception {
        assertFalse(library.checkOutBook("book1"));
        assertFalse(library.getBooks().get(0).isCheckOut());
    }
}