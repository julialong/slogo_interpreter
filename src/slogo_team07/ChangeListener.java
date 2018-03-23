package slogo_team07;

/**
 * 
 * @author benhubsch
 * 
 *         This is an interface that Engine implements and exposes to the front
 *         end. It allows the implementation details within Engine to be
 *         encapsulated while abstracting out two of the more crucial
 *         non-command features. It allows the front end to make calls to change
 *         the language on user click and parse the input on input submit.
 *
 */
public interface ChangeListener {
	void changeInput(String input);

	void changeLanguage(String lang);
}
