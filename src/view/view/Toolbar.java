package view;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.keys.Resources;

public class Toolbar extends AnchorPane {
	
	private AnchorPane myPane;
	
	public Toolbar(){
		
	}
	
	public AnchorPane initToolbar(){
		myPane = new AnchorPane();
		myPane.setPadding(new Insets(Resources.getInt("Inset")));
		
		Text title = new Text(Resources.getString("Title"));
		title.getStyleClass().add("title");
		myPane.getChildren().add(title);
		myPane.setLeftAnchor(title, 10.0);
		myPane.setTopAnchor(title, 10.0);
		
		return myPane;
	}
	
}
