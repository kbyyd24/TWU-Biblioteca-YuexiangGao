package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForCheckOutBook {

    private BibliotecaLibrary library;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
    }

    @Test
    public void should_set_book_isCheckOut_to_be_true_when_check_out_success() throws Exception {
        library.checkOutBook("book1");
        assertTrue(library.getBooks().get(0).isCheckOut());
    }

    @Test
    public void should_check_out_failed_when_book_isCheckOut_is_true() throws Exception {
        library.getBooks().get(0).setCheckOut(true);
        assertFalse(library.checkOutBook("book1"));
    }

    @Test
    public void should_return_true_when_check_out_success() throws Exception {
        assertTrue(library.checkOutBook("book1"));
    }

    @Test
    public void should_return_false_when_check_out_failed() throws Exception {
        assertFalse(library.checkOutBook("bookName"));
    }
}