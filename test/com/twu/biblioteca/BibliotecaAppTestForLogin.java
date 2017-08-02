package com.twu.biblioteca;

import com.twu.biblioteca.config.OptionResultMessage;
import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.CommandResult;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.enums.ConsoleState.*;
import static org.junit.Assert.*;

public class BibliotecaAppTestForLogin {

    private BibliotecaLibrary library;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        app = new BibliotecaApp(library);
    }

    @Test
    public void should_return_COMMAND_state_and_successful_message_when_login_success() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, OptionResultMessage.LOGIN_SUCCESS);
        CommandResult result = app.login("000-0001,p1");
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_failed_message_when_login_fail() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, OptionResultMessage.LOGIN_FAIL);
        CommandResult result = app.login("num,pass");
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_failed_message_when_input_is_invalid() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, OptionResultMessage.LOGIN_FAIL);
        CommandResult result = app.login("invalid input");
        assertEquals(expectResult, result);
    }
}