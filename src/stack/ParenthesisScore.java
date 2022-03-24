package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 */
public class ParenthesisScore {

    /*
        Manipulate a variable according to the level of the stack.
        If there's opening parenthesis save previous value and reset it to 0.
        In case of addition after that it will become 1 because of the max check.
        If it's multiplication based on level we need to do value*2 plus what exists in the stack( for addition).;
     */
    public int scoreOfParentheses(String S) {
        Deque<Integer> stack = new LinkedList<>();
        int cur = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                // default to 0 for each level. Will have value only during addition.
                stack.push(cur);
                cur = 0;
            } else {
                cur = stack.pop() + Math.max(cur * 2, 1);
            }
        }
        return cur;
    }

    /*
        O(1) solution.
     */
    public int scoreOfParenthesesAlt(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
            }

            if (s.charAt(i) == ')' && s.charAt(i - 1) == '(') {
                // Whenever you meet a () pair, you multiply 1 by all the 2 outside of it, and accumulate the result
                score += Math.pow(2, depth);
            }
        }

        return score;
    }

    public static void main(String[] args) {
        ParenthesisScore solution = new ParenthesisScore();
        solution.scoreOfParentheses("((()()))");
    }
}
