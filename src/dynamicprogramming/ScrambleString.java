package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * We can scramble a string s to get a string t using the following algorithm:
 *
 * If the length of the string is 1, stop.
 * If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 * Apply step 1 recursively on each of the two substrings x and y.
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 *
 * Example 1:
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now, and the result string is "rgeat" which is s2.
 * As one possible scenario led s1 to be scrambled to s2, we return true.
 *
 * Example 2:
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 *
 * Example 3:
 * Input: s1 = "a", s2 = "a"
 * Output: true
 */
public class ScrambleString {
    Map<String,Boolean> cache = new HashMap<>();

    /*
        We can judge whether s1 and s2 can scramble into each other through mathematical induction:
        The base case that s1 can scramble into s2 if s1== s2. If the frequencies of each characters appearing in s1 and s2 differ, then s1 can not scramble into s2.
        If there exist 0 <= i <= s1.length() where
        s1[0,i] can scramble into s2[0,i] and s1[i,length] can scramble into s2[i, length]; or
        s1[0,i] can scramble into s2[length - i, length] and s1[i,length] can scramble into s2[0, length - i]
        then, s1 can scramble into s2.
     */
    public boolean isScramble(String s1, String s2) {
        if( s1.equals(s2) )
            return true;
        if(cache.containsKey(s1+" "+s2))
            return cache.get(s1+" "+s2);
        int s1Array[] = new int[26];
        int s2Array[] = new int[26];
        for(int i = 0; i < s1.length(); i++) {
            s1Array[s1.charAt(i) - 'a']++;
            s2Array[s2.charAt(i) - 'a']++;
        }
        for(int i = 0; i < 26; i++)
            if( s1Array[i] != s2Array[i] )
                return false;
        for(int i = 1; i < s1.length(); i++) {
            if( isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)) ) {
                cache.put(s1+" "+s2,true);
                return true;
            }
            if( isScramble(s1.substring(0, i), s2.substring(s1.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s1.length() - i))) {
                cache.put(s1+" "+s2,true);
                return true;
            }
        }
        cache.put(s1+" "+s2,false);
        return false;
    }
}
