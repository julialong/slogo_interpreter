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
	
	double getHeading();
	
	double getPendown();
	
	double getVisible();
	
	double home();
	
<<<<<<< HEAD
	double setPenDown(boolean down);
=======
	double setPen(boolean down);
>>>>>>> ben
	
	double clear();
	
	double getId();

	double getShape();

	double getPenColor();

<<<<<<< HEAD
	double setPenWidth(double pixels);
=======
	double setPenSize(double pixels);
>>>>>>> ben

	double setPenColor(int dex);

	double setShape(int dex);
<<<<<<< HEAD

	double setPalette(int dex, double r, double g, double b);
=======
>>>>>>> ben
}
