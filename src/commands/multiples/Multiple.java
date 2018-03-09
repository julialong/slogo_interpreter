package commands.multiples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.BracketFinder;
import commands.ListModifier;
import commands.NonUpdatableCommand;
import view.Visualizer;

public abstract class Multiple extends NonUpdatableCommand implements ListModifier, BracketFinder {
	
	public Multiple(Visualizer vis, int num_args) {
		super(vis, num_args);
	}
	
	protected List<String> argsToList(List<String> args) {
		List<String> input = new ArrayList<>();
		for (String arg : args) {
			input.addAll(Arrays.asList(arg.split(" ")));
		}
		
		return input;
	}
	
}
