package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 * Return all the possible results. You may return the answer in any order.
 * Example 1:
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 *
 * Example 2:
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 *
 * Example 3:
 * Input: s = ")("
 * Output: [""]
 */
public class RemoveInvalidParenthesis {
    public List<String> removeInvalidParentheses(String s) {
        int remove = getParenthesisToRemove(s);
        Set<String> result = new HashSet<>();
        backtrackAndFind(0, s, result, remove,0, "");
        List<String> output = new ArrayList<>();
        for(String word : result)
            output.add(word);
        return output;
    }

    private void backtrackAndFind(int index, String s, Set<String> result, int remove, int open, String temp) {
        if (index==s.length()) {
            if(remove==0 && open==0) {
                result.add(temp);
            }
            return;
        }

        if(remove<0 || open<0)
            return;

        if(s.charAt(index)=='(') {
            backtrackAndFind(index+1, s, result, remove, open+1,temp+"(");
            backtrackAndFind(index+1, s, result, remove-1, open, temp);
        } else if(s.charAt(index)==')')  {
            backtrackAndFind(index+1, s, result, remove, open-1,temp+")");
            backtrackAndFind(index+1, s, result, remove-1, open, temp);
        } else {
            backtrackAndFind(index+1, s, result, remove, open, temp+s.charAt(index));
        }
    }

    private int getParenthesisToRemove(String s) {
        int open = 0;
        int maxRemove = 0;
        for (char c : s.toCharArray()) {
            if(c=='(') {
                open++;
            } else if (c==')') {
                if(open<=0)
                    maxRemove++;
                else
                    open--;
            }
        }
        maxRemove+=open;
        return maxRemove;
    }

    public static void main(String[] args) {
        RemoveInvalidParenthesis removeInvalidParenthesis = new RemoveInvalidParenthesis();
        removeInvalidParenthesis.removeInvalidParentheses("(a)())()");
    }
}
