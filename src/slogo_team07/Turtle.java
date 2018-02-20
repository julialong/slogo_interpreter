package slogo_team07;

import javafx.scene.paint.Color;

public class Turtle implements Drawable, Updatable {
	
	private double myXPos;
	private double myYPos;
	private boolean tailDown = true;
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public double setPosition(double x, double y) {
		int distance = // calc distance moved;
		myXPos = x;
		myYPos = y;
		return distance
	}

	@Override
	public double move(double delta_x, double delta_y) {
		myXPos += delta_x;
		myYPos += delta_y;
		return (delta_x != 0) ? delta_x : delta_y;
	}

	@Override
	public void changeColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double rotate(double clock, double counter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double setHeading(double degrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double setFacing(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getY() {
		return myYPos;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return myXPos;
	}

}
