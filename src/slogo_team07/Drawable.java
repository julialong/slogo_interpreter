package slogo_team07;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.Canvas;

public interface Drawable {
	public void draw(Pane display);
	
	public ImageView getView();
	
	public void setView(String imagePath);
	
	public boolean getIsVisible();

}
