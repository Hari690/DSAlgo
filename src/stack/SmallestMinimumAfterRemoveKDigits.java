package stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 */
public class SmallestMinimumAfterRemoveKDigits {
    /*
        Since for nos it make sense to remove bigger nos from the beginning we are looking for smaller nos first.
        So we will add a no since we don't know what comes next but if we find a smaller no after it we remove previous one k times so we can minimise.
        Intuition is to use a monotonic stack of increasing values and we remove characters if thats'
        not the case. Else we can remove from end of stack.
        Finally remove leading zeroes and convert to a string.
     */
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

        return output.reverse().toString();
    }
}
