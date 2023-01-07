package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 */
public class RemoveDuplicatesLexicoOrderMonotonicStack {

    /*
        For lexicographically sorted and removing duplicates, we need to check if a given character is the last occurence of that character
        in case a lower character has not been inserted after it so we delete all previous instances so we keep and adding and deleting from a stack.

        Use stack to maintain order and comparison between values.
        If what stack contains is not last occurence of a character and higher value than current pop it off
        and push current.
        Also maintain a visited to ensure we don't push same element twice which is one of the contraints.
        Point to note, we insert the int value of char which can be used as index.
     */
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for(int i=0;i<s.length();i++)
            lastIndex[s.charAt(i)-'a'] = i;

        boolean[] seen = new boolean[26];
        Deque<Integer> stack = new LinkedList<>();
        for(int i=0;i<s.length();i++) {
            int c = s.charAt(i)-'a';
            if(seen[c])
                continue;
            while(!stack.isEmpty() && stack.peek()>c && i<lastIndex[stack.peek()]) {
                seen[stack.pop()] = false;
            }
            stack.push(c);
            seen[c] = true;
        }

        StringBuilder output = new StringBuilder();
        while(!stack.isEmpty()) {
            output.append((char)(stack.poll()+'a'));
        }
        return output.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveDuplicatesLexicoOrderMonotonicStack removeDups = new RemoveDuplicatesLexicoOrderMonotonicStack();
        removeDups.removeDuplicateLetters("cbacdcbc");
    }
}
