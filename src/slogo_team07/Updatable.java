package slogo_team07;

import javafx.scene.paint.Color;

public interface Updatable {
	void setPosition(double x, double y);
	
	void move(double delta_x, double delta_y);
	
	void changeColor(Color color);
	
	void rotate(double clock, double counter);
	
	void setHeading(double degrees);
	
	void setFacing(double x, double y);
	
	void setVisible(boolean isVisible);
}
