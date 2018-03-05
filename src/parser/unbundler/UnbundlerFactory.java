package parser.unbundler;

import java.util.Map;

import commands.CommandFactory;
import parser.Function;
import parser.Parser;

public class UnbundlerFactory {
	
	private CommandFactory myCommandFactory;
	private Parser myParser;
	
	public UnbundlerFactory(CommandFactory cf, Parser p) {
		myCommandFactory = cf;
		myParser = p;
	}

	public Unbundler createUnbundler(String control, Map<String, String> var_map, Map<String, Function> func_map) {
		String lower = control.toLowerCase();
		if (lower.equals("repeat")) {
			return new RepeatUnbundler(myParser);
		} else if (lower.equals("dotimes")) {
			return new DoTimesUnbundler(myParser);
		} else if (lower.equals("for")) {
			return new ForUnbundler();
		} else if (lower.equals("make") || lower.equals("set")) {
			return new MakeUnbundler(var_map);
		} else if (lower.equals("to")) {
			return new ToUnbundler(func_map);
		} else if (lower.equals("if")) {
			return new IfUnbundler(myParser);
		} else if (lower.equals("ifelse")) {
			return new IfElseUnbundler(myParser);
		}
		return null;
	}
}
