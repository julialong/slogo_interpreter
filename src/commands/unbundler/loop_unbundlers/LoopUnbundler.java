package commands.unbundler.loop_unbundlers;


import commands.factory.VariableReplacer;
import commands.unbundler.ControlUnbundler;
import parser.Parser;
import view.Visualizer;


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

}
