package com.twu.biblioteca.command;

import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.CommandResult;

import java.util.List;

import static com.twu.biblioteca.enums.ConsoleState.COMMAND;

public class ListBookCommand implements Command {

    private List<Book> books;

    public ListBookCommand(List<Book> books) {
        this.books = books;
    }

    @Override
    public CommandResult exec() {
        return new CommandResult(COMMAND, buildBookList());
    }

    private String buildBookList() {
        StringBuilder builder = new StringBuilder();
        books.stream()
                .filter(book -> !book.isCheckOut())
                .map(Book::loadDetail)
                .forEach(detail -> builder.append(detail).append('\n'));
        return builder.toString();
    }
}
