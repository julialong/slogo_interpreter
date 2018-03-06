package parser;

import java.util.*;

/**
 * Reads in a written list of variables and functions for use in a SLogo session.
 * Resources used: https://txt2re.com/index-java.php3
 * @author julialong
 */
public class ContentReader {

    private String current;
    private Scanner myScanner;

    private static final String VARIABLES = "Variables";
    private static final String COMMANDS = "Commands";

    public ContentReader() {

    }

    /**
     * Reads in the current file and modifies the given variable and function maps
     * @param variableMap is the map of variable names to values
     * @param functionMap is the map of function names to values
     * @param filename is the name of the file that we want to read in
     * @throws InvalidFileException when the file filename is not correctly formatted
     */
    public void readFile(Map<String, String> variableMap, Map<String, Function> functionMap, String filename) throws InvalidFileException{
        try {
            myScanner = new Scanner(getClass().getClassLoader().getResourceAsStream(filename));
            current = myScanner.next();
            if (current.equals(VARIABLES)) {
                readVariables(variableMap);
            }
            if (current.equals(COMMANDS)) {
                readCommands(functionMap);
            }
        }
        catch (Exception e) {
            throw new InvalidFileException();
        }
    }

    /**
     * Reads the variables from the scanner and adds them to the map
     * @param variableMap is the map of variable names to values
     */
    private void readVariables(Map<String,String> variableMap) {
        current = myScanner.next();
        while (myScanner.hasNext() && !current.equals(COMMANDS)) {
            String key = current;
            myScanner.next();
            String value = myScanner.next();
            variableMap.put(key, value);
            current = myScanner.next();
        }
    }

    /**
     * Reads the functions from the scanner and adds them to the map
     * @param functionMap is the map of function names to values
     */
    private void readCommands(Map<String,Function> functionMap) throws InvalidFileException{
        while (myScanner.hasNext()) {
            current = myScanner.next();
            String key = current;
            myScanner.next();
            String value = myScanner.nextLine();
            functionMap.put(key, toFunction(value));
        }
    }

    /**
     * Converts a string into a function if that string has the proper format. If not, throws InvalidFormatException
     * @param functionAsString is the string containing the parameters and commands for the function
     * @return the Function created from the given string
     */
    private Function toFunction(String functionAsString) throws InvalidFileException{
        String[] functionComponents = functionAsString.split(" (\\|) ");
        if (functionComponents.length != 2) {
            throw new InvalidFileException();
        }
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList(functionComponents[0].split(" ")));
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(functionComponents[1].split(" ")));
        return new Function(parameters, commands);
    }

    /**
     * Thrown when we encounter an invalid file.
     */
    public class InvalidFileException extends Exception {

    }
}
