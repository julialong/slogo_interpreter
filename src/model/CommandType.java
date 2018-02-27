package model;

public enum CommandType {
    COMMAND("Command"),
    ARGUMENT("Argument"),
    CONTROL("Command"),
    HEAD("Head");

    private String textRepresentation;

    CommandType(String command) {
        this.textRepresentation = command;
    }

    @Override
    public String toString() {
        return this.textRepresentation;
    }
}
