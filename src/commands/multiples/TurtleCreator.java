package commands.multiples;

import java.util.List;
import java.util.Map;

import commands.factory.VariableReplacer;
import slogo_team07.Turtle;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * 
 * @author benhubsch
 * 
 * This is an abstract class that both Tell and Ask implement and abstracts out
 * the main concepts that exist surrounding those two Commands.
 *
 */
public abstract class TurtleCreator extends Multiple {
	
	private Visualizer myVis;
	private Map<String, Updatable> myUpdatables;
	private List<String> myActives;
	private VariableReplacer myVariableReplacer;

	public TurtleCreator(Visualizer vis, VariableReplacer var_replacer, int num_args, List<String> actives, Map<String, Updatable> updatables) {
		super(vis, var_replacer, num_args);
		myVis = vis;
		myUpdatables = updatables;
		myActives = actives;
		myVariableReplacer = var_replacer;
	}
	
	protected void addTurtle(String id) {
		Turtle turtle = new Turtle(id);
		myUpdatables.put(id, turtle);
		myVis.addDrawable(turtle);
	}
	
	protected double activate(List<String> replace) {
		double num = 0;
		myActives.clear();
		for (String id : replace) {
			if (isVariable(id)) {
				id = myVariableReplacer.replace(id);
			}
			num = Double.parseDouble(id);
			String id_string = Integer.toString((int) num);
			if (!myUpdatables.containsKey(id_string)) {
				addTurtle(id_string);
			}
			myActives.add(id_string);
		}
		return num;
	}
}
