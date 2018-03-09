package commands.factory;

import java.util.Arrays;
import java.util.List;

import commands.Command;
import commands.unbundler.DoTimes;
import commands.unbundler.For;
import commands.unbundler.conditional_unbundlers.If;
import commands.unbundler.conditional_unbundlers.IfElse;
import commands.unbundler.MakeUserInstruction;
import commands.unbundler.MakeVariable;
import commands.unbundler.Repeat;

public class UnbundlerFactory implements Factory {
	
	private CommandFactory myCF;
	
	public UnbundlerFactory(CommandFactory command_factory) {
		myCF = command_factory;
	}

	@Override
	public List<Command> create(String keyword) {
		Command command = null;
		if (keyword.equalsIgnoreCase("DoTimes")) {
			command = new DoTimes(myCF.getVis(), myCF, myCF.getParser());
		} else if (keyword.equalsIgnoreCase("For")) {
			command = new For(myCF.getVis(), myCF, myCF.getParser());
		} else if (keyword.equalsIgnoreCase("Repeat")) {
			command = new Repeat(myCF.getVis(), myCF, myCF.getParser());
		} else if (keyword.equalsIgnoreCase("If")) {
			command = new If(myCF.getVis(), myCF, myCF.getParser());
		} else if (keyword.equalsIgnoreCase("IfElse")) {
			command = new IfElse(myCF.getVis(), myCF, myCF.getParser());
		} else if (keyword.equalsIgnoreCase("MakeUserInstruction")) {
			command = new MakeUserInstruction(myCF.getVis(), myCF, myCF.getParser(), myCF.getFuncMap());
		} else if (keyword.equalsIgnoreCase("MakeVariable")) {
			command = new MakeVariable(myCF.getVis(), myCF, myCF.getParser(), myCF.getVarMap());
		} else {
			throw new CommandNotFoundException("Multiple command not found.");
		}
		return Arrays.asList(command);
	}

}
