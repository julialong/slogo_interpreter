package commands.turtle;

import java.util.ArrayList;
import java.util.List;

import commands.CommandArgsFullException;
import commands.CommandArgsUnfilledException;
import commands.Commandable;
import commands.Result;
import slogo_team07.Engine;
import slogo_team07.Turtle;

public abstract class TurtleCommand implements Commandable {
	
	private int myArgsNeeded;
	private List<Double> myArgs = new ArrayList<>();
	
	public TurtleCommand(int num_args) {
		myArgsNeeded = num_args;
	}
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		Turtle turtle = getTurtle();
		return new Result(calcValues(turtle, myArgs));
	}

	@Override
	public boolean isReady() {
		return myArgs.size() == myArgsNeeded;
	}

	@Override
	public void inject(Double arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}
		
		myArgs.add(arg);
	}
	
	private Turtle getTurtle() {
		return Engine.getInstance().getTurtle().get(0);
	}
	

	protected abstract Double calcValues(Turtle turtle, List<Double> args);
}
