package com.twu.biblioteca;

import com.twu.biblioteca.model.CommandResult;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.config.OptionResultMessage.RETURN_MOVIE_FAIL;
import static com.twu.biblioteca.config.OptionResultMessage.RETURN_MOVIE_SUCCESS;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
import static com.twu.biblioteca.enums.ConsoleState.RETURN_MOVIE;
import static org.junit.Assert.*;

public class BibliotecaAppTestForReturnMovie {

    private BibliotecaLibrary library;
    private BibliotecaApp app;
    private String movieName;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        library.login("000-0001", "p1");
        app = new BibliotecaApp(library);
        movieName = "movie1";
        library.checkOutMovie(movieName);
    }

    @Test
    public void should_change_movie_isCheckOut_to_false_when_return_movie_success() throws Exception {
        app.returnMovie(movieName);
        assertFalse(library.getMovies().get(0).isCheckOut());
    }

    @Test
    public void should_return_COMMAND_state_and_successful_message_when_return_movie_success() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, RETURN_MOVIE_SUCCESS);
        CommandResult result = app.returnMovie(movieName);
        assertEquals(result, expectResult);
    }

    @Test
    public void should_return_COMMAND_state_and_failed_message_when_return_movie_fail() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, RETURN_MOVIE_FAIL);
        CommandResult result = app.returnMovie("movieName");
        assertEquals(expectResult, result);
    }
}