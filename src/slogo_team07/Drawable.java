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

	public double getId();

	public double getPenColor();

	public double getPenWidth();

	public double getX();

	public double getY();

	public double getHeading();
	
	public boolean getIsDown();

	public double setPenDown(boolean down);

	public double setPenColor(int dex);

	public double setPenWidth(double pixels);
}
