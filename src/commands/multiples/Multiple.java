package commands.multiples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.BracketFinder;
import commands.ListModifier;
import commands.NonUpdatableCommand;
import commands.factory.VariableReplacer;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 * This is an abstract class for commands specific to the use of multiple turtles. It implements
 * the default methods in the ListModifier and BracketFinder interfaces.
 *
 */
public abstract class Multiple extends NonUpdatableCommand implements ListModifier, BracketFinder {

	public Multiple(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		super(vis, var_replacer, num_args);
	}

	protected List<String> argsToList(List<String> args) {
		List<String> input = new ArrayList<>();
		for (String arg : args) {
			input.addAll(Arrays.asList(arg.split(" ")));
		}

		return input;
	}

}
