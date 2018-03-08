package file_managers;

import java.io.*;
import java.util.List;

/**
 * Writes the current states of the session to a text file so that they may be read in later
 * Resources used: stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
 * @author julialong
 */
public class FileWriter {

    Writer myWriter;

    /**
     * Creates a new instance of the FileWriter object
     */
    public FileWriter() {

    }

    /**
     * Writes a List of Strings to a file that can be read back in later.
     * @param filename is the name of the file to be written
     * @param type is the type of the file
     * @param toWrite is the list of strings to write
     * @throws IOException when input is invalid
     */
    public void writeToFile(String filename, String type, List<String> toWrite) throws IOException {
        try {
            myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
            myWriter.write(type);
            writeList(toWrite);
            myWriter.close();
        }
        catch (Exception e) {
            throw new IOException("Bad input");
        }
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
