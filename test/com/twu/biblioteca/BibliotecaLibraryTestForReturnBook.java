package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForReturnBook {

    private BibliotecaLibrary library;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
    }

    @Test
    public void should_set_book_isCheckOut_to_be_false_when_return_success() throws Exception {
        String bookName = "book1";
        library.checkOutBook(bookName);
        library.returnBook(bookName);
        assertFalse(library.getBooks().get(0).isCheckOut());
    }

    @Test
    public void return_book_should_be_failed_when_book_isCheckOut_is_false() throws Exception {
        assertFalse(library.returnBook("book1"));
    }

    @Test
    public void should_return_true_when_return_book_success() throws Exception {
        String bookName = "book1";
        library.checkOutBook(bookName);
        assertTrue(library.returnBook(bookName));
    }

    @Test
    public void should_return_false_when_return_book_failed() throws Exception {
        assertFalse(library.returnBook("bookName"));
    }
}