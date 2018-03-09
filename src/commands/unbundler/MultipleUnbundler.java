package commands.unbundler;

import parser.Parser;
import view.Visualizer;


abstract class MultipleUnbundler extends ControlUnbundler{

    /**
     * Creates a new MultipleControlUnbundler class
     *
     * @param vis     is the current Visualizer class
     * @param numArgs is the number of arguments
     * @param parser  is the current Parser class
     */
    MultipleUnbundler(Visualizer vis, int numArgs, Parser parser) {
        super(vis, numArgs, parser);
    }

}
