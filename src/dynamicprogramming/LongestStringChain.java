package dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of words where each word consists of lowercase English letters.
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 * Example 1:
 *
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 * Example 2:
 *
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 * Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * Example 3:
 *
 * Input: words = ["abcd","dbqca"]
 * Output: 1
 * Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
 * ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 */
public class LongestStringChain {

    public int longestStrChain(String[] words) {
        int max = 0;
        Arrays.sort(words, Comparator.comparingInt(String::length));

        Map<String,Integer> map = new HashMap<>();

        //From shortest word to longest word
        //Each word is atleast 1 chain long
        //Form next word removing 1 char each time for each w
        for (String word : words) {
            map.put(word, 1);
            for(int i=0;i<word.length();i++) {
                StringBuilder newWord = new StringBuilder(word);
                String changedWord = newWord.deleteCharAt(i).toString();
                if(map.containsKey(changedWord)) {
                    map.put(word, Math.max(map.get(word),map.get(changedWord)+1));
                }
            }
            max = Math.max(max, map.get(word));
        }
        return max;
    }

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        LongestStringChain longestChainString = new LongestStringChain();
        longestChainString.longestStrChain(words);
    }
}
