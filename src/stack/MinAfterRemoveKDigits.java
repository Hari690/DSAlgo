package stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 */
public class MinAfterRemoveKDigits {
    public String removeKdigits(String num, int k) {
        if(k==num.length())
            return "0";

        Stack<Character> stack = new Stack<>();
        stack.add(num.charAt(0));
        for( int i=1;i<num.toCharArray().length;i++) {
            char c = num.charAt(i);
            if((c-'a') < (stack.peek()-'a')) {
                while (!stack.isEmpty() && ((c - 'a') < (stack.peek()-'a') && k>0)) {
                    k--;
                    stack.pop();
                }
            }
            stack.push(c);
        }

        while(k>0){
            stack.pop();
            k--;
        }

        StringBuilder output = new StringBuilder();

        while(!stack.isEmpty())
            output.append(stack.pop());

        while(output.length() > 1 && output.charAt(output.length()-1) == '0')
            output.deleteCharAt(output.length()-1);

        List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u');
        Set<Character> vowels = new HashSet<>(list);

        return output.reverse().toString();
    }
}
