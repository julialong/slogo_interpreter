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
	
	private static final String LEFT_BRACE = "[";
	private static final String RIGHT_BRACE = "]";
	
	private Parser parser;

	public ControlUnbundler(Visualizer vis, int num_args, Parser p) {
		super(vis, num_args);
		parser = p;
	}

	/**
	 * @param current is the current string
	 * @return true if the current string is not a left bracket, false otherwise
	 */
	protected boolean notLeftBracket (String current){
		return !current.equals(LEFT_BRACE);
	}

	/**
	 * @param current is the current string
	 * @return true if the current string is not a right bracket, false otherwise
	 */
	protected boolean notRightBracket (String current){
		return !current.equals(RIGHT_BRACE);
	}
	
	protected List<String> argsToExp(List<String> args) {
		List<String> exp = new ArrayList<>();
		for (String arg : args) {
			exp.addAll(Arrays.asList(arg.split(" ")));
		}
		
		return exp;
	}
	
	protected Parser getParser() {
		return parser;
	}

	@Override
	protected double calcValue(List<String> args) {
		String unbundled = unbundle(argsToExp(args));
		return parser.parse(unbundled);
	}
	
	protected abstract String unbundle(List<String> exp);
}