package commands;

import slogo_team07.Argument;

public interface Commandable {
	<T1, T2> Result<?> execute(Argument<T1, T2> argument);
}
