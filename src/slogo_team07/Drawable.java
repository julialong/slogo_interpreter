package slogo_team07;

import java.util.List;

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

	public double getId();

	public double getPenColor();

	public double setPenColor(int dex);

	public double getPenWidth();

	public double setPenWidth(double pixels);

	public double getX();

	public double getY();

	public double getHeading();
	
	public boolean getIsDown();

	public double setPenDown(boolean down);
	
	public double getViewX();

	public void setViewX(double x);
	
	public double getViewY();
	
	public void setViewY(double y);

	public List<Color> getMyColors();
	
	public double getShape();
}
