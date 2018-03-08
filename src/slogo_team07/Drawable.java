package slogo_team07;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface Drawable {
	public void draw(Pane display, Color color, double penWidth);
	
	public ImageView getView();
	
	public void setView(String imagePath);
	
	public boolean getIsVisible();
	
	public void setPane(Pane pane);

	public double getX();

	public double getY();

	public double getHeading();
	
	public boolean getIsDown();

	public Color getColor();

	public double getPenWidth();
	
	public double getViewX();
	
	public double getViewY();
	
	public void setViewX(double x);
	
	public void setViewY(double y);
}
