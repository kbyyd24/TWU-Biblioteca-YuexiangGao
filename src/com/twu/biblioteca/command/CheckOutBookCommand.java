package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.CHECK_OUT_BOOK_MSG;
import static com.twu.biblioteca.enums.ConsoleState.CHECK_OUT_BOOK;

public class CheckOutBookCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(CHECK_OUT_BOOK, CHECK_OUT_BOOK_MSG.getMsg());
    }
}
