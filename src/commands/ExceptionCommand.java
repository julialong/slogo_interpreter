package commands;

public class ExceptionCommand extends Command {
	
	public ExceptionCommand() {
		super(0);
	}

	@Override
	public Result execute() {
		return new Result(Double.MAX_VALUE);
	}

	@Override
	public double getAns() {
		return 0;
	}
}
