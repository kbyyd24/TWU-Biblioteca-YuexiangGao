package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.CommandResult;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.config.OptionResultMessage.CHECK_OUT_BOOK_FAIL;
import static com.twu.biblioteca.config.OptionResultMessage.CHECK_OUT_BOOK_SUCCESS;
import static com.twu.biblioteca.enums.ConsoleState.CHECK_OUT_BOOK;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
import static org.junit.Assert.*;

public class BibliotecaAppTestForCheckOutBook {
    
    private BibliotecaLibrary library;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        library.login("000-0001", "p1");
        app = new BibliotecaApp(library);
    }

    @Test
    public void should_make_book_isCheckOut_to_be_true_when_check_out_success() throws Exception {
        Book book = library.getBooks().get(0);
        app.checkOutBook("book1");
        assertTrue(book.isCheckOut());
    }

    @Test
    public void should_return_COMMAND_state_and_successful_message_when_check_out_success() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, CHECK_OUT_BOOK_SUCCESS);
        CommandResult result = app.checkOutBook("book1");
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_failed_message_when_check_out_fail() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, CHECK_OUT_BOOK_FAIL);
        CommandResult resutl = app.checkOutBook("bookName");
        assertEquals(expectResult, resutl);
    }
}