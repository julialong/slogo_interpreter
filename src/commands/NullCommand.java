package commands;

public class NullCommand implements Commandable {

	@Override
	public Result execute() {
		// do nothing
		return new Result(null);
	}

}
