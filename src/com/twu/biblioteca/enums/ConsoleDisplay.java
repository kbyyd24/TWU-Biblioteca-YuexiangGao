package com.twu.biblioteca.enums;

public enum ConsoleDisplay {
    CHECK_OUT_BOOK_MSG("Input book name which you want to check out"),
    RETURN_BOOK_MSG("Input book name which you want to return"),
    CHECK_OUT_MOVIE_MSG("Input movie name which you want to check out"),
    RETURN_MOVIE_MSG("Input movie name which you want to return"),
    INVALID_NOTICE_MSG("Select a valid option!"),
    QUIT_MSG("Goodbye!");

    private String msg;

    ConsoleDisplay(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
