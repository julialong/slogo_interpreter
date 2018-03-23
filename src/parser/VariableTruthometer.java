package parser;

/**
 * 
 * @author benhubsch
 * 
 *         This is a really simple interface that implements a single default
 *         method that returns a boolean specifying where a given input is a
 *         variable or not. Several distinct classes use this method, so it made
 *         sense to farm it out to a default method for code re-use.
 *
 */
public interface VariableTruthometer {
	default boolean isVariable(String string) {
		return string.matches(":[a-zA-Z]+");
	}
}
