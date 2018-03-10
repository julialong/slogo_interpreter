/**
 * @author Jennifer Chin
 * @author Ben Hubsch
 * Turtle class that implements two interfaces, Drawable and Updatable. Drawable is used by the front end and 
 * Updatable is used by the back end. Back end calls Updatable methods when a Turtle command is called and front 
 * end calls Drawable methods to accurately update the GUI as Turtle commands are called. 
 */

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
import resources.keys.Resources;
import view.Visualizer;

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
	private List<Color> myColors = new ArrayList<>();
	private List<String> myShapes = Visualizer.possIVImages;
	private String myShape = "Turtle";

	/**
	 * Turtle constructor that takes in a String representing the Turtle's id. Sets the default image to a Turtle
	 * @param id
	 */
	public Turtle(String id) {
		myId = Double.parseDouble(id);
		Image image = new Image("/view/turtle.jpg");
		myIV = new ImageView(image);
		myIV.setX(myViewX);
		myIV.setY(myViewY);

		for (int i = 0; i < Visualizer.possPenColors.size(); i++)	{
			myColors.add(Color.valueOf(Visualizer.possPenColors.get(i)));
		}
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
	
	private void opptranslate(Pane display){
		double height = display.getHeight();
		double width = display.getWidth();
		myXPos = myViewX - width/2;
		myYPos = height/2 - myViewY;
		myPrevXPos = myViewPrevX - width/2;
		myPrevYPos = height/2 - myViewPrevY;	
	}
	
	/**
	 * Changes the Pane of the Turtle to be the given Pane
	 * @param pane
	 */
	public void setPane(Pane pane){
		myPane = pane;
	}

	/**
	 * Returns whether or not the Turtle is visible
	 * @return boolean
	 */
	@Override
	public boolean getIsVisible(){
		return isVisible;
	}
	
	/**
	 * Returns whether or not the Turtle's pen is down
	 * @return boolean
	 */
	@Override
	public boolean getIsDown(){
		return isDown;
	}

	/**
	 * Returns pen width in pixels
	 * @return double
	 */
	@Override
	public double getPenWidth()	{
		return myPenWidth;
	}
	
	/**
	 * Returns ImageView of Turtle
	 * @return ImageView
	 */
	@Override
	public ImageView getView(){
		return myIV;
	}

	/**
	 * Changes the ImageView to the new image given by the imagePath param
	 * @param imagePath
	 */
	@Override
	public void setView(String imagePath){
		myShape = Resources.getString(imagePath);
		Image image = new Image(imagePath);
		myIV = new ImageView(image);
		int ivDim = 20;
		myIV.setFitHeight(ivDim);
		myIV.setFitWidth(ivDim);
		translate(myPane);
		myIV.setX(myViewX);
		myIV.setY(myViewY);
	}
	
	/**
	 * Returns the x position of the ImageView
	 * @return double
	 */
	@Override 
	public double getViewX(){
		return myViewX;
	}
	
	/**
	 * Returns the y position of the ImageView
	 * @return double
	 */
	@Override 
	public double getViewY(){
		return myViewY;
	}
	
	/**
	 * Changes the ImageView's x position to the given double
	 * @param x
	 */
	@Override
	public void setViewX(double x){
		myViewPrevX = myViewX;
		myViewX = x;
		opptranslate(myPane);
	}
	
	/**
	 * Changes the ImageView's y position to the given double
	 * @param y
	 */
	@Override
	public void setViewY(double y){
		myViewPrevY = myViewY;
		myViewY = y;
		opptranslate(myPane);
	}

	/**
	 * Returns a list of all the current possible pen colors for this Turtle
	 * @return List<Color>
	 */
	@Override
	public List<Color> getMyColors(){
		return myColors;
	}

	/**
	 * Allows the Turtle to draw itself on the Canvas. Takes in the canvas it is supposed to be drawn on, and the 
	 * pen color and the pen width its trail should be drawn with. 
	 * @param display
	 * @param color
	 * @param penWidth
	 */
	@Override
	public void draw(Pane display, Color color, double penWidth) {
		myPenWidth = penWidth;
		myColor = color;
		myPane = display;
		translate(myPane);
		if (! isDown){
			myViewPrevX = myViewX;
			myViewPrevY = myViewY;
			opptranslate(myPane);
			System.out.println("pen up");
			System.out.println("prev x: " + myViewPrevX);
			System.out.println("prev y: " + myViewPrevY);
			System.out.println("x: " + myViewX);
			System.out.println("y: " + myViewY);
		}
		if (isDown){
			if (myPane.getChildren().contains(myLines)){
				myPane.getChildren().remove(myLines);
			}
			Line trail = new Line(myViewPrevX, myViewPrevY, myViewX, myViewY);
			trail.setStroke(myColor);
			trail.setStrokeWidth(myPenWidth);
			myLines.getChildren().add(trail);
			myPane.getChildren().add(myLines);
			System.out.println("pen down");
			System.out.println("prev x: " + myViewPrevX);
			System.out.println("prev y: " + myViewPrevY);
			System.out.println("x: " + myViewX);
			System.out.println("y: " + myViewY);
			//lineAnimation(myIV);
		}
	}

//	private void lineAnimation(Node iv){
//		Path path = new Path();
//		path.getElements().add(new MoveTo(myViewX, myViewY));
//		path.getElements().add(new LineTo(myViewX, myViewY));
//		//will need to change duration.millis to variable to change speed
//		PathTransition pt = new PathTransition(Duration.millis(4000), path, iv);
//		pt.play();
//	}

	/**
	 * Changes the Turtle's position to the given x and y coordinates
	 * @param x
	 * @param y
	 * @return double - distance moved
	 */
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

	/**
	 * Moves the Turtle a certain number of pixels
	 * @param pixels
	 * @return double - distance moved
	 */
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

	/**
	 * Places the turtle back at the center of the canvas
	 * @return double - distance moved
	 */
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

	/**
	 * Rotates the Turtle a specified number of degrees 
	 * @param clockwise
	 * @return double - degrees rotated
	 */
	@Override
	public double rotate(double clockwise) {
		myDegrees += clockwise;
		myIVDegrees -= clockwise;
		myIV.setRotate(myIVDegrees);
		return Math.abs(clockwise);
	}

	/**
	 * Changes the Turtle's heading to be the direction specified by the parameter degrees
	 * @param degrees
	 * @return double - degrees rotated
	 */
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
	
	/**
	 * Changes whether the Turtle is visible or not
	 * @param visible
	 * @return double
	 */
	@Override
	public double setVisible(boolean visible) {
		isVisible = visible;
		return isVisible ? 1.0 : 0.0;
	}

	/**
	 * Returns y position of the Turtle
	 * @return double
	 */
	@Override
	public double getY() {
		return myYPos;
	}

	/**
	 * Returns x position of the Turtle
	 * @return double
	 */
	@Override
	public double getX() {
		return myXPos;
	}

	/**
	 * Changes whether or not the Turtle's pen is down
	 * @param down
	 * @return double 
	 */
	@Override
	public double setPenDown(boolean down) {
		isDown = down;
		return isDown ? 1.0 : 0.0;
	}

	/**
	 * Returns the direction the Turtle is currently facing
	 * @return double
	 */
	@Override
	public double getHeading() {
		return myDegrees;
	}

	/**
	 * Returns whether or not the pen is down
	 * @return double
	 */
	@Override
	public double getPendown() {
		return isDown ? 1.0 : 0.0;
	}

	/**
	 * Returns whether or nto the Turtle is visible
	 * @return double
	 */
	@Override
	public double getVisible() {
		return isVisible ? 1.0 : 0.0;
	}
	
	/**
	 * Clears the canvas of all Turtle trails and returns the Turtle back to the center of the Canvas
	 * @return double - distance Turtle moved
	 */
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

	/**
	 * Return id of the Turtle
	 * @return double
	 */
	@Override
	public double getId() {
		return myId;
	}

	/**
	 * Returns the index of the current Shape of the Turtle in the list of possible Shapes
	 * @return double
	 */
	@Override
	public double getShape() {
		for (int i = 0; i < myShapes.size(); i++){
			int adjusted = i % myShapes.size();
			if (myShapes.get(adjusted).equals(myShape)){
				return adjusted;
			}
		}
		return 0;
	}

	/**
	 * Returns the index of the current pen color of the Turtle in the list of possible pen colors
	 * @return double
	 */
	@Override
	public double getPenColor() {
		for (int i = 0; i < myColors.size(); i++)	{
			int adjusted = i % myColors.size();
			if (myColors.get(adjusted).equals(myColor))	{
				return adjusted;
			}
		}
		return 0;
	}

	/**
	 * Changes the pen width to the specified number of pixels
	 * @param pixels
	 * @return double - pixels
	 */
	@Override
	public double setPenWidth(double pixels) {
		myPenWidth = pixels;
		return pixels;
	}


	/**
	 * Changes the pen color to the color at the specified index in the list of possible colors 
	 * @param dex
	 * @return double - index
	 */
	@Override
	public double setPenColor(int dex) {
		int col_dex = dex % myColors.size();
		myColor = myColors.get(col_dex);
		return dex;
	}

	/**
	 * Changes the Turtle's shape to the shape at the specified index in the list of possible shapes
	 * @param dex
	 * @return double - index
	 */
	@Override
	public double setShape(int dex) {
		int shape_dex = dex % myShapes.size();
		setView(Resources.getString(myShapes.get(shape_dex)));
		return dex;
	}

	/**
	 * Adds a new color to the possible list of colors at the specified index with specified r, g, b values
	 * @param dex
	 * @param r
	 * @param g
	 * @param b
	 * @return double - the index of the new color
	 */
	@Override
	public double setPalette(int dex, double r, double g, double b) {
		int adjusted = dex % myColors.size();
		Color newColor = Color.rgb((int) r, (int) g, (int) b, 1.0);
		myColors.add(adjusted, newColor);
		return adjusted;
	}

}
