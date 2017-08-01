package com.twu.biblioteca.command;

import com.twu.biblioteca.model.CommandResult;

@FunctionalInterface
public interface Command {

    CommandResult exec();

}
