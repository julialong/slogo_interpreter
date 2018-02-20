package commands;

import slogo_team07.Turtle;

public class SetXYCommand implements Commandable {
	
	private double myXPos;
	private double myYPos;

	public SetXYCommand(double x, double y) {
		this.myXPos = x;
		this.myYPos = x;
	}
	
	@Override
	public double execute(Turtle turtle) {
		return turtle.setPosition(myXPos, myYPos);
	}
}
