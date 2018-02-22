package slogo_team07;

public class Turtle implements Drawable, Updatable {
	
	private double myXPos = 0;
	private double myYPos = 0;
	private double myDegrees = 90;
	private boolean myTailDown = true;
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public double setPosition(double x, double y) {
		double distance = calcDistance(x, y, myXPos, myYPos); // calc distance moved;
		myXPos = x;
		myYPos = y;
		return distance;
	}

	@Override
	public double move(double pixels) {
		double radians = degreesToRadians(myDegrees);
		myXPos += pixels * Math.cos(radians);
		myYPos += pixels * Math.sin(radians);
		return pixels;
	}
	
	@Override
	public double home() {
		double distance = calcDistance(0.0, 0.0, myXPos, myYPos);
		myXPos = 0;
		myYPos = 0;
		return distance;
	}

	@Override
	public double rotate(double clockwise) {
		myDegrees += clockwise;
		return clockwise;
	}

	@Override
	public double setHeading(double degrees) {
		myDegrees = degrees;
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
		return myXPos;
	}
	
	private double degreesToRadians(double degrees) {
		return (degrees * Math.PI) / 180.0;
	}
	

	private double calcDistance(double x1, double y1, double x2, double y2) {
		double a = Math.abs(x2 - x1);
		double b = Math.abs(y2 - y1);
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
}
