package commands.unbundler.loop_unbundlers;


import commands.factory.VariableReplacer;
import commands.unbundler.ControlUnbundler;
import parser.Parser;
import view.Visualizer;

import java.util.List;


abstract class LoopUnbundler extends ControlUnbundler {

    /**
     * Creates a new MultipleControlUnbundler class
     *
     * @param vis     is the current Visualizer class
     * @param numArgs is the number of arguments
     * @param parser  is the current Parser class
     */
    LoopUnbundler(Visualizer vis, VariableReplacer variableReplacer, int numArgs, Parser parser) {
        super(vis, variableReplacer, numArgs, parser);
    }

    /**
     *
     * @param expression is the List of the expression to execute
     * @return the value of the expression
     */
    double executeExpression(List<String> expression) {
        double answer = 0;
        if (expression.size() > 1){
            answer = getParser().parse(String.join(" ", expression));
        }
        else {
            answer = Double.parseDouble(expression.get(0));
        }
        return answer;
    }

}
