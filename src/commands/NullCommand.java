package commands;

public class NullCommand extends Command {
	
	public NullCommand() {
		super(Integer.MAX_VALUE);
	}

	@Override
	public Result execute() {
		return new Result(null);
	}
	
	@Override 
	public boolean isReady() {
		return false;
	}

	@Override
	public Double getAns() {
		return null;
	}
}
