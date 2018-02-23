package commands.booleans;

public class GreaterCommand extends BooleanCommand {
	
	@Override
	protected boolean calcValue() {
		return getExpr1() < getExpr2();
	}
}
