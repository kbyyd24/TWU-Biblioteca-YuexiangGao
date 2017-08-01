package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.RETURN_BOOK_MSG;
import static com.twu.biblioteca.enums.ConsoleState.RETURN_BOOK;

public class ReturnBookCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(RETURN_BOOK, RETURN_BOOK_MSG.getMsg());
    }
}
