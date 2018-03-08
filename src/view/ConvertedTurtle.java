/**
 * @author Maya Messinger
 * Started 3 March 18
 * Helper class for DrawablesTable, as the class that the table uses to dispaly turtle properties and allow them to be edited
 */

package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import resources.keys.Resources;
import slogo_team07.Drawable;
import slogo_team07.Updatable;

public class ConvertedTurtle	{
	private Drawable thisTurtle;
	private List<String> properties;
	private List<String> propertyNames;
	private Visualizer myVis;
	private Canvas myCanvas;
	private Pane myPane;

	private SimpleObjectProperty id;
	private SimpleObjectProperty active;
	private SimpleObjectProperty image;
	private SimpleObjectProperty penDown;
	private SimpleObjectProperty penColor;
	private SimpleObjectProperty penWidth;
	private SimpleObjectProperty xPos;
	private SimpleObjectProperty yPos;
	private SimpleObjectProperty heading;

	public ConvertedTurtle(Map.Entry turtle, Visualizer vis, Canvas canvas, Pane pane)	{
		thisTurtle = (Drawable)turtle.getKey();
		properties = (List<String>)turtle.getValue();
		propertyNames = new ArrayList<String>();
		myVis = vis;
		myCanvas = canvas;
		myPane = pane;

		propertyNames.add("id");
		propertyNames.add("active");
		propertyNames.add("image");
		propertyNames.add("penDown");
		propertyNames.add("penColor");
		propertyNames.add("penWidth");

		id = makeID(properties.get(propertyNames.indexOf("id")));
		active = makeActive(properties.get(propertyNames.indexOf("active")));
		image = makeImage(properties.get(propertyNames.indexOf("image")));
		penDown = makepenDown(properties.get(propertyNames.indexOf("penDown")));
		penColor = makePenColor(properties.get(propertyNames.indexOf("penColor")));
		penWidth = makePenWidth(properties.get(propertyNames.indexOf("penWidth")));
		xPos = makeXPos();
		yPos = makeXPos();
		heading = makeHeading();
	}

	private SimpleObjectProperty makeID(String idString)	{
		return new SimpleObjectProperty(idString);
	}

	private SimpleObjectProperty makeActive(String activeString)	{
		String thisProp = "active";
		CheckBox activeBox = new CheckBox();
		activeBox.setSelected(Boolean.valueOf(activeString));
		activeBox.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent e) {
				if (activeBox.isSelected())	{
					// thisTurtle is active
					// tel all turtles active
					properties.set(propertyNames.indexOf(thisProp), "true");
				}
				else	{
					// thisTurtle is not active
					// tell every other turtle active
					properties.set(propertyNames.indexOf(thisProp), "false");
				}
			}
		});

		return new SimpleObjectProperty(activeBox);
	}

	private SimpleObjectProperty makeImage(String imageString)	{
		String thisProp = "image";
		ObservableList<String> imageList = FXCollections.observableArrayList("Turtle", "Dog", "Cat", "Fish",
			"Octopus", "Bird", "Butterfly");
		ComboBox imageMenu = new ComboBox(imageList);

		imageMenu.setPromptText(imageString);
		imageMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				String newImage = imageMenu.getSelectionModel().getSelectedItem().toString();

				myPane.getChildren().remove(thisTurtle.getView());
				thisTurtle.setPane(myPane);
				thisTurtle.setView(Resources.getString(newImage));
				myPane.getChildren().add(thisTurtle.getView());

				properties.set(propertyNames.indexOf(thisProp), newImage);
			}
		});

		return new SimpleObjectProperty(imageMenu);
	}

	private SimpleObjectProperty makepenDown(String penDownString)	{
		String thisProp = "penDown";
		CheckBox penDownBox = new CheckBox();
		penDownBox.setSelected(Boolean.valueOf(penDownString));
		penDownBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (penDownBox.isSelected())	{
					thisTurtle.setPenDown(true);
					properties.set(propertyNames.indexOf(thisProp), "down");
				}
				else	{
					thisTurtle.setPenDown(false);
					properties.set(propertyNames.indexOf(thisProp), "up");
				}
			}
		});

		return new SimpleObjectProperty(penDownBox);
	}

	private SimpleObjectProperty makePenColor(String penColorString)	{
		String thisProp = "penColor";
		ObservableList<String> penList = Visualizer.possPenColors;

		ComboBox penColorMenu = new ComboBox(penList);

		penColorMenu.setPromptText(penList.get(Integer.parseInt(penColorString)));
		penColorMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				String newColor = penColorMenu.getSelectionModel().getSelectedItem().toString();

				thisTurtle.setPenColor(penList.indexOf(newColor));

				properties.set(propertyNames.indexOf(thisProp), Integer.toString(penList.indexOf(newColor)));
			}
		});

		return new SimpleObjectProperty(penColorMenu);
	}

	private SimpleObjectProperty makePenWidth(String penWidthString)	{
		String thisProp = "penWidth";
		ObservableList<String> penWidthList = FXCollections.observableArrayList("0.5", "1.0", "1.5", "2.0", "2.5",
		"3.0", "4.0", "5.0");

		ComboBox penWidthMenu = new ComboBox(penWidthList);

		penWidthMenu.setPromptText(penWidthString);
		penWidthMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				String newWidth = penWidthMenu.getSelectionModel().getSelectedItem().toString();

				thisTurtle.setPenWidth(Double.parseDouble(newWidth));

				properties.set(propertyNames.indexOf(thisProp), newWidth);
			}
		});

		return new SimpleObjectProperty(penWidthMenu);
	}

	private SimpleObjectProperty makeXPos()	{
		return new SimpleObjectProperty(Double.toString(thisTurtle.getX()));
	}

	private SimpleObjectProperty makeYPos()	{
		return new SimpleObjectProperty(Double.toString(thisTurtle.getY()));
	}

	private SimpleObjectProperty makeHeading()	{
		return new SimpleObjectProperty(Double.toString(thisTurtle.getHeading()));
	}

	/**
	 * Returns this turtle's id
	 */
	public SimpleObjectProperty idProperty()	{
		return id;
	}

	/**
	 * Returns whether turtle is active or not
	 */
	public SimpleObjectProperty activeProperty()	{
		return active;
	}

	/**
	 * Returns this turtle's image
	 */
	public SimpleObjectProperty imageProperty()	{
		return image;
	}

	/**
	 * Returns whether pen for turtle is drawing a path or not
	 */
	public SimpleObjectProperty penDownProperty()	{
		return penDown;
	}

	/**
	 * Returns this turtle's pen color
	 */
	public SimpleObjectProperty penColorProperty()	{
		return penColor;
	}

	/**
	 * Returns this turtle's pen's width
	 */
	public SimpleObjectProperty penWidthProperty()	{
		return penWidth;
	}

	/**
	 * Returns this turtle's x position on canvas
	 */
	public SimpleObjectProperty xPosProperty()	{
		return xPos;
	}

	/**
	 * Returns this turtle's y position on canvas
	 */
	public SimpleObjectProperty yPosProperty()	{
		return yPos;
	}

	/**
	 * Returns this turtle's heading on canvas
	 */
	public SimpleObjectProperty headingProperty()	{
		return heading;
	}
}