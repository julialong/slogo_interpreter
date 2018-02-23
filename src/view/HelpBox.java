package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpBox extends Group {
    public HelpBox()    {
        this.getChildren().add(helpButton());
    }

    public Button helpButton()	{
        Button helpButton = new Button("Help");
    	helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				helpButton.setDisable(true);
				Scene helpInternal = new Scene(helpButton, 100, 100);
				Stage helpBox = new Stage();
				helpBox.setScene(helpInternal);
				helpBox.show();
			}
		});

    	return helpButton;
    }
}