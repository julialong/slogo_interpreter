/**
 * @author Jennifer Chin
 * @author Maya Messinger
 * Started 20 Feb 18
 * Class that holds all of the GUI sidebar items - mmostly menus
 */

package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import resources.keys.Resources;
import resources.languages.ResourcesLanguages;
import slogo_team07.Drawable;
import slogo_team07.Turtle;

public class SideBar extends VBox{
	private VBox myVBox;
	private Pane myCanvasObjects;
	private Canvas myCanvas;
	private List<Drawable> myTurtles;
	private ObservableList<String> colorList = FXCollections.observableArrayList("Default", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> iconList = FXCollections.observableArrayList("Turtle", "Dog", "Cat", "Fish",
			"Octopus", "Bird", "Butterfly");
	private ObservableList<String> penList = FXCollections.observableArrayList("Black", "White", "Red", "Orange",
			"Yellow", "Green", "Blue", "Purple", "Pink");
	private ObservableList<String> langsSupported = FXCollections.observableArrayList("Chinese", "English",
			"French", "German", "Italian", "Portuguese", "Russian", "Spanish");
	private ObservableList<LoadButton> uDefCommands = FXCollections.observableArrayList();
	private ObservableList<VarVal> setVariables = FXCollections.observableArrayList();
	private TableView commandTable;
	private TableView variableTable;
	private TableColumn comText;
	private TableColumn vars;
	private TableColumn varName;
	private TableColumn varValue;
	protected TextInput myTextInput;
	protected String language;
	
	/**
	 * Constructor for sidebar of program that contains buttons/options
	 * @param canvas	canvas of program, where turtles are displayed
	 * @param turtles	list of all the movers in the canvas
	 */
	public SideBar(Pane canvas, List<Drawable> turtles, Canvas c){
		myCanvasObjects = canvas;
		myTurtles = turtles;
		myCanvas = c;
	}
	
	/**
	 * Creates the sidebar as a VBox and adds its children (buttons) inside
	 */
	public VBox initSideBar(){
		myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));
		
		Button helpButton = new Button();
		ComboBox colorMenu = new ComboBox(colorList);
		ComboBox iconMenu = new ComboBox(iconList);
		ComboBox penMenu = new ComboBox(penList); 
		ComboBox langMenu = new ComboBox<String>(langsSupported);
		commandTable = new TableView();
		variableTable = new TableView();
		double colWidth = 250;
		
		Button test = new Button("Test");
		test.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				double x = 0;
				double y = 0;
				for (Drawable turtle: myTurtles){
					turtle.setPane(myCanvasObjects);
					turtle.test(100, 100);
				}
			}
		});
		myVBox.getChildren().add(test);

		helpButton.setText(ResourcesLanguages.getString(language, "Help"));
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new HelpBox(language);
			}
		});
		myVBox.getChildren().add(helpButton);

		colorMenu.setPromptText(Resources.getString("ColorMenu"));
		colorMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String tempColor = colorMenu.getSelectionModel().getSelectedItem().toString();
				myCanvasObjects.getStyleClass().removeAll("pane", "red-back", "orange-back", "yellow-back", 
						"green-back", "blue-back", "purple-back", "pink-back");
				myCanvasObjects.getStyleClass().add(Resources.getString(tempColor));
			}
		});
		myVBox.getChildren().add(colorMenu);
		
		iconMenu.setPromptText(Resources.getString("TurtleMenu"));
		iconMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String tempIcon = iconMenu.getSelectionModel().getSelectedItem().toString();
				//right now will change icon of all turtles
				for (Drawable turtle: myTurtles){
					myCanvasObjects.getChildren().remove(turtle.getView());
					turtle.setPane(myCanvasObjects);
					turtle.setView(Resources.getString(tempIcon));
					myCanvasObjects.getChildren().add(turtle.getView());
				}
			}
		});
		myVBox.getChildren().add(iconMenu);
		
		penMenu.setPromptText(Resources.getString("PenMenu"));
		penMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String tempPen = penMenu.getSelectionModel().getSelectedItem().toString();
				//System.out.println("set: " + Color.valueOf(tempPen));
				myCanvas.setColor(Color.valueOf(tempPen));
			}
		});
		myVBox.getChildren().add(penMenu);
		
		langMenu.setPromptText(Resources.getString("LangMenu"));
		langMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				language = (String)(langMenu.getValue());
				helpButton.setText(ResourcesLanguages.getString(language, "Help"));
			}
		});
		myVBox.getChildren().add(langMenu);

		commandTable.setEditable(false);
		comText = new TableColumn("User-defined commands");
		comText.setCellValueFactory(new PropertyValueFactory<>("me"));
		comText.setMinWidth(colWidth);
        commandTable.getColumns().add(comText);
        commandTable.setItems(uDefCommands);
		myVBox.getChildren().add(commandTable);

		variableTable.setEditable(false);
		vars = new TableColumn("Variables");
		vars.setPrefWidth(colWidth);
		varName = new TableColumn("Name");
		varName.setCellValueFactory(new PropertyValueFactory<>("key"));
		varName.setPrefWidth(colWidth/2);
		varValue = new TableColumn("Value");
		varValue.setPrefWidth(colWidth/2);
		varValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		vars.getColumns().addAll(varName, varValue);
		variableTable.getColumns().add(vars);
		variableTable.setItems(setVariables);
		myVBox.getChildren().add(variableTable);
				
		return myVBox;
	}

	protected void addButton(String text)	{
		if (buttonExists(text))	{
			return;
		}

		LoadButton udc = new LoadButton();
		udc.setText(text);

		udc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				myTextInput.loadInput("\n" + udc.getText());
			}
		});

		uDefCommands.add(udc);
	}

	protected void addVar(String var, String value)	{
		if (varExists(var) != null)	{
			varExists(var).value.set(value);
		}
		else	{
			setVariables.add(new VarVal(var, value));
		}
	}

	private boolean buttonExists(String text)	{
		for (Button command:uDefCommands)	{
			if (command.getText().equals(text))	{
				return true;
			}
		}

		return false;
	}

	private VarVal varExists(String var)	{
		for (VarVal aVar:setVariables)	{
			if (aVar.key.getValue().equals(var))	{
				return aVar;
			}
		}

		return null;
	}

	public class LoadButton extends Button	{
		private SimpleObjectProperty me;

		public LoadButton()	{
			super();
			me = new SimpleObjectProperty(this);
		}

		public SimpleObjectProperty meProperty()	{
			return me;
		}
	}

	public class VarVal	{
		private SimpleStringProperty key;
		private SimpleStringProperty value;

		private VarVal(String aKey, String aVal)	{
			key = new SimpleStringProperty(aKey);
			value = new SimpleStringProperty(aVal);
		}

    	public StringProperty keyProperty() {
	        return key;
	    }

	    public StringProperty valueProperty()	{
	    	return value;
	    }
	}
}
