package twopointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 */
public class MaxNoOfVowelsInSubstringGivenLength {
    public int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));

        int left = 0;
        int right = 0;
        int vowel = 0;
        int max = 0;
        while(right<s.length()) {
            if(vowels.contains(s.charAt(right))) {
                vowel++;
            }
            while(right-left>=k) {
                if(vowels.contains(s.charAt(left))) {
                    vowel--;
                }
                left++;
            }
            max = Math.max(max,vowel);
            right++;
        }

        return max;
    }

    public static void main(String[] args) {
        MaxNoOfVowelsInSubstringGivenLength maxNoOfVowelsInSubstringGivenLength = new MaxNoOfVowelsInSubstringGivenLength();

        System.out.println(maxNoOfVowelsInSubstringGivenLength.maxVowels("aeiou", 2));
    }
}
