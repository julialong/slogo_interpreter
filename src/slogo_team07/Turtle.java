package slogo_team07;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Group;
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
	private double myDegrees = 90.0;
	private double myIVDegrees = myDegrees - 90;
	private ImageView myIV;
	private Pane myPane;
	private Group myLines = new Group();
	private Color myColor = Color.BLACK;
	private double myId;
	private double myPenWidth = 1.0;

	public Turtle(String id) {
		myId = Double.parseDouble(id);
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
	public boolean getIsDown(){
		return isDown;
	}

	@Override
	public double getPenWidth()	{
		return myPenWidth;
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
	public void draw(Pane display, Color color, double penWidth) {
		myPenWidth = penWidth;
		myColor = color;
		myPane = display;
		translate(myPane);
		if (isDown){
			if (myPane.getChildren().contains(myLines)){
				myPane.getChildren().remove(myLines);
			}
			Line trail = new Line(myViewPrevX, myViewPrevY, myViewX, myViewY);
			trail.setStroke(myColor);
			trail.setStrokeWidth(myPenWidth);
			myLines.getChildren().add(trail);
			myPane.getChildren().add(myLines);
		}
	}


	@Override
	public double setPosition(double x, double y) {
		double distance = calcDistance(x, y, myXPos, myYPos); // calc distance moved;
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
	public double move(double pixels) {
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		double radians = degreesToRadians(myDegrees);
		myXPos += pixels * Math.cos(radians);
		myYPos += pixels * Math.sin(radians);
		translate(myPane);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
		return pixels;
	}

	@Override
	public double home() {
		double distance = calcDistance(0.0, 0.0, myXPos, myYPos);
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myXPos = 0.0;
		myYPos = 0.0;
		myDegrees = 90.0;
		myIVDegrees = myDegrees - 90;
		myIV.setRotate(myIVDegrees);
		translate(myPane);
		return distance;
	}

	@Override
	public double rotate(double clockwise) {
		myDegrees += clockwise;
		myIVDegrees -= clockwise;
		myIV.setRotate(myIVDegrees);
		return Math.abs(clockwise);
	}

	@Override
	public double setHeading(double degrees) {
		double old = myDegrees;
		myDegrees = degrees;
		myIVDegrees = degrees + 90;
		myIV.setRotate(myIVDegrees);
		return degrees - old;
	}

	// THIS METHOD IS BROKEN....IT'S TOO LATE AND I'M TOO TIRED TO DO THIS
	// MATH
	@Override
	public double setFacing(double x, double y) {
		double radians = degreesToRadians(myDegrees);
		Point2D old_vec = calcVector(myXPos, 
				myYPos,
				myXPos + Math.cos(radians), 
				myYPos + Math.sin(radians));
		Point2D new_vec = calcVector(0.0, 0.0, x, y);

		double ans = calcAngle(old_vec, new_vec);
		//need to add imageview rotation
		return ans;
	}

	private double calcAngle(Point2D old_vec, Point2D new_vec) {
		double numer = old_vec.getX() * new_vec.getX() + old_vec.getY() * new_vec.getY();
		double denom = Math.sqrt(old_vec.getX() * old_vec.getX() + old_vec.getY() * old_vec.getY())
				* Math.sqrt(new_vec.getX() * new_vec.getX() + new_vec.getY() * new_vec.getY());
		return Math.acos(numer / denom);

	}

	@Override
	public double setVisible(boolean visible) {
		isVisible = visible;
		return isVisible ? 1.0 : 0.0;
	}

	@Override
	public double getY() {
		return myYPos;
	}

	@Override
	public double getX() {
		return myXPos;
	}

	@Override
	public double setPenDown(boolean down) {
		isDown = down;
		return isDown ? 1.0 : 0.0;
	}

	@Override
	public double getHeading() {
		return myDegrees;
	}

	@Override
	public double getPendown() {
		return isDown ? 1.0 : 0.0;
	}

	@Override
	public double getVisible() {
		return isVisible ? 1.0 : 0.0;
	}
	
	@Override
	public double clear() {
		double dist = this.home();
		myPane.getChildren().clear();
		myLines.getChildren().clear();
		translate(myPane);
		myPrevXPos = myXPos;
		myPrevYPos = myYPos;
		myViewPrevX = myViewX;
		myViewPrevY = myViewY;
		myIV.setFitHeight(20);
		myIV.setFitWidth(20);
		myPane.getChildren().add(myIV);
		return dist;
	}
	
	private double degreesToRadians(double degrees) {
		return (degrees * Math.PI) / 180.0;
	}

	private double calcDistance(double x1, double y1, double x2, double y2) {
		double a = Math.abs(x2 - x1);
		double b = Math.abs(y2 - y1);
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}


	private Point2D calcVector(double x1, double y1, double x2, double y2) {
		double x = x2 - x1;
		double y = y2 - y1;
		return new Point2D(x, y);
	}

	@Override
	public double getId() {
		return myId;
	}

	@Override
	public double getShape() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPenColor() {
		List<String> myColors = new ArrayList<>()	{{
			add("Black");
			add("White");
			add("Red");
			add("Orange");
			add("Yellow");
			add("Green");
			add("Blue");
			add("Purple");
			add("Pink");
		}};

		for (int i = 0; i < myColors.size(); i++)	{
			if (Color.valueOf(myColors.get(i)) == myColor)	{
				return i;
			}
		}
		return 0;
	}

	@Override
	public double setPenWidth(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPenColor(int dex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setShape(int dex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPalette(int dex, double r, double g, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setPenSize(Double aDouble) {
		return 0;
	}

}
