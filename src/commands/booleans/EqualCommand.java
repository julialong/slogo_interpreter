package commands.booleans;

public class EqualCommand extends BooleanCommand {
	
	@Override
	protected boolean calcValue() {
		return getExpr1() == getExpr2();
	}
}
