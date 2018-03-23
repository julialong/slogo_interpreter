package commands.factory;

import java.util.List;

import commands.Command;

/**
 * 
 * @author benhubsch
 * 
 *         This is an abstract interface that all Factory classes must
 *         implement. It allows for polymorphism in the instantiation of new
 *         Command lists given an input in CommandFactory.
 *
 */
public interface Factory {
	List<Command> create(String keyword);
}
