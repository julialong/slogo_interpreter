package slogo_team07;

import java.beans.PropertyChangeEvent;

public interface ChangeListener {
	void changeInput(PropertyChangeEvent event);
	
	void changeLanguage(PropertyChangeEvent event);
}
