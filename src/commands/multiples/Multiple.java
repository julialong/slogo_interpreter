package commands.multiples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import commands.BracketFinder;
import commands.ListDeleter;
import commands.NonUpdatableStringArgs;
import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public abstract class Multiple extends NonUpdatableStringArgs implements ListDeleter, BracketFinder {
	
	private Parser myParser;
	private Set<String> myActives;
	private Visualizer myVis;
	private Map<String, Updatable> myUpdatables;
	
	public Multiple(Visualizer vis, Parser parser, Set<String> actives, Map<String, Updatable> updatables, int num_args) {
		super(vis, num_args);
		myParser = parser;
		myActives = actives;
		myVis = vis;
		myUpdatables = updatables;
	}
	
	@Override
	protected double calcValue(List<String> args) {
		return manage(argsToList(args));
	}
	
	private List<String> argsToList(List<String> args) {
		List<String> input = new ArrayList<>();
		for (String arg : args) {
			input.addAll(Arrays.asList(arg.split(" ")));
		}
		
		return input;
	}
	
	protected Parser getParser() {
		return myParser;
	}
	
	protected Set<String> getActives() {
		return myActives;
	}
	
	protected Visualizer getVis() {
		return myVis;
	}
	
	protected Map<String, Updatable> getUpdatables() {
		return myUpdatables;
	}
	
	protected abstract double manage(List<String> input);
}
