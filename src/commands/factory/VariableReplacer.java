package commands.factory;

/**
 * 
 * @author benhubsch
 * 
 *         VariableReplacer specifies an interface that the CommandFactory
 *         implements. The implementation of the method replaces variables with
 *         their true value at execution time. It is the type that all of the
 *         Commands store, preventing them access to the other public methods of
 *         the CommandFactory.
 *
 */
@FunctionalInterface
public interface VariableReplacer {
	String replace(String var);
}
