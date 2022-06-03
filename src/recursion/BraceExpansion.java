package recursion;

import java.util.TreeSet;

/**
 * A string S represents a list of words.
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 * Example 1:
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 *
 * Example 2:
 * Input: "abcd"
 * Output: ["abcd"]
 *
 * Note:
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
public class BraceExpansion {
    public String[] expand(String S) {
        TreeSet<String> set = new TreeSet<>();
        if (S.length() == 0) {
            return new String[]{""};
        } else if (S.length() == 1) {
            return new String[]{S};
        }

        if (S.charAt(0) == '{') {
            int i = 0;
            while (S.charAt(i) != '}') {
                i++;
            }
            String sub = S.substring(1, i);
            String[] subs = sub.split(",");
            String[] strs = expand(S.substring(i + 1)); // dfs
            for (int j = 0; j < subs.length; j++) {
                for (String str : strs) {
                    set.add(subs[j] + str);
                }
            }
        } else {
            String[] strs = expand(S.substring(1));
            for (String str : strs) {
                set.add(S.charAt(0) + str);
            }
        }
        return set.toArray(new String[0]);
    }
}