package stack;

import java.util.Deque;
import java.util.LinkedList;
/**
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "(*)"
 * Output: true
 * Example 3:
 *
 * Input: s = "(*))"
 * Output: true
 */
public class ValidParenthesisString {

    /*
        Store opening and wild in separate stacks and keep popping in order for closing parenthesis.
        The reason we need stack is to store the index since in the end we need to check if
        we have as many wild chars as the no of opening parenthesis
        and all the opening parenthesis come before wild.
     */
    public boolean checkValidString(String s) {
        Deque<Integer> parStack = new LinkedList<>();
        Deque<Integer> wildStack = new LinkedList<>();

        for(int i=0;i<s.toCharArray().length;i++) {
            char c = s.charAt(i);
            if(c=='(')
                parStack.push(i);
            if(c=='*')
                wildStack.push(i);
            if(c==')') {
                if(!parStack.isEmpty())
                    parStack.pop();
                else if(!wildStack.isEmpty())
                    wildStack.pop();
                else
                    return false;
            }
        }
        while(!parStack.isEmpty() && !wildStack.isEmpty()) {
            if(wildStack.pop()<parStack.pop())
                return false;
        }
        return parStack.isEmpty();
    }
}
