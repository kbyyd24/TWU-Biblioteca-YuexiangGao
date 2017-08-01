package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.CHECK_OUT_MOVIE_MSG;
import static com.twu.biblioteca.enums.ConsoleState.CHECK_OUT_MOVIE;

public class CheckOutMovieCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(CHECK_OUT_MOVIE, CHECK_OUT_MOVIE_MSG.getMsg());
    }
}
