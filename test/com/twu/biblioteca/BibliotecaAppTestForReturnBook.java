package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.CommandResult;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.twu.biblioteca.config.OptionResultMessage.RETURN_BOOK_FAIL;
import static com.twu.biblioteca.config.OptionResultMessage.RETURN_BOOK_SUCCESS;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
import static com.twu.biblioteca.enums.ConsoleState.RETURN_BOOK;
import static org.junit.Assert.*;

public class BibliotecaAppTestForReturnBook {

    private BibliotecaLibrary library;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        app = new BibliotecaApp(library);
    }

    @Test
    public void should_change_book_isCheckOut_to_be_false_when_return_success() throws Exception {
        Book book = library.getBooks().get(0);
        book.setCheckOut(true);
        app.returnBook("book1");
        assertFalse(book.isCheckOut());
    }

    @Test
    public void should_return_COMMAND_state_and_successful_message_when_return_book_success() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, RETURN_BOOK_SUCCESS);
        String bookName = "book1";
        library.checkOutBook(bookName);
        CommandResult result = app.returnBook(bookName);
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_failed_message_when_return_book_fail() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, RETURN_BOOK_FAIL);
        CommandResult result = app.returnBook("bookName");
        assertEquals(expectResult, result);
    }
}