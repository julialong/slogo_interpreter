package parser.unbundler;

import parser.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToUnbundler extends ControlUnbundler{

    private String commandName;
    private List<String> parameters;
    private List<String> commands;
    private Map<String, Function> dictionary;

    public ToUnbundler(Map<String, Function> dict) {
        dictionary = dict;
    }

    /**
     * unbundles the given control command starting at index to crete a new variable
     * @param exp is the entire ArrayList of the input commands
     * @param index is the index that the control command was found
     * @return the string variable name
     */
    public String unbundle(List<String> exp, int index) {
        commandName = exp.get(index + 1);
        int[] variableIndex = findBrackets(exp, index + 2);
        int[] commandIndex = findBrackets(exp, variableIndex[1]);
        parameters = new ArrayList<>();
        commands = new ArrayList<>();
        buildExpression(exp, commandIndex[0], commandIndex[1]);
        addVariables(exp, variableIndex[0], variableIndex[1]);
        addFunction(commandName);
        modifyList(exp, index, commandIndex[1]);
        return Integer.toString(1);
    }

    private void addVariables(List<String> exp, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            String current = exp.get(i);
            parameters.add(current);
        }
    }

    /**
     * Builds the expression to be evaluated
     * @param exp is the entire ArrayList of the input commands
     * @return the index of the first left bracket
     */
    private void buildExpression(List <String> exp, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            String current = exp.get(i);
            commands.add(current);
        }
    }

    /**
     * TODO: Unsure if this correctly modifies map
     * @param variable string variable name
     */
    private void addFunction(String variable) {
        dictionary.put(commandName, new Function(parameters, commands));
    }


}
