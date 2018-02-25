package slogo_team07;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.Canvas;

public interface Drawable {
	public Pane draw(Pane display);
	
	public ImageView getView();
	
	public void setView(String imagePath);
}
