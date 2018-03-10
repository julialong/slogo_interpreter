/**
 * @author Maya Messinger (mm479)
 * Started 20 Feb 18
 * Handles input of all commands from user in program. Has run, clear, and recall command functionality
 */

package view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import resources.keys.Resources;
import slogo_team07.ChangeListener;

public class Console extends AnchorPane implements TextInput {

	private TextArea consoleInput;
	private TableView history;
	private Button runner;
	private Button clearer;
	private ObservableList<ComRet> pastCommands;
	protected VBox myVBox;
	protected ChangeListener myChangeListener;
	protected String language;

	private int offsetPad = 0;
	private int width = 700;
	private int cHeight = 100;
	private int hHeight = 80;
	private int buttonheightPadding = 3;
	private int buttonWidthPadding = 50;
	private int bHeight = (cHeight + hHeight + offsetPad + buttonWidthPadding)/buttonheightPadding;
	private int bWidth = 80;

	/**
	 * Ceates a new instance of Console and initialized variables
	 */
	public Console()    {
		initConsole();
	}

	/**
	 * Initializes this Console with variables/contents
	 */
	public AnchorPane initConsole() {
		pastCommands = FXCollections.observableArrayList();
		consoleInput = setConsole();
		history = setHistory();
		runner = setRunner();
		clearer = setClearer();

		VBox myButtons = new VBox(Resources.getInt(Visualizer.inset));
		myButtons.setPadding(new Insets(Resources.getInt(Visualizer.inset), Resources.getInt(Visualizer.inset), Resources.getInt(Visualizer.inset), Resources.getInt(Visualizer.inset)));
		myButtons.getChildren().addAll(runner, clearer);
		this.getChildren().add(myButtons);
		addElements();
		this.setTopAnchor(history, 0.0);
		this.setBottomAnchor(consoleInput, 0.0);
		this.setRightAnchor(myButtons, 0.0);

		return this;
	}

	/**
	 * Sends text from consoleInput to anything that calls run(), clears text box
	 */
	@Override
	public String run()    {
		String comm = consoleInput.getText();

		myChangeListener.changeInput(comm);

		return comm;
	}

	/**
	 * Clears text in input consoleInput
	 */
	@Override
	public void clear() {
		consoleInput.clear();
	}

	/**
	 * Loads a String (pre-determined user command or text from command history) into consoleInput
	 * @param command   pre-set command to insert into consoleInput
	 */
	@Override
	public void loadInput(String command) {
		consoleInput.appendText(command);    // "types" long command into textbox for the ability to re-use a pre-defined function
	}

	private void addElements()  {
		List<Node> elements = new ArrayList<>();
		addText(elements);
		this.getChildren().addAll(elements);
	}

	protected void printResult(String res)    {
		pastCommands.add(new ComRet(consoleInput.getText(), res));
		history.scrollTo(pastCommands.get(pastCommands.size() - 1));
		clear();
	}

	private void addText(List<Node> elements)  {
		elements.add(history);
		elements.add(consoleInput);
	}

	private void addButtons(List<Node> elements)   {
		elements.add(runner);
		elements.add(clearer);
	}

	private TableView setHistory()	{
		TableView aHistory = new TableView();

		int commWidth = (int)(width * 0.85);
		int scrollbarWidth = 20;
		aHistory.setEditable(true);
		aHistory.setPrefWidth(width);
		aHistory.setPrefHeight(hHeight);
		TableColumn commText = new TableColumn("Command");
		commText.setCellValueFactory(new PropertyValueFactory<>("command"));
		commText.setPrefWidth(commWidth);
		TableColumn returned = new TableColumn("Returns");
		returned.setCellValueFactory(new PropertyValueFactory<>("returned"));
		returned.setPrefWidth(width - commWidth - scrollbarWidth);
		aHistory.getColumns().addAll(commText, returned);
		aHistory.setItems(pastCommands);

		return aHistory;
	}

	private TextArea setConsole()	{
		TextArea aConsole = new TextArea();

		aConsole.setPromptText("Enter Commands");
		aConsole.setPrefWidth(width);
		aConsole.setPrefHeight(cHeight);
		aConsole.setLayoutY(hHeight + offsetPad);

		return aConsole;
	}

	private Button setRunner() {
		Button aRunner = new Button("Run");

		aRunner.setLayoutX(width + offsetPad);
		aRunner.setPrefWidth(bWidth);
		aRunner.setMaxWidth(runner.USE_PREF_SIZE);
		aRunner.setPrefHeight(bHeight);
		aRunner.setMaxHeight(aRunner.USE_PREF_SIZE);
		aRunner.setTextAlignment(TextAlignment.CENTER);
		aRunner.setFont(new Font(20));

		aRunner.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				run();
			}
		});

		return aRunner;
	}

	private Button setClearer()    {
		Button aClearer = new Button("Clear");

		aClearer.setLayoutX(width + offsetPad);
		aClearer.setLayoutY(bHeight + offsetPad);
		aClearer.setPrefWidth(bWidth);
		aClearer.setMaxWidth(aClearer.USE_PREF_SIZE);
		aClearer.setPrefHeight(bHeight);
		aClearer.setMaxHeight(aClearer.USE_PREF_SIZE);
		aClearer.setTextAlignment(TextAlignment.CENTER);
		aClearer.setFont(new Font(20));

		aClearer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clear();
			}
		});

		return aClearer;
	}

	/**
	 * Class that has properties that TableView can read in order to import into table
	 * Only public so PropertyValueFactory can get its properties
	 */
	public class ComRet	{
		private SimpleObjectProperty command;
		private SimpleStringProperty returned;

		private ComRet(String aCom, String aRet)	{
			Button cButton = new Button();
			cButton.setText(aCom);
			cButton.setOnAction(e -> loadInput("\n" + cButton.getText()));
			command = new SimpleObjectProperty(cButton);
			returned = new SimpleStringProperty(aRet);
		}

		private void setReturn(String newRet)	{
			returned = new SimpleStringProperty(newRet);
		}

		/**
		 * Returns the command as a button with text
		 */
    	public ObjectProperty commandProperty() {
	        return command;
	    }

	    /**
	     * Returns the returned value of the command as a property
	     */
	    public StringProperty returnedProperty()	{
	    	return returned;
	    }
	}
}