package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForReturnBook {

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
    public void should_set_book_isCheckOut_to_be_false_when_return_success() throws Exception {
        library.login(number, password);
        String bookName = "book1";
        library.checkOutBook(bookName);
        library.returnBook(bookName);
        assertFalse(library.getBooks().get(0).isCheckOut());
    }

    @Test
    public void return_book_should_be_failed_when_book_isCheckOut_is_false() throws Exception {
        library.login(number, password);
        assertFalse(library.returnBook("book1"));
    }

    @Test
    public void should_return_true_when_return_book_success() throws Exception {
        library.login(number, password);
        String bookName = "book1";
        library.checkOutBook(bookName);
        assertTrue(library.returnBook(bookName));
    }

    @Test
    public void should_return_false_when_return_book_failed() throws Exception {
        library.login(number, password);
        assertFalse(library.returnBook("bookName"));
    }

    @Test
    public void should_return_false_when_book_check_out_by_others() throws Exception {
        library.login(number, password);
        String bookName = "book1";
        library.checkOutBook(bookName);
        library.getLoginUser().getCheckOutBooks().remove(0);
        assertFalse(library.returnBook(bookName));
    }

    @Test
    public void should_return_false_when_login_user_is_null() throws Exception {
        assertFalse(library.returnBook("book1"));
    }
}