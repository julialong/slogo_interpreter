package model;

public enum CommandType {
    COMMAND("Command"),
    ARGUMENT("Argument"),
    CONTROL("Command");

    private String textRepresentation;

    CommandType(String command) {
        this.textRepresentation = command;
    }

    @Override
    public String toString() {
        return this.textRepresentation;
    }
}
