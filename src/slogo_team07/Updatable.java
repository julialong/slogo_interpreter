package slogo_team07;

public interface Updatable {
	double setPosition(double x, double y);
	
	double move(double delta);
	
	double rotate(double clockwise);
	
	double setHeading(double degrees);
	
	double setFacing(double x, double y);
	
	double setVisible(boolean isVisible);
	
	double getY();
	
	double getX();
	
	double home();
}
