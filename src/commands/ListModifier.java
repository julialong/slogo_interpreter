package commands;

import java.util.List;

public interface ListModifier {
	default void modifyList(List<String> exp, int dex) {
		for (int i=dex; i >= 0; i--) {
			exp.remove(i);
		}
	}
}
