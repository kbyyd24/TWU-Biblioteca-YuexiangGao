package com.twu.biblioteca;

import com.twu.biblioteca.command.*;
import com.twu.biblioteca.enums.ConsoleState;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.CommandResult;
import com.twu.biblioteca.model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.twu.biblioteca.enums.ConsoleState.*;

public class BibliotecaApp {

    static Map<String, String> mainMenu;
    static ConsoleState state;

    static {
        state = COMMAND;
        mainMenu = new HashMap<>();
        mainMenu.put("1", "List Book");
        mainMenu.put("cob", "Check Out Book");
        mainMenu.put("rb", "Return Book");
        mainMenu.put("lm", "List Movie");
        mainMenu.put("com", "Check Out Movie");
        mainMenu.put("rm", "Return Movie");
        mainMenu.put("q", "Quit");
    }

    private BibliotecaLibrary library;

    public BibliotecaApp(BibliotecaLibrary library) {
        this.library = library;
    }

    public static void main(String[] args) {
        BibliotecaLibrary library = new BibliotecaLibrary();
        BibliotecaApp app = new BibliotecaApp(library);
        app.start();
    }

    private void start() {
        printWelcome(library);
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
        if (state == COMMAND) {
            CommandResult result = parseCommand(input);
            state = result.getState();
            System.out.println(result.getDisplayMsg());
        } else if (state == CHECK_OUT_BOOK) {
            printCheckOutResult(checkOutBook(library, input));
        } else if (state == RETURN_BOOK) {
            printReturnBookResult(returnBook(library, input));
        } else if (state == CHECK_OUT_MOVIE) {
            printCheckOutMovieResult(checkOutMovie(library, input));
        } else if (state == RETURN_MOVIE) {
            printReturnMovieResult(returnMovie(library, input));
        }
    }

    private static Map<String, Command> buildOptionCommandMap(BibliotecaLibrary library) {
        Map<String, Command> map = new HashMap<>();
        map.put("List Book", new ListBookCommand(library.getBooks()));
        map.put("Check Out Book", new CheckOutBookCommand());
        map.put("Return Book", new ReturnBookCommand());
        map.put("List Movie", new ListMovieCommand(library.getMovies()));
        map.put("Check Out Movie", new CheckOutMovieCommand());
        map.put("Return Movie", new ReturnMovieCommand());
        map.put("Quit", new QuitCommand());
        return map;
    }

    CommandResult parseCommand(String input) {
        String option = mainMenu.compute(input, (command, opt) -> opt == null ? "Invalid Option" : opt);
        Map<String, Command> stringCommandMap = buildOptionCommandMap(library);
        return stringCommandMap
                .compute(option, (key, command) ->
                        command == null ? new InvalidOptionCommand() : command).exec();
    }

    static void printCheckOutResult(boolean result) {
        System.out.println(result ?
                "Thank you! Enjoy the book" :
                "That book is not available");
    }

    static boolean checkOutBook(BibliotecaLibrary library, String bookName) {
        state = COMMAND;
        return library.checkOutBook(bookName);
    }

    static void printWelcome(BibliotecaLibrary library) {
        System.out.println(library.getWelcome());
    }

    static void printBookList(BibliotecaLibrary library) {
        List<Book> books = library.getBooks().stream().filter(book1 -> !book1.isCheckOut()).collect(Collectors.toList());
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.isCheckOut()) {
                System.out.println(String.format("%d. %s", i + 1, book.loadDetail()));
            }
        }
    }

    static void printMainMenu() {
        System.out.println(" Main Menu ");
        System.out.println("command | action");
        mainMenu.forEach((key, value) ->
                System.out.println(String.format("%s    %s", key, value)));
    }

    static void printInvalidOptoinNotification() {
        System.out.println("Select a valid option!");
    }

    static boolean returnBook(BibliotecaLibrary library, String bookName) {
        state = COMMAND;
        return library.returnBook(bookName);
    }

    static void printReturnBookResult(boolean result) {
        System.out.println(result ?
                "Thank you for returning the book." :
                "That is not a valid book to return");
    }

    static boolean checkOutMovie(BibliotecaLibrary library, String movieName) {
        state = COMMAND;
        return library.checkOutMovie(movieName);
    }

    static void printCheckOutMovieResult(boolean result) {
        System.out.println(result ?
                "Thank you! Enjoy the movie!" :
                "That movie is not available!");
    }

    static boolean returnMovie(BibliotecaLibrary library, String movieName) {
        state = COMMAND;
        return library.returnMovie(movieName);

    }

    static void printReturnMovieResult(boolean result) {
        System.out.println(result ?
                "Thank you for returning the movie." :
                "That is not a valid movie to return.");
    }
}
