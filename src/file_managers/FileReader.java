package file_managers;

import view.Console;

import java.util.Scanner;

/**
 * Reads in a written list of commands, variables, and functions for use in a SLogo session.
 * Resources used: https://txt2re.com/index-java.php3
 * @author julialong
 */
public class FileReader {

    private Console myConsole;
    private String commandsToSend;

    /**
     * Creates a new instance of the FileReader object
     */
    public FileReader(Console console) {
        myConsole = console;
    }

    /**
     * Reads in the current file and modifies the given variable and function maps
     * @param filename is the name of the file that we want to read in
     * @throws InvalidFileException when the file filename is not correctly formatted
     */
    public void readFile(String filename) throws InvalidFileException {
        try {
            Scanner myScanner = new Scanner(getClass().getClassLoader().getResourceAsStream(filename));
            StringBuilder newCommands = new StringBuilder();
            while (myScanner.hasNext()) {
                newCommands.append(myScanner.next());
            }
            commandsToSend = newCommands.toString();
            // TODO: send commands to console
            sendToConsole();
        }
        catch (Exception e) {
            throw new InvalidFileException();
        }
    }

    /**
     * Sends commands to console to be executed
     */
    private void sendToConsole() {
        myConsole.loadInput(commandsToSend);
        myConsole.run();
    }

    /**
     * Thrown when we encounter an invalid file.
     */
    public class InvalidFileException extends Exception {

    }
}
