package twopointer;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s2, String s1) {
        if(s1.length()>s2.length())
            return new ArrayList<>();

        int[] map1 = new int[26];
        int[] map2 = new int[26];

        for(int i=0;i<s1.length();i++) {
            map1[s1.charAt(i)-'a']++;
            map2[s2.charAt(i)-'a']++;
        }

        List<Integer> result = new ArrayList<>();

        int left=0;
        for(int i=s1.length();i<s2.length();i++) {
            if(compare(map1,map2))
                result.add(i-s1.length());
            map2[s2.charAt(i)-'a']++;
            map2[s2.charAt(left)-'a']--;
            left++;
        }
        if(compare(map1,map2))
            result.add(s2.length()-s1.length());
        return result;
    }

    private boolean compare(int[] map1, int[] map2) {
        for(int i=0;i<map1.length;i++) {
            if(map1[i]!=map2[i])
                return false;
        }
        return true;
    }
}
