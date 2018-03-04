package parser;

import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Writes the current states of the session to a text file so that they may be read in later
 * Resources used: stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
 * @author julialong
 */
public class ContentWriter {

    public ContentWriter() {

    }

    public void writeVariables(Map<String, String> vars) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TEST.txt"), "utf-8"))){
            writer.write("VARIABLES\n");
            for (String key : vars.keySet()) {
                writer.write(makeString(key, vars.get(key)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeCommands(Map<String, Function> funcs) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TEST2.txt"), "utf-8"))){
            writer.write("FUNCTIONS\n");
            for (String key : funcs.keySet()) {
                writer.write(makeString(key, funcs.get(key).toString()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String makeString(String key, String value) {
        return key + " : " + value + "\n";
    }
}
