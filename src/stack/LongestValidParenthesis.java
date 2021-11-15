package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int max=0;
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c==')') {
                stack.pop();
                if(stack.isEmpty())
                    stack.push(i);
                else {
                    max = Math.max(max, i-stack.peek());
                }
            } else {
                stack.push(i);
            }
        }
        return max;
    }
}
