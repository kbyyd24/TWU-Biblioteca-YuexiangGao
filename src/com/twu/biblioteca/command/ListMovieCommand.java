package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;
import com.twu.biblioteca.model.Movie;

import java.util.List;

import static com.twu.biblioteca.enums.ConsoleState.COMMAND;

public class ListMovieCommand implements Command {

    private List<Movie> movies;

    public ListMovieCommand(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public CommandResult exec() {
        return new CommandResult(COMMAND, buildMovieList());
    }

    private String buildMovieList() {
        StringBuilder builder = new StringBuilder();
        movies.stream()
                .filter(movie -> !movie.isCheckOut())
                .map(Movie::loadDetail)
                .forEach(detail -> builder.append(detail).append('\n'));
        return builder.toString();
    }
}
