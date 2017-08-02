package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForCheckOutMovie {

    private BibliotecaLibrary library;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
    }

    @Test
    public void should_set_movie_isCheckOut_to_be_true_when_check_out_movie_success() throws Exception {
        String movieName = "movie1";
        library.checkOutMovie(movieName);
        assertTrue(library.getMovies().get(0).isCheckOut());
    }

    @Test
    public void should_return_true_when_check_out_movie_success() throws Exception {
        assertTrue(library.checkOutMovie("movie1"));
    }

    @Test
    public void should_return_false_when_check_out_movie_failed() throws Exception {
        assertFalse(library.checkOutMovie("movieName"));
    }

    @Test
    public void should_check_out_fail_when_movie_isCheckOut_is_true() throws Exception {
        library.getMovies().get(0).setCheckOut(true);
        assertFalse(library.checkOutMovie("movie1"));
    }
}