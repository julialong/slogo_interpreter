package slogo_team07;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class Turtle implements Drawable, Updatable {

	private double myXPos;
	private double myYPos;
	private double myPrevXPos;
	private double myPrevYPos;
	private boolean isDown = true;
	private boolean isVisible = true;
	private Double myDegrees = 90.0;
	private ImageView myIV;

	public Turtle(double x, double y){
		myXPos = x;
		myYPos = y;
		myPrevXPos = x;
		myPrevYPos = y;
		Image image = new Image("/view/turtle.jpg");
		myIV = new ImageView(image);
		myIV.setX(myXPos);
		myIV.setY(myYPos);
	}

	public ImageView getView(){
		return myIV;
	}

	public void setView(String imagePath){
		Image image = new Image(imagePath);
		myIV = new ImageView(image);
		myIV.setFitHeight(20);
		myIV.setFitWidth(20);
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
	public Double setPosition(Double x, Double y) {
		Double distance = calcDistance(x, y, myXPos, myYPos); // calc distance moved;
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos = x;
		myYPos = y;
		myIV.setX(x);
		myIV.setY(y);
		return distance;
	}

	@Override
	public Double move(Double pixels) {
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		Double radians = degreesToRadians(myDegrees);
		myXPos += pixels * Math.cos(radians);
		myYPos += pixels * Math.sin(radians);
		return pixels;
	}

	@Override
	public Double home() {
		Double distance = calcDistance(0.0, 0.0, myXPos, myYPos);
		myXPos = 0.0;
		myYPos = 0.0;
		return distance;
	}

	@Override
	public Double rotate(Double clockwise) {
		myDegrees += clockwise;
		return clockwise;
	}

	@Override
	public Double setHeading(Double degrees) {
		Double old = myDegrees;
		myDegrees = degrees;
		return degrees - old;
	}

	// THIS METHOD IS BROKEN....IT'S TOO LATE AND I'M TOO TIRED TO DO THIS
	// MATH
	@Override
	public Double setFacing(Double x, Double y) {
		Double radians = degreesToRadians(myDegrees);
		Point2D.Double old_vec = calcVector(myXPos, 
				myYPos,
				myXPos + Math.cos(radians), 
				myYPos + Math.sin(radians));
		Point2D.Double new_vec = calcVector(0.0, 0.0, x, y);

		Double ans = calcAngle(old_vec, new_vec);
		return ans;
	}

	private Double calcAngle(Point2D.Double old_vec, Point2D.Double new_vec) {
		double numer = old_vec.x * new_vec.x + old_vec.y * new_vec.y;
		double denom = Math.sqrt(old_vec.x * old_vec.x + old_vec.y * old_vec.y)
				* Math.sqrt(new_vec.x * new_vec.x + new_vec.y * new_vec.y);
		return Math.acos(numer / denom);

	}

	@Override
	public Double setVisible(boolean visible) {
		isVisible = visible;
		return isVisible ? 1.0 : 0.0;
	}

	@Override
	public Double getY() {
		return myYPos;
	}

	@Override
	public Double getX() {
		return myXPos;
	}

	@Override
	public Double setPen(boolean down) {
		isDown = down;
		return isDown ? 1.0 : 0.0;
	}

	@Override
	public Double getHeading() {
		return myDegrees;
	}

	@Override
	public Double getPendown() {
		return isDown ? 1.0 : 0.0;
	}

	@Override
	public Double getVisible() {
		return isVisible ? 1.0 : 0.0;
	}

	private Double degreesToRadians(Double degrees) {
		return (degrees * Math.PI) / 180.0;
	}

	private Double calcDistance(Double x1, Double y1, Double x2, Double y2) {
		Double a = Math.abs(x2 - x1);
		Double b = Math.abs(y2 - y1);
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}


	private Point2D.Double calcVector(Double x1, Double y1, Double x2, Double y2) {
		Double x = x2 - x1;
		Double y = y2 - y1;
		return new Point2D.Double(x, y);
	}
}
