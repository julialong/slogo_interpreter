package parser;

import java.util.HashMap;
import java.util.Map;

public class WriterTest {
    public static void main(String[] args) {
        Map<String, String> test = new HashMap<>();
        test.put("English", "Hello");
        test.put("Espanol", "Hola");
        test.put("Deutsch", "Hallo");
        test.put("Francais", "Alo");
        ContentWriter cw = new ContentWriter();
        cw.writeVariables(test);
    }
}
