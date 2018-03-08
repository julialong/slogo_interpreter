package slogo_team07;

import java.util.List;
import java.util.Map;

import javafx.animation.Transition;
import javafx.util.Duration;

public class TurtleAnimation extends Transition {
	
	final private Duration animationTime = Duration.seconds(3);
	
	public TurtleAnimation(Map<Drawable, List<String>> drawables){
		//put draw in this class
			//each draw is an animation
		//run this animation parallel to overall animation 
		//- visualizer knows about this class, this class knows about turtle
		//canvas no longer needs to call draw, just needs to update pen color, width, etc
	}

	@Override
	protected void interpolate(double frac) {
		// TODO Auto-generated method stub
		
	}
	
}
