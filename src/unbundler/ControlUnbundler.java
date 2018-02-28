package unbundler;

import java.util.List;
import java.util.Stack;

public abstract class ControlUnbundler implements Unbundler{

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";

    /**
     * Finds the beginning and ending brackets for the given control command
     * @param exp
     * @param index
     * @return
     */
    protected int[] findBrackets (List< String > exp, int index) {
        int[] answer = new int[2];
        boolean valid = false;
        Stack<Integer> bracketIndex = new Stack<>();
        for (int i = index; i < exp.size(); i++) {
            if (!notLeftBracket(exp.get(i))) {
                bracketIndex.push(i);
            }
            else if (!notRightBracket(exp.get(i)) && bracketIndex.size() > 0) {
                answer[1] = i;
                answer[0] = bracketIndex.pop();
                valid = true;
            }
            if (valid && bracketIndex.size() == 0) {
                System.out.println(answer[0] + " " + answer[1]);
                return answer;
            }
        }
        return answer;
    }

    /**
     * Modifies the list and returns a new list without the extracted, unbundled string
     * @param exp is the entire ArrayList of the input commands
     * @param firstIndex is the index where the command begins
     * @param lastIndex is the index where the command ends
     */
    protected void modifyList (List < String > exp, int firstIndex, int lastIndex){
        for (int i = lastIndex; i >= firstIndex; i--) {
            exp.remove(i);
        }
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a left bracket, false otherwise
     */
    protected boolean notLeftBracket (String current){
        return !current.equals(LEFT_BRACE);
    }

    /**
     * @param current is the current string
     * @return true if the current string is not a right bracket, false otherwise
     */
    protected boolean notRightBracket (String current){
        return !current.equals(RIGHT_BRACE);
    }
}
