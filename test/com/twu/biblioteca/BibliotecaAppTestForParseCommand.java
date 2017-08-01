package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static com.twu.biblioteca.enums.ConsoleState.*;
import static org.junit.Assert.*;

public class BibliotecaAppTestForParseCommand {

    private BibliotecaLibrary library;
    private ByteArrayOutputStream outputMonitor = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputMonitor));
        library = new BibliotecaLibrary();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void should_change_console_state_to_CHECK_OUT_BOOK_when_choose_check_out_book() throws Exception {
        BibliotecaApp.state = COMMAND;
        String checkout = "cob";
        BibliotecaApp.parseCommand(library, checkout);
        assertEquals(CHECK_OUT_BOOK, BibliotecaApp.state);
    }

    @Test
    public void should_change_state_to_CHECK_OUT_MOVIE_when_choose_check_out_movie() throws Exception {
        BibliotecaApp.state = COMMAND;
        BibliotecaApp.parseCommand(library, "com");
        assertEquals(CHECK_OUT_MOVIE, BibliotecaApp.state);
    }

    @Test
    public void should_change_state_to_RETURN_MOVIE_when_choose_option_return_movie() throws Exception {
        BibliotecaApp.state = COMMAND;
        BibliotecaApp.parseCommand(library, "rm");
        assertEquals(RETURN_MOVIE, BibliotecaApp.state);
    }

    @Test
    public void should_change_state_to_RETURN_BOOK_when_choose_return_book_option() throws Exception {
        BibliotecaApp.state = COMMAND;
        BibliotecaApp.parseCommand(null, "rb");
        assertEquals(RETURN_BOOK, BibliotecaApp.state);
    }

    @Test
    public void should_print_book_list_when_choose_list_book_option_and_state_is_command() throws Exception {
        List<Book> books = library.getBooks();
        StringBuilder listStr = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            listStr.append(String.format("%d. %s\n", i + 1, books.get(i).loadDetail()));
        }
        BibliotecaApp.parseCommand(library, "1");
        assertEquals(listStr.toString(), outputMonitor.toString());
    }

    @Test
    public void should_print_invalid_notification_when_choose_an_invalid_option() throws Exception {
        BibliotecaApp.parseCommand(null, "invalid option");
        assertEquals("Select a valid option!\n", outputMonitor.toString());
    }

    @Test
    public void should_print_movie_list_without_checked_out_movies() throws Exception {
        List<Movie> movies = library.getMovies();
        StringBuilder listStr = new StringBuilder();
        movies.stream()
                .filter(movie -> !movie.isCheckOut())
                .map(Movie::loadDetail)
                .forEach(detail -> listStr.append(detail).append("\n"));
        BibliotecaApp.parseCommand(library, "lm");
        assertEquals(listStr.toString(), outputMonitor.toString());
    }

    @Test
    public void should_return_false_when_option_is_quit() throws Exception {
        assertFalse(BibliotecaApp.parseCommand(null, "q"));
    }

    @Test
    public void should_return_true_when_choose_an_invalid_option() throws Exception {
        assertTrue(BibliotecaApp.parseCommand(null, "invalid option"));
    }

    @Test
    public void should_return_true_when_option_is_list_book() throws Exception {
        assertTrue(BibliotecaApp.parseCommand(library, "1"));
    }

}