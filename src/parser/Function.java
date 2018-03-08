package parser;

import java.util.ArrayList;
import java.util.List;

import commands.NonUpdatableDoubleArgs;
import view.Visualizer;

public class Function extends NonUpdatableDoubleArgs {
	private List<String> myArgs = new ArrayList<>();
	private List<String> myParams;
	private List<String> myCommands;
	private Parser myParser;
	
	public Function(Parser parser, Visualizer vis, List<String> params, List<String> commands) {
		super(vis, params.size());
		myParams = params;
		myCommands = commands;
	}
	
	@Override
	public void inject(String arg) {
		myArgs.add(arg);
	}
	
	public int numArgs() {
		return myParams.size();
	}
	
	public List<String> replaceParams() {
		List<String> replaced = new ArrayList<>(myCommands);
		for (int i=0; i < myParams.size(); i++) {
			String param = myParams.get(i);
			for (int j=0; j < replaced.size(); j++) {
				String command = replaced.get(j);
				if (command.equals(param)) {
					replaced.set(j, myArgs.get(i));
				}
			}
		}
		myArgs.clear();
		return replaced;
	}

	@Override
	protected double calcValue(List<Double> args) {
		// TODO Auto-generated method stub
		return 0;
	}
}
