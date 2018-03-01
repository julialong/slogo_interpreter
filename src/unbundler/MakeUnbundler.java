package unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeUnbundler extends ControlUnbundler{

    private List<String> expression;
    private Map<String, String> dictionary;

    public MakeUnbundler(Map<String, String> dict) {
        dictionary = dict;
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
        buildExpression(exp, index + 2, exp.size());
        addVariable(variableName);
        modifyList(exp, index, index + 1);
        return String.join(" ", expression);
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private void buildExpression(List<String> exp, int start, int end) {
        for (int i = start; i < end; i++) {
            String current = exp.get(i);
            expression.add(current);
        }
        System.out.println("exprezz " + expression);
    }

    /**
     * TODO: Unsure if this correctly modifies map
     * @param variable string variable name
     */
    private void addVariable(String variable) {
        dictionary.put(variable, String.join(" ", expression));
    }
}
