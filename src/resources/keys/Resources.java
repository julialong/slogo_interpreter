package resources.keys;

import java.util.ResourceBundle;

import javafx.scene.paint.Color;

public class Resources {
	
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("resources.keys/resourceKeys");
	
	public static String getString(String key) {
        return RESOURCEKEYS.getString(key);
    }
	
	public static int getInt(String key){
		return Integer.parseInt(RESOURCEKEYS.getString(key));
	}
	
	public static Color getColor(String key){
		return Color.valueOf(RESOURCEKEYS.getString(key));
	}

}
