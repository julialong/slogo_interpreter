package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import slogo_team07.Updatable;
import view.Visualizer;

public abstract class Command {
	
	private int myArgsNeeded;
	private List<String> myArgs = new ArrayList<>();
	private Visualizer myVis;
	private VariableReplacer myVariableReplacer;

	public Command(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		myArgsNeeded = num_args;
		myVis = vis;
		myVariableReplacer = var_replacer;
	}

	public void inject(String arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}

		myArgs.add(arg);
	}

	public boolean isReady() {
		return myArgs.size() == myArgsNeeded;
	}
	
	protected List<String> getArgs() {
		return myArgs;
	}

	public int getChildren() {
		return myArgsNeeded;
	}
	
	protected void visCommand(Result result) {
		myVis.runCommand(result);
	}

	protected List<Double> parseToDouble(List<String> args) {
		return replaceVars(args).stream()
				.map(Double::parseDouble)
				.collect(Collectors.toList());
	}
	
	protected List<String> replaceVars(List<String> args) {
		List<String> temp = new ArrayList<>();
		for (int i=0; i < args.size(); i++) {
			String curr = args.get(i);
			if (isVariable(curr)) {
				temp.add(myVariableReplacer.replace(curr));
			} else {
				temp.add(curr);
			}
		}
		return temp;
	}
	
	private boolean isVariable(String string) {
		return string.matches(":[a-zA-Z]+");
	}

	public abstract String execute();
	
	public abstract boolean hasUpdatable();

	public abstract Updatable getUpdatable();
}
