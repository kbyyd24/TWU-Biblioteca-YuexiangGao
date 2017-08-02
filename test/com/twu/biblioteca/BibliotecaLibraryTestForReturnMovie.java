package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForReturnMovie {

    private BibliotecaLibrary library;
    private String number;
    private String password;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
        number = "000-0001";
        password = "p1";
    }

    @Test
    public void return_movie_should_success_when_movie_isCheckOut_is_true() throws Exception {
        library.login(number, password);
        String movieName = "movie1";
        library.checkOutMovie(movieName);
        library.returnMovie(movieName);
        assertFalse(library.getMovies().get(0).isCheckOut());
    }

    @Test
    public void should_return_true_when_return_movie_success() throws Exception {
        library.login(number, password);
        String movieName = "movie1";
        library.checkOutMovie(movieName);
        assertTrue(library.returnMovie(movieName));
    }

    @Test
    public void should_return_false_when_return_movie_failed() throws Exception {
        library.login(number, password);
        assertFalse(library.returnMovie("movieName"));
    }

    @Test
    public void return_movie_should_failed_when_movie_isCheckOut_is_false() throws Exception {
        library.login(number, password);
        assertFalse(library.returnMovie("movie1"));
    }

    @Test
    public void should_return_false_when_login_user_not_check_out_this_movie() throws Exception {
        library.login(number, password);
        String movieName = "movie1";
        library.checkOutMovie(movieName);
        library.getLoginUser().getCheckOutMovies().remove(0);
        assertFalse(library.returnMovie(movieName));
    }

    @Test
    public void should_return_false_when_login_user_is_null() throws Exception {
        assertFalse(library.returnMovie(""));
    }
}