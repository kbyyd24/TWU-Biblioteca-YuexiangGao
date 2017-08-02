package com.twu.biblioteca;

import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaLibraryTestForLogin {

    private BibliotecaLibrary library;

    @Before
    public void setUp() throws Exception {
        library = new BibliotecaLibrary();
    }

    @Test
    public void should_set_loginUser_when_login_success() throws Exception {
        String libraryNumber = "000-0001";
        String password = "p1";
        User user = new User(libraryNumber, password);
        library.login(libraryNumber, password);
        assertEquals(user, library.getLoginUser());
    }

    @Test
    public void should_return_true_when_login_success() throws Exception {
        assertTrue(library.login("000-0001", "p1"));
    }

    @Test
    public void should_not_set_loginUser_when_login_fail() throws Exception {
        library.login("number", "password");
        assertNull(library.getLoginUser());
    }

    @Test
    public void should_return_false_when_login_fail() throws Exception {
        assertFalse(library.login("number", "password"));
    }
}