package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.QUIT_MSG;
import static com.twu.biblioteca.enums.ConsoleState.QUIT;

public class QuitCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(QUIT, QUIT_MSG.getMsg());
    }
}
