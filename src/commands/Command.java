package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import commands.factory.VariableReplacer;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * The Class Command.
 *
 * @author benhubsch
 * 
 *         This Command class is the building block of every Command in the system. Fundamentally,
 *         it holds a collection of arguments that get injected into the class as the input string
 *         is parsed in Parser. When the Command has been injected with an appropriate number of
 *         arguments, it can be executed via the execute() method, which calls the formatArgs()
 *         method. The formatArgs() method is implemented differently for different subclasses of
 *         Command objects. My write up talks in depth about why I believe this is a good bit of
 *         code, so I refer you to that for the full, detailed discussion. There are some finer
 *         details I'd like to point out, however.
 * 
 *         For starters, Command holds relatively few instance variables. For a class as powerful as
 *         this one with such wide ranging responsibilities, having few dependencies is powerful is
 *         powerful. VariableReplacer is an interface that CommandFactory implements, but any module
 *         could come along and implement that interface, and this module would still work. Same
 *         with Visualizer. Furthermore, Command is also an active class that operates on its own
 *         data and exposes a useful API.
 * 
 *         This class has methods that throw runtime exceptions in the event that 1) the execute
 *         function is called without a sufficient number of arguments, which throws a
 *         CommandArgsUnfilledException or 2) too many arguments are injected into the Command, in
 *         which case a CommandArgsFullException is thrown. This makes it much easier to debug this
 *         class, as I learned firsthand. When I was first getting the Parser up and running, those
 *         exceptions gave me a much better idea of where and why I was erroring in my Parser logic.
 * 
 *         Finally, this class makes use of streams in two places, which allows for a more
 *         functional code style than the traditional for-loop. Streams are succinct and more
 *         descriptively express their functionality than operations within a for loop ever could.
 *         There are also a handful of lambdas used in the streams, one of which makes use of a
 *         functional interface that I wrote (VariableReplacer). The lambdas employ a really simple
 *         and readable syntax and, again, are one of the cornerstones of the functional programming
 *         world.
 */
public abstract class Command {

	private int myArgsNeeded;
	private List<String> myArgs = new ArrayList<>();
	private Visualizer myVis;
	private VariableReplacer myVariableReplacer;

	/**
	 * Instantiates a new Command object.
	 *
	 * @param vis The Visualizer class that implements the front-end logic. It is used to update the
	 *        front-end when commands execute.
	 * @param var_replacer This parameter allows variables to have their values calculated at execution
	 *        time. It's an interface that the CommandFactory implements.
	 * @param num_args The number of arguments this object takes.
	 */
	public Command(Visualizer vis, VariableReplacer var_replacer, int num_args) {
		myArgsNeeded = num_args;
		myVis = vis;
		myVariableReplacer = var_replacer;
	}

	/**
	 * This is the function that is called when a Command object is ready to be executed and its value
	 * calculated.
	 *
	 * @return String The double result of the calculation in String form.
	 */
	public String execute() {
		if (!isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		List<String> args = replaceVars(myArgs);
		double ans = formatArgs(args);

		visCommand(new Result(ans));
		return Double.toString(ans);
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

	/**
	 * Gets the Updatable object, if there is one. It's used by the Parser to determine proper id
	 * values.
	 *
	 * @return Updatable
	 */
	public abstract Updatable getUpdatable();

	protected List<String> getArgs() {
		return myArgs;
	}

	protected void visCommand(Result result) {
		myVis.runCommand(result);
	}

	protected List<Double> parseToDouble(List<String> args) {
		return args.stream().map(Double::parseDouble).collect(Collectors.toList());
	}

	private List<String> replaceVars(List<String> args) {
		return args.stream().map(myVariableReplacer::replace).collect(Collectors.toList());
	}

	protected abstract double formatArgs(List<String> args);
}
