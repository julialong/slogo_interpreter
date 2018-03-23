package commands;

import java.util.List;

/**
 * 
 * @author benhubsch
 * 
 *         Since so many classes modify the input list, it only made sense to
 *         farm it out to a default method in an interface. This method simply
 *         modifies the contents of a list object. It is called from classes
 *         like the unbundlers that operate on the input string but then return
 *         a different value. This class prevents input from being parsed more
 *         than once.
 *
 */
public interface ListModifier {
	default void modifyList(List<String> exp, int dex) {
		for (int i = dex; i >= 0; i--) {
			exp.remove(i);
		}
	}
}
