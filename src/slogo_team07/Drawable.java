package slogo_team07;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface Drawable {
	public void draw(Pane display, Color color, double penWidth);
	
	public ImageView getView();
	
	public void setView(String imagePath);
	
	public boolean getIsVisible();
	
	public void setPane(Pane pane);

	public Double getX();

	public Double getY();

	public Double getHeading();
	
	public boolean getIsDown();
	
	public boolean getStatus();

}
