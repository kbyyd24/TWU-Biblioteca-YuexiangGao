package com.twu.biblioteca.command;

import com.twu.biblioteca.BibliotecaLibrary;
import com.twu.biblioteca.model.CommandResult;
import com.twu.biblioteca.model.User;

import static com.twu.biblioteca.config.OptionResultMessage.OFFLINE;
import static com.twu.biblioteca.enums.ConsoleState.COMMAND;

public class LoadUserInfoCommand implements Command {

    private BibliotecaLibrary library;

    public LoadUserInfoCommand(BibliotecaLibrary library) {
        this.library = library;
    }

    @Override
    public CommandResult exec() {
        User user = library.getLoginUser();
        String msg = user == null ? OFFLINE : user.loadInfo();
        return new CommandResult(COMMAND, msg);
    }
}
