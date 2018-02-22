package slogo_team07;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import view.Canvas;

public class Turtle implements Drawable, Updatable {
	
	private double myXPos;
	private double myYPos;
	private double myPrevXPos;
	private double myPrevYPos;
	private boolean tailDown = true;
	public ImageView myIV;
	
	public Turtle(double x, double y){
		myXPos = x;
		myYPos = y;
		myPrevXPos = x;
		myPrevYPos = y;
		Image image = new Image("turtle.jpg");
		myIV = new ImageView(image);
		myIV.setX(myXPos);
		myIV.setY(myYPos);
	}
	
	@Override
	public Group draw(Group display) {
		Line trail = new Line(myPrevXPos, myPrevYPos, myXPos, myYPos);
		display.getChildren().add(myIV);
		display.getChildren().add(trail);
		return display;
	}
	
	@Override
	public double setPosition(double x, double y) {
		int distance = 0; // calc distance moved;
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos = x;
		myYPos = y;
		myIV.setX(x);
		myIV.setY(y);
		return distance;
	}

	@Override
	public double move(double delta_x, double delta_y) {
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos += delta_x;
		myYPos += delta_y;
		return (delta_x != 0) ? delta_x : delta_y;
	}

	@Override
	public double rotate(double clock, double counter) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double setHeading(double degrees) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double setFacing(double x, double y) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double setVisible(boolean isVisible) {
		// TODO Auto-generated method stub
		return 0.0;
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
