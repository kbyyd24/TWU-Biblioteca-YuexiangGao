package com.twu.biblioteca;

import com.twu.biblioteca.command.*;
import com.twu.biblioteca.config.Welcome;
import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.CommandResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.twu.biblioteca.config.OptionResultMessage.*;
import static com.twu.biblioteca.enums.ConsoleState.*;

public class BibliotecaApp {

    private ConsoleState state;
    private Map<String, String> mainMenu;
    private Map<String, Command> optionCommandMap;
    private Map<ConsoleState, Function<String, CommandResult>> parseInputMap;
    private BibliotecaLibrary library;

    public BibliotecaApp(BibliotecaLibrary library) {
        state = COMMAND;
        this.library = library;
        mainMenu = buildMainMenuMap();
        optionCommandMap = buildOptionCommandMap();
        parseInputMap = buildParseInputMap();
    }

    private Map<String, String> buildMainMenuMap() {
        Map<String, String> mainMenu = new HashMap<>();
        mainMenu.put("1", "List Book");
        mainMenu.put("cob", "Check Out Book");
        mainMenu.put("rb", "Return Book");
        mainMenu.put("lm", "List Movie");
        mainMenu.put("com", "Check Out Movie");
        mainMenu.put("rm", "Return Movie");
        mainMenu.put("q", "Quit");
        return mainMenu;
    }

    private Map<String, Command> buildOptionCommandMap() {
        Map<String, Command> optionComandMap = new HashMap<>();
        optionComandMap.put("List Book", new ListBookCommand(library.getBooks()));
        optionComandMap.put("Check Out Book", new CheckOutBookCommand());
        optionComandMap.put("Return Book", new ReturnBookCommand());
        optionComandMap.put("List Movie", new ListMovieCommand(library.getMovies()));
        optionComandMap.put("Check Out Movie", new CheckOutMovieCommand());
        optionComandMap.put("Return Movie", new ReturnMovieCommand());
        optionComandMap.put("Quit", new QuitCommand());
        return optionComandMap;
    }

    private Map<ConsoleState, Function<String, CommandResult>> buildParseInputMap() {
        Map<ConsoleState, Function<String, CommandResult>> map = new HashMap<>();
        map.put(COMMAND, this::parseCommand);
        map.put(CHECK_OUT_BOOK, this::checkOutBook);
        map.put(RETURN_BOOK, this::returnBook);
        map.put(CHECK_OUT_MOVIE, this::checkOutMovie);
        map.put(RETURN_MOVIE, this::returnMovie);
        return map;
    }

    public Map<String, String> getMainMenu() {
        return mainMenu;
    }

    public static void main(String[] args) {
        BibliotecaLibrary library = new BibliotecaLibrary();
        BibliotecaApp app = new BibliotecaApp(library);
        app.start();
    }

    private void start() {
        printWelcome();
        printMainMenu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            parseInput(input);
            if (state == COMMAND) {
                printMainMenu();
            } else if (state == QUIT) {
                break;
            }
        }
        scanner.close();
    }

    private void parseInput(String input) {
        CommandResult result = parseInputMap.get(state).apply(input);
        state = result.getState();
        System.out.println(result.getDisplayMsg());
    }

    CommandResult parseCommand(String input) {
        String option = mainMenu.compute(input, (command, opt) -> opt == null ? "Invalid Option" : opt);
        return optionCommandMap
                .compute(option, (key, command) ->
                        command == null ? new InvalidOptionCommand() : command).exec();
    }

    void printWelcome() {
        System.out.println(Welcome.msg);
    }

    void printMainMenu() {
        System.out.println(" Main Menu ");
        System.out.println("command | action");
        mainMenu.forEach((key, value) ->
                System.out.println(String.format("%s    %s", key, value)));
    }

    CommandResult checkOutBook(String bookName) {
        return new CommandResult(COMMAND,
                library.checkOutBook(bookName) ?
                        CHECK_OUT_BOOK_SUCCESS :
                        CHECK_OUT_BOOK_FAIL);
    }

    CommandResult returnBook(String bookName) {
        return new CommandResult(COMMAND,
                library.returnBook(bookName) ?
                        RETURN_BOOK_SUCCESS :
                        RETURN_BOOK_FAIL);
    }

    CommandResult checkOutMovie(String movieName) {
        return new CommandResult(COMMAND,
                library.checkOutMovie(movieName) ?
                        CHECK_OUT_MOVIE_SUCCESS :
                        CHECK_OUT_MOVIE_FAIL);
    }

    CommandResult returnMovie(String movieName) {
        return new CommandResult(COMMAND,
                library.returnMovie(movieName) ?
                        RETURN_MOVIE_SUCCESS :
                        RETURN_MOVIE_FAIL);
    }

}
