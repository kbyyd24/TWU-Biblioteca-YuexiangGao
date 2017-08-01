package com.twu.biblioteca.command;

import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.INVALID_NOTICE_MSG;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;

public class InvalidOptionCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(COMMAND, INVALID_NOTICE_MSG.getMsg());
    }
}
