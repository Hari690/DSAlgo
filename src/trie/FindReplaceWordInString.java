package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 * To complete the ith replacement operation:
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 *
 * Example 2:
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 */
public class FindReplaceWordInString {

    /*
        O(n) since we go through each string only once
     */
    public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, Integer> map = new HashMap<>();

        // find all indexes for which there is a match
        for (int i=0; i<indexes.length; i++) {
            // use sources if a match is found in the original string, record it and also index in targets where there's match
            if (s.startsWith(sources[i], indexes[i])) {
                map.put(indexes[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); ) {
            if (map.containsKey(i)) {
                // if a replacement was recorded before
                sb.append(targets[map.get(i)]);
                i+=sources[map.get(i)].length();
            } else {
                // if no replacement happened at this index
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FindReplaceWordInString findReplaceWordInString = new FindReplaceWordInString();
        findReplaceWordInString.findReplaceString("abcd",new int[]{0,2},new String[]{"a","cd"}, new String[]{"eee", "ffff"});
    }
}
