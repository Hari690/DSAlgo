package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
public class PermutationInAString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length())
            return false;

        int[] map1 = new int[26];
        int[] map2 = new int[26];

        for(int i=0;i<s1.length();i++) {
            map1[s1.charAt(i)-'a']++;
            map2[s2.charAt(i)-'a']++;
        }

        if(compare(map1,map2))
            return true;

        int left=0;
        for(int i=s1.length();i<s2.length();i++) {
            if(compare(map1,map2))
                return true;
            map2[s2.charAt(i)-'a']++;
            map2[s2.charAt(left)-'a']--;
            left++;
        }
        if(compare(map1,map2))
            return true;
        return false;
    }

    private boolean compare(int[] map1, int[] map2) {
        for(int i=0;i<map1.length;i++) {
            if(map1[i]!=map2[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationInAString solution = new PermutationInAString();

        solution.checkInclusion("adc","dcda");
    }
}
