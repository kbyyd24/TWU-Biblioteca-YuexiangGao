package com.twu.biblioteca;

import com.twu.biblioteca.command.*;
import com.twu.biblioteca.config.Welcome;
import com.twu.biblioteca.enums.ConsoleDisplay;
import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.enums.MainMenuItem;
import com.twu.biblioteca.model.CommandResult;

import java.util.*;
import java.util.function.Function;

import static com.twu.biblioteca.config.OptionResultMessage.*;
import static com.twu.biblioteca.enums.ConsoleDisplay.LOGIN_MSG;
import static com.twu.biblioteca.enums.ConsoleState.*;
import static com.twu.biblioteca.enums.MainMenuItem.LIST_BOOK;

public class BibliotecaApp {

    private ConsoleState state;
    private List<MainMenuItem> mainMenu;
    private Map<String, Command> optionCommandMap;
    private Map<ConsoleState, Function<String, CommandResult>> parseInputMap;
    private BibliotecaLibrary library;

    public BibliotecaApp(BibliotecaLibrary library) {
        state = COMMAND;
        this.library = library;
        mainMenu = buildMainMenu();
        optionCommandMap = buildOptionCommandMap();
        parseInputMap = buildParseInputMap();
    }

    private List<MainMenuItem> buildMainMenu() {
        return Arrays.asList(MainMenuItem.values());
    }

    private Map<String, Command> buildOptionCommandMap() {
        Map<String, Command> optionComandMap = new HashMap<>();
        optionComandMap.put(MainMenuItem.LIST_BOOK.getOption(), new ListBookCommand(library.getBooks()));
        optionComandMap.put(MainMenuItem.CHECK_OUT_BOOK.getOption(), new CheckOutBookCommand());
        optionComandMap.put(MainMenuItem.RETURN_BOOK.getOption(), new ReturnBookCommand());
        optionComandMap.put(MainMenuItem.LIST_MOVIE.getOption(), new ListMovieCommand(library.getMovies()));
        optionComandMap.put(MainMenuItem.CHECK_OUT_MOVIE.getOption(), new CheckOutMovieCommand());
        optionComandMap.put(MainMenuItem.RETURN_MOVIE.getOption(), new ReturnMovieCommand());
        optionComandMap.put(MainMenuItem.LOGIN.getOption(), new LoginCommand());
        optionComandMap.put(MainMenuItem.LOAD_USER_INFO.getOption(), new LoadUserInfoCommand(library));
        optionComandMap.put(MainMenuItem.QUIT.getOption(), new QuitCommand());
        return optionComandMap;
    }

    private Map<ConsoleState, Function<String, CommandResult>> buildParseInputMap() {
        Map<ConsoleState, Function<String, CommandResult>> map = new HashMap<>();
        map.put(COMMAND, this::parseCommand);
        map.put(CHECK_OUT_BOOK, this::checkOutBook);
        map.put(RETURN_BOOK, this::returnBook);
        map.put(CHECK_OUT_MOVIE, this::checkOutMovie);
        map.put(RETURN_MOVIE, this::returnMovie);
        map.put(LOGIN, this::login);
        return map;
    }

    public List<MainMenuItem> getMainMenu() {
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
        String option = mainMenu.stream()
                .filter(item -> item.getCommand().equals(input))
                .findFirst()
                .map(MainMenuItem::getOption)
                .orElse("Invalid Option");
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
        mainMenu.forEach(item ->
                System.out.println(String.format("%s    %s", item.getCommand(), item.getOption())));
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

    public CommandResult login(String numberAndPassword) {
        String[] split = numberAndPassword.split(",");
        if (split.length != 2) {
            return new CommandResult(COMMAND, LOGIN_FAIL);
        }
        String libraryNumber = split[0];
        String password = split[1];
        return new CommandResult(COMMAND,
                library.login(libraryNumber, password) ?
                        LOGIN_SUCCESS :
                        LOGIN_FAIL);
    }
}
