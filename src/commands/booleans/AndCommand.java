package commands.booleans;

public class AndCommand extends BooleanCommand {
	
	@Override
	protected boolean calcValue() {
		return getExpr1() != 0.0 && getExpr2() != 0.0;
	}
}
