package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import commands.factory.VariableReplacer;
import parser.VariableTruthometer;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * The Class Command.
 *
 * @author benhubsch
 * 
 * This Command class is the building block of every Command in the system. Fundamentally,
 * it holds a list of arguments that get injected into an instance of this class as the
 * input string is parsed. When the Command has been injected with an appropriate number
 * of arguments, it can be executed via a command called execute() that contains different
 * implementations for different kinds of commands.
 */
public abstract class Command implements VariableTruthometer {

	private int myArgsNeeded;
	private List<String> myArgs = new ArrayList<>();
	private Visualizer myVis;
	private VariableReplacer myVariableReplacer;

	/**
	 * Instantiates a new Command object.
	 *
	 * @param vis The Visualizer class that implements the front-end logic. It is used to 
	 * update the front-end when commands execute.
	 * @param var_replacer This parameter allows variables to have their values calculated at 
	 * execution time. It's an interface that the CommandFactory implements.  
	 * @param num_args The number of arguments this object takes.
	 */
	public Command(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		myArgsNeeded = num_args;
		myVis = vis;
		myVariableReplacer = var_replacer;
	}

	/**
	 * Injects arguments into this command object. It's called from Parser.
	 *
	 * @param arg the arg
	 */
	public void inject(String arg) {
		if (isReady()) {
			throw new CommandArgsFullException("This Command object already has a sufficient number of arguments.");
		}

		myArgs.add(arg);
	}

	/**
	 * Checks if the command contains a sufficient number of arguments.
	 *
	 * @return true, if is ready
	 */
	public boolean isReady() {
		return myArgs.size() == myArgsNeeded;
	}

	protected List<String> getArgs() {
		return myArgs;
	}

	protected void visCommand(Result result) {
		myVis.runCommand(result);
	}

	protected List<Double> parseToDouble(List<String> args) {
		return replaceVars(args).stream()
				.map(Double::parseDouble)
				.collect(Collectors.toList());
	}

	protected List<String> replaceVars(List<String> args) {
		List<String> temp = new ArrayList<>();
		for (int i=0; i < args.size(); i++) {
			String curr = args.get(i);
			if (isVariable(curr)) {
				temp.add(myVariableReplacer.replace(curr));
			} else {
				temp.add(curr);
			}
		}
		return temp;
	}

	/**
	 * This is the function that is called when a Command object is ready to be
	 * executed and its value calculated.
	 *
	 * @return String The double result of the calculation in String form.
	 */
	public abstract String execute();

	/**
	 * Checks if this Command object contains an Updatable. This is used to update the
	 * latest current Updatable object in Parser, which is needed to calculate the id.
	 *
	 * @return true, if successful
	 */
	public abstract boolean hasUpdatable();

	/**
	 * Gets the Updatable object.
	 *
	 * @return Updatable
	 */
	public abstract Updatable getUpdatable();
}
