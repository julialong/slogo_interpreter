package parser;

public interface VariableTruthometer {
	default boolean isVariable(String string) {
		return string.matches(":[a-zA-Z]+");
	}
}
