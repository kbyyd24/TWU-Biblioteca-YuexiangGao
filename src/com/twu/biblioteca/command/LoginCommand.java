package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.LOGIN_MSG;
import static com.twu.biblioteca.enums.ConsoleState.LOGIN;

public class LoginCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(LOGIN, LOGIN_MSG.getMsg());
    }
}
