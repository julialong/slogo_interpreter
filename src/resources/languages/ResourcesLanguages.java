/**
 * @author Maya Messinger (mm479)
 * Started 23 Feb 18
 * Class that provites the ability to change SLogo command lanuage
 */


package resources.languages;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ResourcesLanguages {
	private ResourcesLanguages()	{
	}
	
	/**
	 * Returns the typeable/input of a command of a given key in language lang
	 * @param lang	language to use
	 * @param key	command to find
	 */
	public static String getString(String lang, String key) {
		try	{
			ResourceBundle language = ResourceBundle.getBundle("resources.languages/" + lang);
			return language.getString(key);
		}
		catch (NullPointerException e)	{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Language File");
			alert.setContentText("Cannot find a properties file with this language");
			alert.show();
		}

		return null;
    }
}
