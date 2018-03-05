package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ContentReader {

    private String current;

    public ContentReader() {

    }

    public void readFile(Map<String, String> vars, Map<String, Function> funcs, String filename){
        try (Scanner myScanner = processFile(filename)){
            current = myScanner.next();
            if (current.equals("VARIABLES")) {
                readVariables(vars, myScanner);
            }
            if (current.equals("COMMANDS")) {
                readCommands(funcs, myScanner);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readVariables(Map<String,String> vars, Scanner myScanner) {
        while (myScanner.hasNext() && !myScanner.next().equals("COMMANDS")) {
            String[] currentLine = myScanner.nextLine().split(":");
            vars.put(currentLine[0].trim(), currentLine[1].trim());
        }
    }

    private void readCommands(Map<String,Function> funcs, Scanner myScanner) {
        while (myScanner.hasNext()) {
            String[] currentLine = myScanner.nextLine().split(":");
            funcs.put(currentLine[0].trim(), toFunction( currentLine[1]));
        }
    }

    private Function toFunction(String functionAsString) {
        String[] functionComponents = functionAsString.split(" | ");
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList(functionComponents[0].split(" ")));
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(functionComponents[1].split(" ")));
        return new Function(parameters, commands);
    }

    private Scanner processFile(String filename) throws InvalidFileException{
        File toRead = new File(filename);
        try (Scanner myScanner = new Scanner(toRead)) {
            if (!myScanner.hasNext()) {
                throw new InvalidFileException();
            }
            return myScanner;
        }
        catch (Exception e) {
            throw new InvalidFileException();
        }
    }

    public class InvalidFileException extends Exception {

    }
}
