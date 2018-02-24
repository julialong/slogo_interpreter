package commands;

import java.util.List;

import commands.Command;
import commands.CommandArgsUnfilledException;
import commands.Result;
import slogo_team07.Turtle;

public abstract class TurtleCommand extends Command {
	
	private Turtle myTurtle;
	private Double ans;
	
	public TurtleCommand(int num_args, Turtle turtle) {
		super(num_args);
		myTurtle = turtle;
	}
	
	@Override
	public Result execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}
		
		ans = calcValues(myTurtle, getArgs());
		return new Result(ans);
	}
	
	public Double getAns() {
		return ans;
	}

	protected abstract Double calcValues(Turtle turtle, List<Double> args);
}
