package parser.unbundler;

import java.util.Map;

import commands.CommandFactory;
import parser.Function;

public class UnbundlerFactory {
	
	private CommandFactory myCommandFactory;
	
	public UnbundlerFactory(CommandFactory cf) {
		myCommandFactory = cf;
	}

	public Unbundler createUnbundler(String control, Map<String, String> var_map, Map<String, Function> func_map) {
		String lower = control.toLowerCase();
		if (lower.equals("repeat")) {
			return new RepeatUnbundler(myCommandFactory);
		} else if (lower.equals("dotimes")) {
			return new DoTimesUnbundler(myCommandFactory);
		} else if (lower.equals("for")) {
			return new ForUnbundler();
		} else if (lower.equals("make") || lower.equals("set")) {
			return new MakeUnbundler(var_map);
		} else if (lower.equals("to")) {
			return new ToUnbundler(func_map);
		} else if (lower.equals("if")) {
			return new IfUnbundler(myCommandFactory);
		} else if (lower.equals("ifelse")) {
			return new IfElseUnbundler(myCommandFactory);
		}
		return null;
	}
}
