package com.twu.biblioteca;

import com.twu.biblioteca.config.Welcome;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.twu.biblioteca.enums.ConsoleState.*;
import static org.junit.Assert.*;

public class BibliotecaAppTest {

    private BibliotecaLibrary library;
    private ByteArrayOutputStream outputMonitor = new ByteArrayOutputStream();
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputMonitor));
        library = new BibliotecaLibrary();
        app = new BibliotecaApp(library);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void should_print_welcome_message() throws Exception {
        app.printWelcome();
        assertEquals(Welcome.msg + '\n', outputMonitor.toString());
    }


    @Test
    public void should_print_main_menu() throws Exception {
        StringBuilder mainMenuBuilder = new StringBuilder(" Main Menu \n");
        mainMenuBuilder.append("command | action\n");
        app.getMainMenu().forEach((key, value) ->
                mainMenuBuilder.append(String.format("%s    %s\n", key, value)));
        app.printMainMenu();
        assertEquals(mainMenuBuilder.toString(), outputMonitor.toString());
    }












    @Test
    public void should_print_successful_message_when_check_out_success() throws Exception {
        BibliotecaApp.printCheckOutResult(true);
        assertEquals("Thank you! Enjoy the book\n", outputMonitor.toString());
    }

    @Test
    public void should_print_unsuccessful_message_when_check_out_book_failed() throws Exception {
        BibliotecaApp.printCheckOutResult(false);
        assertEquals("That book is not available\n", outputMonitor.toString());
    }











    @Test
    public void should_print_successful_message_when_return_book_success() throws Exception {
        BibliotecaApp.printReturnBookResult(true);
        assertEquals("Thank you for returning the book.\n", outputMonitor.toString());
    }

    @Test
    public void should_print_unsuccessful_message_when_return_book_failed() throws Exception {
        BibliotecaApp.printReturnBookResult(false);
        assertEquals("That is not a valid book to return\n", outputMonitor.toString());
    }















    @Test
    public void should_print_successful_message_when_check_out_movie_result_is_true() throws Exception {
        BibliotecaApp.printCheckOutMovieResult(true);
        assertEquals("Thank you! Enjoy the movie!\n", outputMonitor.toString());
    }

    @Test
    public void should_print_unsuccessful_message_when_check_out_movie_result_is_false() throws Exception {
        BibliotecaApp.printCheckOutMovieResult(false);
        assertEquals("That movie is not available!\n", outputMonitor.toString());
    }









    @Test
    public void should_print_successful_message_when_return_movie_success() throws Exception {
        BibliotecaApp.printReturnMovieResult(true);
        assertEquals("Thank you for returning the movie.\n", outputMonitor.toString());
    }

    @Test
    public void should_print_unsuccessful_message_when_return_movie_failed() throws Exception {
        BibliotecaApp.printReturnMovieResult(false);
        assertEquals("That is not a valid movie to return.\n", outputMonitor.toString());
    }
}