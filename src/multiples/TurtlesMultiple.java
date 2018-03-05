package multiples;

import java.util.List;
import java.util.Map;

import slogo_team07.Updatable;

public class TurtlesMultiple extends Multiple {
	
	Map<String, Updatable> myUpdatables;
	
	public TurtlesMultiple(Map<String, Updatable> updatables) {
		myUpdatables = updatables;
	}

	@Override
	public double manage(List<String> input) {
		return myUpdatables.size();
	}

}
