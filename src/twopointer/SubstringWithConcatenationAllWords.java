package twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
 * You can return the answer in any order.
 * Example 1:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 *
 * Example 2:
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 *
 * Example 3:
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 */
public class SubstringWithConcatenationAllWords {

    /*
        Create a map with words and occurrences.
        Go through word and cut it into smaller words from that index
        and see f it has occurred.
     */
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String,Integer> map = getWordCount(words);
        List<Integer> result = new ArrayList<>();
        int l = words[0].length();
        for(int i=0;i<=s.length()-words.length*l;i++) {
            Map<String,Integer> seen = new HashMap<>();
            for(int j=0;j<words.length;j++) {
                String word = s.substring(i+j*l,i+j*l+l);
                if(map.containsKey(word) && (!seen.containsKey(word) || seen.get(word)<map.get(word)))                 {
                    seen.put(word, seen.getOrDefault(word, 0)+1);
                } else {
                    break;
                }
                if(j==words.length-1)
                    result.add(i);
            }
        }
        return result;
    }

    private Map<String,Integer> getWordCount(String[] words) {
        Map<String,Integer> map = new HashMap<>();
        for( String word : words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        return map;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationAllWords solution = new SubstringWithConcatenationAllWords();
        String[] words = {"foo","bar"};
        solution.findSubstring("barfoothefoobarman", words);
    }
}
