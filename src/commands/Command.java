package commands;

import java.util.ArrayList;
import java.util.List;

import view.Visualizer;

public abstract class Command implements Commandable {
	
	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();
	private Visualizer myVis;

	public Command(Visualizer vis, int num_args) {
		myArgsNeeded = num_args;
		myVis = vis;
	}

	@Override
	public void inject(double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}

		myArgs.add(arg);
	}

	public boolean isReady() {
		return myArgs.size() == myArgsNeeded;
	}
	
	protected List<Double> getArgs() {
		return myArgs;
	}
	
	public int getChildren() {
		return myArgsNeeded;
	}
	
	protected void visCommand(Result result) {
		System.out.println("Visualizing: " + result.toString());
//		myVis.runCommand(result);
	}

}
