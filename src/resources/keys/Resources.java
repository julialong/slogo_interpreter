/**
 * @author Jennifer Chin
 * Resource class used to access values in the Resource bundle
 */
package resources.keys;

import java.util.ResourceBundle;

import javafx.scene.paint.Color;

public class Resources {
	
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("resources.keys/resourceKeys");
	
	/**
	 * Returns the corresponding String of a given String key
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
        return RESOURCEKEYS.getString(key);
    }
	
	/**
	 * Returns the corresponding int of a given String key
	 * @param key
	 * @return int
	 */
	public static int getInt(String key){
		return Integer.parseInt(RESOURCEKEYS.getString(key));
	}
	
	/**
	 * Returns the corresponding Color of a given String key
	 * @param key
	 * @return Color
	 */
	public static Color getColor(String key){
		return Color.valueOf(RESOURCEKEYS.getString(key));
	}

}
