/**
 * @author Maya Messinger (mm479)
 * Started 23 Feb 18
 * Class that provites the ability to change SLogo command lanuage
 */


package resources.languages;

import java.util.ResourceBundle;

public class ResourcesLanguages {
	
	public static String getString(String lang, String key) {
		try	{
			ResourceBundle language = ResourceBundle.getBundle("resources.languages/" + lang);
			return language.getString(key);
		}
		catch (NullPointerException e)	{
			// alert language not found
		}

		return null;
    }
}
