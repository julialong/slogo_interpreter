package commands.unbundler.multiple_unbundlers;

import commands.VariableReplacer;
import commands.unbundler.ControlUnbundler;
import parser.Parser;
import view.Visualizer;


abstract class MultipleUnbundler extends ControlUnbundler {

    /**
     * Creates a new MultipleControlUnbundler class
     *
     * @param vis     is the current Visualizer class
     * @param numArgs is the number of arguments
     * @param parser  is the current Parser class
     */
    MultipleUnbundler(Visualizer vis, VariableReplacer variableReplacer, int numArgs, Parser parser) {
        super(vis, variableReplacer, numArgs, parser);
    }

}
