package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import view.Visualizer;

public abstract class Command {
	
	private int myArgsNeeded;
	private List<String> myArgs = new ArrayList<>();
	private Visualizer myVis;

	public Command(Visualizer vis, int num_args) {
		myArgsNeeded = num_args;
		myVis = vis;
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
	
	protected List<Double> getDoubleArgs() {
		return parseToDouble(myArgs);
	}
	
	protected List<String> getStringArgs() {
		return myArgs;
	}
	
	public int getChildren() {
		return myArgsNeeded;
	}
	
	protected void visCommand(Result result) {
		myVis.runCommand(result);
	}

	private List<Double> parseToDouble(List<String> args) {
		return args.stream()
				.map(Double::parseDouble)
				.collect(Collectors.toList());
	}

	public abstract String execute();
}
