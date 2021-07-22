package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of strings words representing an English Dictionary,
 * return the longest word in words that can be built one character at a time by other words in words.
 */
public class LongestWordBuilt {
    public String longestWord(String[] words) {
        // smaller strings come first
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestWordBuilt longestWordBuilt = new LongestWordBuilt();
        String words[] = {"w","wo","wor","worl","world"};
        System.out.println(longestWordBuilt.longestWord(words));
    }
}
