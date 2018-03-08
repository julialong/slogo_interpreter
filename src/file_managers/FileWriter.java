package file_managers;

import javafx.scene.control.TextInputDialog;
import view.SideBar;

import java.io.*;
import java.util.List;
import java.util.Optional;

/**
 * Writes the current states of the session to a text file so that they may be read in later
 * Resources used: stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
 * @author julialong
 */
public class FileWriter {

    private Writer myWriter;
    private SideBar mySideBar;

    /**
     * Creates a new instance of the FileWriter object
     */
    public FileWriter(SideBar sideBar) {
        mySideBar = sideBar;
    }

    /**
     * Writes a List of Strings to a file that can be read back in later.
     * @param filename is the name of the file to be written
     * @throws IOException when input is invalid
     */
    public void writeToFile(String filename) throws IOException {
            myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
            writeCommands();
            writeVariables();
            myWriter.close();
    }

    /**
     * Writes commands taken from the sidebar to the given file
     * @throws IOException when input is invalid
     */
    private void writeCommands() throws IOException {
        myWriter.write("Commands\n");
        List<String> commands = mySideBar.exportCommands();
        writeList(commands);
    }

    /**
     * Writes variables taken from the sidebar to the given file
     * @throws IOException when input is invalid
     */
    private void writeVariables() throws IOException {
        myWriter.write("Variables\n");
        List<String> variables = mySideBar.exportVariables();
        writeList(variables);
    }

    /**
     * Writes the Strings in the List with myWriter
     * @param toWrite is the list of strings to write
     * @throws IOException when input in toWrite is invalid
     */
    private void writeList(List<String> toWrite) throws IOException{
        for (String current : toWrite) {
            myWriter.write(current + "\n");
        }
    }

}
