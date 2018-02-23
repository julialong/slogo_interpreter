package commands.booleans;

public class NotEqualCommand extends BooleanCommand {

	@Override
	protected boolean calcValue() {
		return getExpr1() != getExpr2();
	}
}
