package commands.misc;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsUnfilledException;
import commands.NonUpdatableCommand;
import commands.Result;
import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

public class Function extends NonUpdatableCommand {
	private List<String> myParams;
	private List<String> myCommands;
	private Parser myParser;
	private String myName;

	public Function(Visualizer vis, VariableReplacer var_replacer, Parser parser, String name, List<String> params, List<String> commands) {
		super(vis, var_replacer, params.size());
		myParser = parser;
		myParams = params;
		myCommands = commands;
		myName = name;
	}
	
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(getArgs());
		visCommand(new Result(ans));
		return Double.toString(ans);
	}

	private String replaceParams(List<String> args) {
		List<String> replaced = new ArrayList<>(myCommands);
		for (int i=0; i < myParams.size(); i++) {
			String param = myParams.get(i);
			for (int j=0; j < replaced.size(); j++) {
				String command = replaced.get(j);
				if (command.equals(param)) {
					replaced.set(j, args.get(i));
				}
			}
		}
		args.clear();
		return String.join(" ", replaced);
	}

	@Override
	protected double calcValue(List<String> args) {
		return myParser.parse(replaceParams(args));
	}

	@Override
	public String toString() {
		return String.format("to %s [ %s ] [ %s ]", myName, String.join(" ", myParams), String.join(" ", myCommands));
	}
}
