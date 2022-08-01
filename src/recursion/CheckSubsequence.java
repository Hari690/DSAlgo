package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 */
public class CheckSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }

    /**
     * Follow-up
     * If we check each sk in this way, then it would be O(kn) time where k is the number of s and t is the length of t.
     * This is inefficient.
     * Since there is a lot of s, it would be reasonable to preprocess t to generate something that is easy to search for if a character of s is in t.
     * Sounds like a HashMap, which is super suitable for search for existing stuff.
     */
    public boolean isSubsequenceMapAndBS(String s, String t) {
        if (s == null || t == null) return false;

        Map<Character, List<Integer>> map = new HashMap<>(); //<character, index>

        //preprocess t
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<>());
            }
            map.get(curr).add(i);
        }

        int prev = -1;  //index of previous character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == null)  {
                return false;
            } else {
                List<Integer> list = map.get(c);
                prev = binarySearch(prev, list, 0, list.size() - 1);
                if (prev == -1) {
                    return false;
                }
                prev++;
            }
        }

        return true;
    }

    private int binarySearch(int index, List<Integer> list, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < index) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start == list.size() ? -1 : list.get(start);
    }
}
