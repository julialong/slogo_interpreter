package slogo_team07;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Turtle implements Drawable, Updatable {

	private double myXPos;
	private double myYPos;
	private double myPrevXPos;
	private double myPrevYPos;
	private double myViewX = 0.0;
	private double myViewY = 0.0;
	private double myViewPrevX = 0.0;
	private double myViewPrevY = 0.0;
	private boolean isDown = true;
	private boolean isVisible = true;
	private Double myDegrees = 0.0; //unsure if correct initial value
	private ImageView myIV;
	private Pane myPane;

	public Turtle() {
		Image image = new Image("/view/turtle.jpg");
		myIV = new ImageView(image);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
	}
	
	private void translate(Pane display){
		double height = display.getHeight();
		double width = display.getWidth();
		myViewX = myXPos + width/2;
		myViewY = -1 * (myYPos - height/2);
		myViewPrevX = myPrevXPos + width/2;
		myViewPrevY = -1 * (myPrevYPos  - height/2);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
	}
	
	public void setPane(Pane pane){
		myPane = pane;
	}

	@Override
	public boolean getIsVisible(){
		return isVisible;
	}
	
	@Override
	public ImageView getView(){
		return myIV;
	}

	@Override
	public void setView(String imagePath){
		Image image = new Image(imagePath);
		myIV = new ImageView(image);
		myIV.setFitHeight(20);
		myIV.setFitWidth(20);
		translate(myPane);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
	}

	@Override
	public void draw(Pane display, Color color) {
		myPane = display;
		translate(myPane);
		if (isDown){
			Line trail = new Line(myViewPrevX, myViewPrevY, myViewX, myViewY);
			trail.setStroke(color);
			display.getChildren().add(trail);
		}
	}
	
	@Override
	public void test(double x, double y){
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos = x;
		myYPos = y;
		translate(myPane);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
	}

	@Override
	public Double setPosition(Double x, Double y) {
		Double distance = calcDistance(x, y, myXPos, myYPos); // calc distance moved;
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos = x;
		myYPos = y;
		translate(myPane);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
		return distance;
	}

	@Override
	public Double move(Double pixels) {
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		Double radians = degreesToRadians(myDegrees);
		myXPos += pixels * Math.cos(radians);
		myYPos += pixels * Math.sin(radians);
		translate(myPane);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
		return pixels;
	}

	@Override
	public Double home() {
		Double distance = calcDistance(0.0, 0.0, myXPos, myYPos);
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos = 0.0;
		myYPos = 0.0;
		translate(myPane);
		return distance;
	}

	@Override
	public Double rotate(Double clockwise) {
		myDegrees += clockwise;
		myIV.setRotate(myDegrees);
		return clockwise;
	}

	@Override
	public Double setHeading(Double degrees) {
		Double old = myDegrees;
		myDegrees = degrees;
		myIV.setRotate(myDegrees);
		return degrees - old;
	}

	// THIS METHOD IS BROKEN....IT'S TOO LATE AND I'M TOO TIRED TO DO THIS
	// MATH
	@Override
	public Double setFacing(Double x, Double y) {
		Double radians = degreesToRadians(myDegrees);
		Point2D old_vec = calcVector(myXPos, 
				myYPos,
				myXPos + Math.cos(radians), 
				myYPos + Math.sin(radians));
		Point2D new_vec = calcVector(0.0, 0.0, x, y);

		Double ans = calcAngle(old_vec, new_vec);
		//need to add imageview rotation
		return ans;
	}

	private Double calcAngle(Point2D old_vec, Point2D new_vec) {
		double numer = old_vec.getX() * new_vec.getX() + old_vec.getY() * new_vec.getY();
		double denom = Math.sqrt(old_vec.getX() * old_vec.getX() + old_vec.getY() * old_vec.getY())
				* Math.sqrt(new_vec.getX() * new_vec.getX() + new_vec.getY() * new_vec.getY());
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
	
	@Override
	public Double clear() {
		Double dist = this.home();
		myPane.getChildren().clear();
		myIV.setFitHeight(20);
		myIV.setFitWidth(20);
		myPane.getChildren().add(myIV);
		return dist;
	}
	
	private Double degreesToRadians(Double degrees) {
		return (degrees * Math.PI) / 180.0;
	}

	private Double calcDistance(Double x1, Double y1, Double x2, Double y2) {
		Double a = Math.abs(x2 - x1);
		Double b = Math.abs(y2 - y1);
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}


	private Point2D calcVector(Double x1, Double y1, Double x2, Double y2) {
		Double x = x2 - x1;
		Double y = y2 - y1;
		return new Point2D(x, y);
	}
}
