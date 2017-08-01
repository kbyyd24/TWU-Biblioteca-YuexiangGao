package com.twu.biblioteca;

import com.twu.biblioteca.config.Welcome;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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


}