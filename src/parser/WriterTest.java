package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WriterTest {
    public static void main(String[] args) {
        Map<String, String> test = new HashMap<>();
        test.put("English", "Hello");
        test.put("Espanol", "Hola");
        test.put("Deutsch", "Hallo");
        test.put("Francais", "Alo");
        ArrayList<String> params = new ArrayList<>();
        ArrayList<String> commands = new ArrayList<>();
        String[] a = {"a","b","c","d"};
        for (String s : a) {
            params.add(s);
        }
        String[] b = {"1","2","3","4"};
        for (String s : b) {
            commands.add(s);
        }
        Function f = new Function(params, commands);
        Map<String, Function> test2 = new HashMap<>();
        test2.put("alpha", f);
        ContentWriter cw = new ContentWriter();
        cw.writeVariables(test);
        cw.writeCommands(test2);

        Map<String, String> testVars = new HashMap<>();
        Map<String, Function> testFunc = new HashMap<>();
        ContentReader cr = new ContentReader();
        String filename = "TEST.txt";
        cr.readFile(testVars, testFunc, filename);
            System.out.println(testVars.toString());
            System.out.println(testFunc.toString());
        }
    }
