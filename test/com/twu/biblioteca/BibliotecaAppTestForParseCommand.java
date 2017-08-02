package com.twu.biblioteca;

import com.twu.biblioteca.enums.ConsoleDisplay;
import com.twu.biblioteca.enums.MainMenuItem;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.CommandResult;
import com.twu.biblioteca.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static com.twu.biblioteca.enums.ConsoleDisplay.*;
import static com.twu.biblioteca.enums.ConsoleState.*;
import static org.junit.Assert.*;

public class BibliotecaAppTestForParseCommand {

    private BibliotecaLibrary library;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        app = new BibliotecaApp(library);
    }

    @Test
    public void should_return_COMMAND_state_and_book_list_message_when_choose_list_book_option() throws Exception {
        StringBuilder listStr = new StringBuilder();
        library.getBooks().stream()
                .filter(book -> !book.isCheckOut())
                .map(Book::loadDetail)
                .forEach(detail -> listStr.append(detail).append('\n'));
        CommandResult result = app.parseCommand(MainMenuItem.LIST_BOOK.getCommand());
        assertEquals(COMMAND, result.getState());
        assertEquals(listStr.toString(), result.getDisplayMsg());
    }

    @Test
    public void should_return_CHECK_OUT_BOOK_state_and_message_when_choose_check_out_book_option() throws Exception {
        CommandResult expectResult = new CommandResult(CHECK_OUT_BOOK, CHECK_OUT_BOOK_MSG.getMsg());
        CommandResult result = app.parseCommand(MainMenuItem.CHECK_OUT_BOOK.getCommand());
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_RETURN_BOOK_state_and_message_when_choose_return_book_option() throws Exception {
        CommandResult expectResult = new CommandResult(RETURN_BOOK, RETURN_BOOK_MSG.getMsg());
        CommandResult result = app.parseCommand(MainMenuItem.RETURN_BOOK.getCommand());
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_movie_list_message_when_choose_list_movie_option() throws Exception {
        StringBuilder listStr = new StringBuilder();
        library.getMovies().stream()
                .filter(movie -> !movie.isCheckOut())
                .map(Movie::loadDetail)
                .forEach(detail -> listStr.append(detail).append("\n"));
        CommandResult result = app.parseCommand(MainMenuItem.LIST_MOVIE.getCommand());
        assertEquals(COMMAND, result.getState());
        assertEquals(listStr.toString(), result.getDisplayMsg());
    }

    @Test
    public void should_return_CHECK_OUT_MOVIE_state_and_message_when_choose_check_out_movie_option() throws Exception {
        CommandResult expectResult = new CommandResult(CHECK_OUT_MOVIE, CHECK_OUT_MOVIE_MSG.getMsg());
        CommandResult result = app.parseCommand(MainMenuItem.CHECK_OUT_MOVIE.getCommand());
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_RETURN_MOVIE_state_and_message_when_choose_return_movie_option() throws Exception {
        CommandResult expectResult = new CommandResult(RETURN_MOVIE, RETURN_MOVIE_MSG.getMsg());
        CommandResult result = app.parseCommand(MainMenuItem.RETURN_MOVIE.getCommand());
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_invalid_notice_when_choose_an_invalid_option() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, ConsoleDisplay.INVALID_NOTICE_MSG.getMsg());
        CommandResult result = app.parseCommand("invalid option");
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_QUIT_state_and_message_when_choose_quit_option() throws Exception {
        CommandResult expectResult = new CommandResult(QUIT, ConsoleDisplay.QUIT_MSG.getMsg());
        CommandResult result = app.parseCommand(MainMenuItem.QUIT.getCommand());
        assertEquals(expectResult, result);
    }

}