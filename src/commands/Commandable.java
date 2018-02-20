package commands;

import slogo_team07.Argument;

public interface Commandable {
	Result<?> execute(Argument<?, ?> argument);
}
