package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string s, determine if it is valid.
 *
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any
 * number of times:
 *
 * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that
 * tleft and tright may be empty.
 * Return true if s is a valid string, otherwise, return false.
 *
 * Example 1:
 *
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 * Example 2:
 *
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 * Example 3:
 *
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 */
public class WordValidSuperstition {
    public boolean isValid(String s) {
        if(s.length()%3!=0)
            return false;
        Deque<Character> stack = new LinkedList<>();
        int i=0;
        while(i<s.length()) {
            if(s.charAt(i)=='a') {
                stack.push('a');
            } else if(s.charAt(i)=='b') {
                if(stack.isEmpty())
                    return false;
                char prev = stack.pop();
                if(prev!='a')
                    return false;
                stack.push('b');
            } else {
                if(stack.isEmpty())
                    return false;
                char prev = stack.pop();
                if(prev!='b')
                    return false;
            }
            i++;
        }
        return stack.isEmpty();
    }
}
