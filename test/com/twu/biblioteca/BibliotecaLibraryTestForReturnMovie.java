package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForReturnMovie {

    private BibliotecaLibrary library;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
    }

    @Test
    public void return_movie_should_success_when_movie_isCheckOut_is_true() throws Exception {
        library.getMovies().get(0).setCheckOut(true);
        library.returnMovie("movie1");
        assertFalse(library.getMovies().get(0).isCheckOut());
    }

    @Test
    public void should_return_true_when_return_movie_success() throws Exception {
        library.getMovies().get(0).setCheckOut(true);
        assertTrue(library.returnMovie("movie1"));
    }

    @Test
    public void should_return_false_when_return_movie_failed() throws Exception {
        assertFalse(library.returnMovie("movieName"));
    }

    @Test
    public void return_movie_should_failed_when_movie_isCheckOut_is_false() throws Exception {
        assertFalse(library.returnMovie("movie1"));
    }
}