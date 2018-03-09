/**
 * @author Jennifer Chin
 * Drawable interface that is implemented by the Turtle class. Defines the public Turtle methods that can be accessed
 * by the front end in order to update the GUI.
 */

package slogo_team07;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface Drawable {
	/**
	 * Allows the Turtle to draw itself on the Canvas. Takes in the canvas it is supposed to be drawn on, and the 
	 * pen color and the pen width its trail should be drawn with. 
	 * @param display
	 * @param color
	 * @param penWidth
	 */
	public void draw(Pane display, Color color, double penWidth);
	
	/**
	 * Returns ImageView of Turtle
	 * @return ImageView
	 */
	public ImageView getView();
	
	/**
	 * Changes the ImageView to the new image given by the imagePath param
	 * @param imagePath
	 */
	public void setView(String imagePath);
	
	/**
	 * Returns whether or not the Turtle is visible
	 * @return boolean
	 */
	public boolean getIsVisible();

	/**
	 * Return id of the Turtle
	 * @return double
	 */
	public double getId();

	/**
	 * Returns the index of the current pen color of the Turtle in the list of possible pen colors
	 * @return double
	 */
	public double getPenColor();

	/**
	 * Changes the pen color to the color at the specified index in the list of possible colors 
	 * @param dex
	 * @return double - index
	 */
	public double setPenColor(int dex);

	/**
	 * Returns pen width in pixels
	 * @return double
	 */
	public double getPenWidth();

	/**
	 * Changes the pen width to the specified number of pixels
	 * @param pixels
	 * @return double - pixels
	 */
	public double setPenWidth(double pixels);

	/**
	 * Returns x position of the Turtle
	 * @return double
	 */
	public double getX();

	/**
	 * Returns y position of the Turtle
	 * @return double
	 */
	public double getY();

	/**
	 * Returns the direction the Turtle is currently facing
	 * @return double
	 */
	public double getHeading();
	
	/**
	 * Returns whether or not the Turtle's pen is down
	 * @return boolean
	 */
	public boolean getIsDown();

	/**
	 * Changes whether or not the Turtle's pen is down
	 * @param down
	 * @return double 
	 */
	public double setPenDown(boolean down);
	
	/**
	 * Returns the x position of the ImageView
	 * @return double
	 */
	public double getViewX();

	/**
	 * Changes the ImageView's x position to the given double
	 * @param x
	 */
	public void setViewX(double x);
	
	/**
	 * Returns the y position of the ImageView
	 * @return double
	 */
	public double getViewY();
	
	/**
	 * Changes the ImageView's y position to the given double
	 * @param y
	 */
	public void setViewY(double y);

	/**
	 * Returns a list of all the current possible pen colors for this Turtle
	 * @return List<Color>
	 */
	public List<Color> getMyColors();
	
	/**
	 * Returns the index of the current Shape of the Turtle in the list of possible Shapes
	 * @return double
	 */
	public double getShape();
	
	/**
	 * Changes the Pane of the Turtle to be the given Pane
	 * @param pane
	 */
	public void setPane(Pane pane);
}
