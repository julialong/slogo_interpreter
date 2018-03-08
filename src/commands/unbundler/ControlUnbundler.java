package commands.unbundler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.BracketFinder;
import commands.ListModifier;
import commands.NonUpdatableStringArgs;
import parser.Parser;
import view.Visualizer;

public abstract class ControlUnbundler extends NonUpdatableStringArgs implements ListModifier, BracketFinder {
	
	private Parser myParser;

	ControlUnbundler(Visualizer vis, int numArgs, Parser parser) {
		super(vis, numArgs);
		myParser = parser;
	}
	
	protected List<String> argsToExp(List<String> args) {
		List<String> exp = new ArrayList<>();
		for (String arg : args) {
			exp.addAll(Arrays.asList(arg.split(" ")));
		}
		return exp;
	}
	
	Parser getMyParser() {
		return myParser;
	}

	@Override
	protected double calcValue(List<String> args) {
		String unbundled = unbundle(argsToExp(args));
		System.out.println("unbundled: " + unbundled);
		return myParser.parse(unbundled);
	}
	
	protected abstract String unbundle(List<String> exp);

}
