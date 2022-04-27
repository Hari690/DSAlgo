package hashmap;

import java.util.HashSet;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
 *
 * Hint:
 * Consider the palindromes of odd vs even length. What difference do you notice? Count the frequency of each character.
 * If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
 */
public class CanFormPalindrome {
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> app = new HashSet<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (app.contains(c)) {
                app.remove(c);
            }
            else {
                app.add(c);
            }
        }
        return app.size() <=1;
    }
}
