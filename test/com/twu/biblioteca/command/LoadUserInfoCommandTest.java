package com.twu.biblioteca.command;

import com.twu.biblioteca.BibliotecaLibrary;
import com.twu.biblioteca.model.CommandResult;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.config.OptionResultMessage.OFFLINE;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;
import static org.junit.Assert.assertEquals;

public class LoadUserInfoCommandTest {

    private BibliotecaLibrary library;
    private Command command;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();

        command = new LoadUserInfoCommand(library);
    }

    @Test
    public void should_return_COMMAND_state_and_offline_message_when_no_user_is_logged_in() throws Exception {
        CommandResult expectResult = new CommandResult(COMMAND, OFFLINE);
        CommandResult result = command.exec();
        assertEquals(expectResult, result);
    }

    @Test
    public void should_return_COMMAND_state_and_user_info_string_when_user_is_logged_in() throws Exception {
        library.login("000-0001", "p1");
        CommandResult expectResult = new CommandResult(COMMAND, library.getLoginUser().loadInfo());
        CommandResult result = command.exec();
        assertEquals(expectResult, result);
    }
}