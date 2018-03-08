package parser;

import java.util.ArrayList;
import java.util.List;

import commands.NonUpdatableStringArgs;
import view.Visualizer;

public class Function extends NonUpdatableStringArgs {
	private List<String> myParams;
	private List<String> myCommands;
	private Parser myParser;
	
	public Function(Visualizer vis, Parser parser, List<String> params, List<String> commands) {
		super(vis, params.size());
		myParser = parser;
		myParams = params;
		myCommands = commands;
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
}
