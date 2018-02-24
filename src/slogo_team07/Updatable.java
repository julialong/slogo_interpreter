package slogo_team07;

public interface Updatable {
	Double setPosition(Double x, Double y);
	
	Double move(Double delta);
	
	Double rotate(Double clockwise);
	
	Double setHeading(Double degrees);
	
	Double setFacing(Double x, Double y);
	
	Double setVisible(boolean isVisible);
	
	Double getY();
	
	Double getX();
	
	Double getHeading();
	
	Double getPendown();
	
	Double getVisible();
	
	Double home();
	
	Double setPen(boolean down);
}
