package trie;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

/**
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 * <p>
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 *
 * Time complexity - O(w) where w - length of word
 */
public class MinLengthEncoding {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length).reversed());
        Trie root = createRootTrie(words[0]);
        int length = words[0].length() + 1;
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            if (this.createSingleTrie(word, root))
                length += word.length() + 1;
        }
        return length;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean endOfWord = false;
    }

    private Trie createRootTrie(String word) {
        Trie root = new Trie();
        Trie node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie();
            }
            node = node.children[c - 'a'];
        }
        node.endOfWord = true;
        return root;
    }

    private boolean createSingleTrie(String word, Trie node) {
        boolean newPath = false;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                newPath = true;
                node.children[c - 'a'] = new Trie();
            }
            node = node.children[c - 'a'];
        }
        node.endOfWord = true;
        if (newPath)
            return true;
        return false;
    }

    public static void main(String[] args) {
        MinLengthEncoding minLengthEncoding = new MinLengthEncoding();
//        String[] words = {"time", "me", "bell"};
//        minLengthEncoding.minimumLengthEncoding(words);

        BigDecimal threshold = new BigDecimal("0.75");
        BigDecimal value1 = new BigDecimal("0.72");
        BigDecimal value2 = new BigDecimal("0.76");
        System.out.println(value1.compareTo(threshold));
        System.out.println(value2.compareTo(threshold));
    }
}
