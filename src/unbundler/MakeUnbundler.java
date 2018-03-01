package unbundler;

import commands.CommandFactory;
import commands.Commandable;
import parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeUnbundler extends ControlUnbundler{

    private List<String> expression;
    private Map<String, String> dictionary;
    private CommandFactory commandFactory;
    private double answer;

    public MakeUnbundler(Map<String, String> dict, CommandFactory cf) {
        dictionary = dict;
        commandFactory = cf;
    }

    /**
     * unbundles the given control command starting at index to crete a new variable
     * @param exp is the entire ArrayList of the input commands
     * @param index is the index that the control command was found
     * @return the string variable name
     */
    public String unbundle(List<String> exp, int index) {
        String variableName = exp.get(index + 1);
        expression = new ArrayList<>();
        buildExpression(exp, index + 1, exp.size());
        executeExpression();
        addVariable(variableName);
        modifyList(exp, index, index + 1);
        return "";
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private void buildExpression(List<String> exp, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            String current = exp.get(i);
            System.out.println(current);
            expression.add(current);
        }
    }

    private void executeExpression() {
        if (expression.size() <= 0) {
            answer = 0;
        } else {
            Iterable<Commandable> iterable = new Parser(commandFactory).parse(String.join(" ", expression));
            for (Commandable c : iterable) {
                c.execute();
                answer = c.getAns();
            }
        }
    }

    /**
     * TODO: Unsure if this correctly modifies map
     * @param variable string variable name
     */
    private void addVariable(String variable) {
        dictionary.put(variable, String.join(" ", Double.toString(answer)));
    }
}
