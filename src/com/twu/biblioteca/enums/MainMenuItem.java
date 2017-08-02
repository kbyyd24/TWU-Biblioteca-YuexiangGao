package com.twu.biblioteca.enums;

public enum MainMenuItem {
    LIST_BOOK("lb", "List Book"),
    CHECK_OUT_BOOK("cb", "Check Out Book"),
    RETURN_BOOK("rb", "Return Book"),
    LIST_MOVIE("lm", "List Movie"),
    CHECK_OUT_MOVIE("cm", "Check Out Movie"),
    RETURN_MOVIE("rm", "Return Movie"),
    QUIT("qu", "Quit"),
    ;

    private String command;
    private String option;

    MainMenuItem(String command, String option) {
        this.command = command;
        this.option = option;
    }

    public String getCommand() {
        return command;
    }

    public String getOption() {
        return option;
    }
}
