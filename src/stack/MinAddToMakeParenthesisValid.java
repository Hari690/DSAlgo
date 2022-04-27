package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * A parentheses string is valid if and only if:
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 */
public class MinAddToMakeParenthesisValid {
    public int minAddToMakeValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        int count=0;
        for(char c : s.toCharArray()) {
            if (c=='(')
                stack.push('(');
            else if(stack.isEmpty())
                count++;
            else
                stack.pop();
        }
        return stack.size()+count;
    }
}
