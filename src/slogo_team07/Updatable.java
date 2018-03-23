package slogo_team07;

/**
 * @author benhubsch
 * @author Jennifer Chin
 * 
 *         Updatable interface that is implemented by the Turtle class. Defines
 *         the public Turtle methods that can be accessed by the back end in
 *         order to update Turtle values as Turtle commands are executed.
 * 
 */
public interface Updatable {
	/**
	 * Changes the Turtle's position to the given x and y coordinates
	 * 
	 * @param x
	 * @param y
	 * @return double - distance moved
	 */
	double setPosition(double x, double y);

	/**
	 * Moves the Turtle a certain number of pixels
	 * 
	 * @param pixels
	 * @return double - distance moved
	 */
	double move(double delta);

	/**
	 * Rotates the Turtle a specified number of degrees
	 * 
	 * @param clockwise
	 * @return double - degrees rotated
	 */
	double rotate(double clockwise);

	/**
	 * Changes the Turtle's heading to be the direction specified by the parameter
	 * degrees
	 * 
	 * @param degrees
	 * @return double - degrees rotated
	 */
	double setHeading(double degrees);

	/**
	 * Sets the Turtle to face a particular direction.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	double setFacing(double x, double y);

	/**
	 * Changes whether the Turtle is visible or not
	 * 
	 * @param visible
	 * @return double
	 */
	double setVisible(boolean isVisible);

	/**
	 * Returns y position of the Turtle
	 * 
	 * @return double
	 */
	double getY();

	/**
	 * Returns x position of the Turtle
	 * 
	 * @return double
	 */
	double getX();

	/**
	 * Returns the direction the Turtle is currently facing
	 * 
	 * @return double
	 */
	double getHeading();

	/**
	 * Returns whether or not the pen is down
	 * 
	 * @return double
	 */
	double getPendown();

	/**
	 * Returns whether or nto the Turtle is visible
	 * 
	 * @return double
	 */
	double getVisible();

	/**
	 * Places the turtle back at the center of the canvas
	 * 
	 * @return double - distance moved
	 */
	double home();

	/**
	 * Changes whether or not the Turtle's pen is down
	 * 
	 * @param down
	 * @return double
	 */
	double setPenDown(boolean down);

	/**
	 * Clears the canvas of all Turtle trails and returns the Turtle back to the
	 * center of the Canvas
	 * 
	 * @return double - distance Turtle moved
	 */
	double clear();

	/**
	 * Return id of the Turtle
	 * 
	 * @return double
	 */
	double getId();

	/**
	 * Returns the index of the current Shape of the Turtle in the list of possible
	 * Shapes
	 * 
	 * @return double
	 */
	double getShape();

	/**
	 * Returns the index of the current pen color of the Turtle in the list of
	 * possible pen colors
	 * 
	 * @return double
	 */
	double getPenColor();

	/**
	 * Changes the pen width to the specified number of pixels
	 * 
	 * @param pixels
	 * @return double - pixels
	 */
	double setPenWidth(double pixels);

	/**
	 * Changes the pen color to the color at the specified index in the list of
	 * possible colors
	 * 
	 * @param dex
	 * @return double - index
	 */
	double setPenColor(int dex);

	/**
	 * Changes the Turtle's shape to the shape at the specified index in the list of
	 * possible shapes
	 * 
	 * @param dex
	 * @return double - index
	 */
	double setShape(int dex);

	/**
	 * Adds a new color to the possible list of colors at the specified index with
	 * specified r, g, b values
	 * 
	 * @param dex
	 * @param r
	 * @param g
	 * @param b
	 * @return double - the index of the new color
	 */
	double setPalette(int dex, double r, double g, double b);
}
