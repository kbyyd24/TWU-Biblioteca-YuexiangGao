package com.twu.biblioteca.command;

import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.CommandResult;

import static com.twu.biblioteca.enums.ConsoleDisplay.RETURN_MOVIE_MSG;
import static com.twu.biblioteca.enums.ConsoleState.RETURN_MOVIE;

public class ReturnMovieCommand implements Command {
    @Override
    public CommandResult exec() {
        return new CommandResult(RETURN_MOVIE, RETURN_MOVIE_MSG.getMsg());
    }
}
