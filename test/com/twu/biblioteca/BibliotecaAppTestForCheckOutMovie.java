package com.twu.biblioteca;

import com.twu.biblioteca.model.CommandResult;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.config.OptionResultMessage.CHECK_OUT_MOVIE_FAIL;
import static com.twu.biblioteca.config.OptionResultMessage.CHECK_OUT_MOVIE_SUCCESS;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTestForCheckOutMovie {

    private BibliotecaLibrary library;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        app = new BibliotecaApp(library);
    }

    @Test
    public void should_change_movie_isCheckOut_to_true_when_check_out_movie_success() throws Exception {
        app.checkOutMovie("movie1");
        assertTrue(library.getMovies().get(0).isCheckOut());
    }

    @Test
    public void should_return_COMMAND_state_and_successful_message_when_check_out_movie_success() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, CHECK_OUT_MOVIE_SUCCESS);
        CommandResult result = app.checkOutMovie("movie1");
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_failed_message_when_check_out_fail() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, CHECK_OUT_MOVIE_FAIL);
        CommandResult result = app.checkOutMovie("movieName");
        assertEquals(expectResult, result);
    }

}