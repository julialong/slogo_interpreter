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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.Resources;
import slogo_team07.Drawable;

public class SideBar extends VBox{
	private VBox myVBox;
	private Pane myCanvasObjects;
	private Canvas myCanvas;
	private Visualizer myVis;
	private Map<Drawable, List<String>> myTurtles;
	
	private ObservableList<LoadButton> uDefCommands = FXCollections.observableArrayList();
	private ObservableList<VarVal> setVariables = FXCollections.observableArrayList();
	private TableView commandTable;
	private TableView variableTable;
	private TableColumn comText;
	private TableColumn vars;
	private TableColumn varName;
	private TableColumn varValue;
	protected TextInput myTextInput;
	
	
	/**
	 * Constructor for sidebar of program that contains buttons/options
	 * @param canvas	canvas of program, where turtles are displayed
	 * @param turtles	list of all the movers in the canvas
	 */
	public SideBar(Pane canvas, Visualizer v, Map<Drawable, List<String>> turtles, Canvas c){
		myCanvasObjects = canvas;
		myVis = v;
		myTurtles = turtles;
		myCanvas = c;
	}
	
	/**
	 * Creates the sidebar as a VBox and adds its children (buttons) inside
	 */
	public VBox initSideBar(){
		myVBox = new VBox(Resources.getInt("Inset"));
		myVBox.setPadding(new Insets(Resources.getInt("Inset")));

		commandTable = new TableView();
		variableTable = new TableView();
		double colWidth = 250;
	
		myVBox.getChildren().add(allDrawablesButton());
		myVBox.getChildren().add(commandTable(colWidth));
		myVBox.getChildren().add(variableTable(colWidth));

		return myVBox;
	}	
	
	private Button allDrawablesButton()	{
		Button allDrawablesButton = new Button("Turtle Information");
    	allDrawablesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new DrawablesTable(myTurtles, myVis, myCanvas, myCanvasObjects);
				exportCommands();
				exportVariables();
			}
		});
		return allDrawablesButton;
	}

	private TableView commandTable(double colWidth)	{
		commandTable = new TableView();
		commandTable.setEditable(false);
		comText = new TableColumn("User-defined commands");
		comText.setCellValueFactory(new PropertyValueFactory<>("me"));
		comText.setMinWidth(colWidth);
        commandTable.getColumns().add(comText);
        commandTable.setItems(uDefCommands);

        return commandTable;
	}

	public List<String> exportCommands()	{
		List<String> commands = new ArrayList<>();

		for (Button button:uDefCommands)	{
			commands.add(button.getText());

			System.out.println(button.getText());
		}

		return commands;
	}

	private TableView variableTable(double colWidth)	{
		variableTable = new TableView();
		variableTable.setEditable(true);
		int numVarCols = 2;
		vars = new TableColumn("Variables");
		vars.setPrefWidth(colWidth);
		varName = new TableColumn("Name");
		varName.setCellValueFactory(new PropertyValueFactory<>("key"));
		varName.setPrefWidth(colWidth/numVarCols);
		varValue = new TableColumn("Value");
		varValue.setPrefWidth(colWidth/numVarCols);
		varValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		vars.getColumns().addAll(varName, varValue);
		variableTable.getColumns().add(vars);
		variableTable.setItems(setVariables);

		return variableTable;
	}

	public List<String> exportVariables()	{
		List<String> variables = new ArrayList<>();

		for (VarVal pair:setVariables)	{
			String fullText = "make " + pair.keyProperty().getValue() + " " + pair.valueProperty().getValue();
			variables.add(fullText);
		}

		return variables;
	}

	protected void addUDIButton(String text)	{
		if (buttonExists(text))	{
			return;
		}

		LoadButton udc = new LoadButton();
		udc.setText(text);

		udc.setOnAction(e -> myTextInput.loadInput("\n" + udc.getText()));

		uDefCommands.add(udc);
	}

	protected void addUDVar(String var, String value)	{
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

	/**
	 * Button that has an ObjectProperty in order for TableView to display
	 * Only public because TableView getter uses reflection and could not operate without this class being public
	 */
	public class LoadButton extends Button	{
		private SimpleObjectProperty me;

		private LoadButton()	{
			super();
			me = new SimpleObjectProperty(this);
		}

		/**
		 * Returns this button as an ObjectProperty
		 */
		public SimpleObjectProperty meProperty()	{
			return me;
		}
	}

	/**
	 * Class that has properties that TableView can read in order to import into table
	 * Only public so PropertyValueFactory can get its properties
	 */
	public class VarVal	{
		private SimpleStringProperty key;
		private SimpleStringProperty value;

		private VarVal(String aKey, String aVal)	{
			key = new SimpleStringProperty(aKey);
			value = new SimpleStringProperty(aVal);
		}

		/**
		 * Returns the name of a variable, as a property
		 */
    	public StringProperty keyProperty() {
	        return key;
	    }

	    /**
	     * Returns the value of a variable, as a property
	     */
	    public StringProperty valueProperty()	{
	    	return value;
	    }
	}
}
