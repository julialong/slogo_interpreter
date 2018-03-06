package parser;

import java.io.*;
import java.util.Map;

/**
 * Writes the current states of the session to a text file so that they may be read in later
 * Resources used: stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
 * @author julialong
 */
public class ContentWriter {

    private Writer myWriter;

    private static final String VARIABLES = "Variables\n";
    private static final String COMMANDS = "Commands\n";

    private static final String NEWLINE = "\n";
    private static final String COLON = " : ";

    public ContentWriter() {

    }

    /**
     * Writes all of the given variables and functions
     * @param variableMap is the map of variable names to values
     * @param functionMap is the map of function names to values
     * @param filename is the name of the file we want to write to
     */
    public void writeAll(Map<String,String> variableMap, Map<String,Function> functionMap, String filename) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))){
            myWriter = writer;
            writeVariables(variableMap);
            writeCommands(functionMap);
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * writes the given variables to the file
     * @param variableMap is the map of variable names to values
     * @throws IOException if an I/O error occurs
     */
    private void writeVariables(Map<String, String> variableMap) throws IOException {
        myWriter.write(VARIABLES);
        for (String key : variableMap.keySet()) {
            myWriter.write(makeString(key, variableMap.get(key)));
        }
    }

    /**
     * writes the given commands to the file
     * @param functionMap is the map of function names to values
     * @throws IOException if an I/O error occurs
     */
    private void writeCommands(Map<String, Function> functionMap) throws IOException {
        myWriter.write(COMMANDS);
        for (String key : functionMap.keySet()) {
            myWriter.write(makeString(key, functionMap.get(key).toString()));
        }
    }

    /**
     * Formats the key and value of the current entry into a properly formatted string
     * @param key is the key
     * @param value is the value associated with key
     * @return a string with the key and value properly concatenated
     */
    private String makeString(String key, String value) {
        return key + COLON + value + NEWLINE;
    }
}
