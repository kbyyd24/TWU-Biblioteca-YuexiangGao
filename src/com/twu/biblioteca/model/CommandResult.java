package com.twu.biblioteca.model;

import com.twu.biblioteca.enums.ConsoleDisplay;
import com.twu.biblioteca.enums.ConsoleState;

public class CommandResult {

    private ConsoleState state;
    private String displayMsg;

    public CommandResult(ConsoleState state, String displayMsg) {
        this.state = state;
        this.displayMsg = displayMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandResult result = (CommandResult) o;

        if (getState() != result.getState()) return false;
        return getDisplayMsg() != null ? getDisplayMsg().equals(result.getDisplayMsg()) : result.getDisplayMsg() == null;
    }

    @Override
    public int hashCode() {
        int result = getState() != null ? getState().hashCode() : 0;
        result = 31 * result + (getDisplayMsg() != null ? getDisplayMsg().hashCode() : 0);
        return result;
    }

    public ConsoleState getState() {
        return state;
    }

    public String getDisplayMsg() {
        return displayMsg;
    }
}
