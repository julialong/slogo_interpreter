package commands.unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeUnbundler extends ControlUnbundler{

    private List<String> expression;
    private Map<String, String> dictionary;;

        public MakeUnbundler(Map<String, String> dict) {
            dictionary = dict;
        }

        /**
         * unbundles the given control command starting at index to crete a new variable
         * @param exp is the entire ArrayList of the input commands
         * @return the string variable name
         */
        public String unbundle(List<String> exp) {
            String variableName = exp.get(1);
            expression = new ArrayList<>();
            buildExpression(exp, 2, exp.size() - 1);
            addVariable(variableName);
            modifyList(exp, 1);
            return String.join(" ", expression);
        }

        /**
         * Builds the expression to be evaluated
         * @param exp is the entire ArrayList of the input commands
         * @return the index of the first left bracket
         */
        private void buildExpression(List<String> exp, int start, int end) {
            for (int i = start + 1; i < end; i++) {
                String current = exp.get(i);
                expression.add(current);
            }
        }

        /**
         * TODO: Unsure if this correctly modifies map
         * @param variable string variable name
         */
        private void addVariable(String variable) {
            dictionary.put(variable, String.join(" ", expression));
        }
    }

