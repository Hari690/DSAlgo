package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 */
public class BasicCalculator2 {

    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        char op = '+';
        int val = 0;
        for(int i=0;i<s.length();i++) {
            if(Character.isDigit(s.charAt(i))) {
                val = val*10+s.charAt(i)-'0';
            }
            if(s.charAt(i)=='*' || s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='/' || i==s.length()-1) {
                if(op=='+') {
                    stack.push(val);
                }
                if(op=='-') {
                    stack.push(-val);
                }
                if(op=='*') {
                    stack.push(stack.pop()*val);
                }
                if(op=='/') {
                    stack.push(stack.pop()/val);
                }
                val = 0;
                op = s.charAt(i);
            }
        }

        int result = 0;
        while(!stack.isEmpty()) {
            result+=stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculator2 solution = new BasicCalculator2();
        solution.calculate("3+2*2");
    }
}
