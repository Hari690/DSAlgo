package twopointer;

import java.util.Set;

/**
 * Given a string s and an integer k.
 * Return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are (a, e, i, o, u).
 */
public class MaxVowelsInString {
    public int maxVowels(String s, int k) {
        int ans = 0;
        var vowels = Set.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0, winCnt = 0; i < s.length(); ++i) {
            if (vowels.contains(s.charAt(i))) {
                ++winCnt;
            }
            if (i >= k && vowels.contains(s.charAt(i - k))) {
                --winCnt;
            }
            ans = Math.max(winCnt, ans);
        }
        return ans;
    }
}
