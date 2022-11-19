package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 * A palindrome is a string that reads the same forward and backward.
 *
 * Example 1:
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 *
 * Example 2:
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 *
 * Example 3:
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 */
public class LongestPalindromeTwoLetterWords {

    /*
        Check if word and reverse word exists, then increase by 4.
        Check if all chars exists for a word then add that separately as 2.
     */
    public int longestPalindrome(String[] words) {
        Map<String, Integer> nonPaired = new HashMap<>();
        int pairs = 0, sym = 0;
        for (String w : words) {
            String reverse = new StringBuilder(w).reverse().toString();
            if (nonPaired.getOrDefault(reverse, 0) > 0) { // Find a counterpart for w among non-paired words.
                ++pairs;                                  // Increase the counter by 1.
                nonPaired.put(w, 1 + nonPaired.getOrDefault(w, 0));  // Decrease reverse by 1 since it has been counted in pairs.
                sym -= w.charAt(0) == w.charAt(1) ? 1 : 0; // Decrease sym by 1 since it has been counted in pairs.
            }else {
                nonPaired.put(w, 1 + nonPaired.getOrDefault(w, 0));  // Increase the occurrence of w.
                sym += w.charAt(0) == w.charAt(1) ? 1 : 0; // Increase sym by 1.
            }
        }
        return 4 * pairs + (sym > 0 ? 2 : 0);
    }
}
