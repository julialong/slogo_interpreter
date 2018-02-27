package model.Unbundlers;

import java.util.Map;
import model.SyntaxNode;
import model.Parser;

public class RepeatUnbundler {

    Map<String, String> dictionary;
    Parser pr;

    RepeatUnbundler(Parser p, Map<String,String> variables, String[] exp, String[] command) {
        pr = p;
        Double value = pr.parse(String.join(" ", exp));

    }
}
