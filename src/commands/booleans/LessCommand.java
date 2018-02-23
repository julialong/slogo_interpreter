package commands.booleans;

public class LessCommand extends BooleanCommand {

	@Override
	protected boolean calcValue() {
		return getExpr1() < getExpr2();
	}
}
