package slogo_team07;

import javafx.scene.paint.Color;

public class Turtle implements Updatable {
	
	private double myXPos;
	private double myYPos;
	
	@Override
	public void setPosition(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double delta_x, double delta_y) {
		myXPos += delta_x;
		myYPos += delta_y;
	}

	@Override
	public void changeColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(double clock, double counter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeading(double degrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFacing(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

}
