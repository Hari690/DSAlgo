package hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.
 *
 * Example 1:
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
 *
 * Example 2:
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 */
public class FindAndReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for(String word : words) {
            if(word.length()!=pattern.length())
                continue;

            int i=0;
            Map<Character,Character> map = new HashMap<>();
            Map<Character,Character> reverseMap = new HashMap<>();
            while(i<=pattern.length()) {
                if(i==pattern.length()) {
                    result.add(word);
                    break;
                }

                if(map.containsKey(pattern.charAt(i))) {
                    if(map.get(pattern.charAt(i))!=word.charAt(i))
                        break;
                }

                if(reverseMap.containsKey(word.charAt(i))) {
                    if(reverseMap.get(word.charAt(i))!=pattern.charAt(i))
                        break;
                }

                map.put(pattern.charAt(i),word.charAt(i));
                reverseMap.put(word.charAt(i), pattern.charAt(i));
                i++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        FindAndReplacePattern findAndReplacePattern = new FindAndReplacePattern();
        String[] words = {"abc","deq","mee","aqq","dkd","ccc"};
        findAndReplacePattern.findAndReplacePattern(words, "abb");
    }
}
