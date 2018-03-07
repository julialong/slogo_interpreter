package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WriterTest {
    public static void main(String[] args) {
        Map<String, String> test = new HashMap<>();
        test.put(":x", "30");
        test.put(":y", "400");
        test.put(":z", "2");
        test.put(":w", "5");
        String[] a = {":a",":b"};
        ArrayList<String> params = new ArrayList<>(Arrays.asList(a));
        String[] b = {"sum :a :b","product :b :a"};
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(b));
        Function f = new Function(params, commands);
        Map<String, Function> test2 = new HashMap<>();
        test2.put("alpha", f);
        ContentWriter cw = new ContentWriter();
        cw.writeAll(test, test2, "data/TEST.txt");

        Map<String, String> testVars = new HashMap<>();
        Map<String, Function> testFunc = new HashMap<>();
        ContentReader cr = new ContentReader();
        String filename = "TEST.txt";
        try {
            cr.readFile(testVars, testFunc, filename);
            System.out.println(testVars.toString());
            System.out.println(testFunc.toString());
        }
        catch(Exception e) {
            System.out.println("Bad file.");
        }
        }
    }
