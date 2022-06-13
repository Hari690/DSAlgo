package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 *
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class MinWindowSubstring {

    public static void main(String[] args) {
        new MinWindowSubstring().minWindow("ABDEAB","ABE");
    }

    public String minWindow(String s, String t) {
        if(t.length()>s.length())
            return "";

        Map<Character,Integer> counts = new HashMap<>();

        int count = 0;
        for( char c : t.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0)+1);
            count++;
        }

        int left = 0;
        int right = 0;
        int min=s.length()+1;
        int head = 0;

        while(right<s.length()) {
            if (counts.containsKey(s.charAt(right))) {
                counts.put(s.charAt(right), counts.get(s.charAt(right)) - 1);
                if(counts.get(s.charAt(right))>=0)
                    count--;
            }
            while (count == 0) {
                if((right-left+1) < min) {
                    min = right - left + 1;
                    head = left;
                }
                if (counts.containsKey(s.charAt(left))) {
                    counts.put(s.charAt(left), counts.get(s.charAt(left)) + 1);

                    if(counts.get(s.charAt(left))>0)
                        count++;
                }
                left++;
            }
            right++;
        }
        return (min==(s.length()+1))?"":s.substring(head,head+min);
    }
}
