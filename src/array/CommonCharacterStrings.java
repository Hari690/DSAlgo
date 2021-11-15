package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the
 * list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that
 * character three times in the final answer.
 *
 * You may return the answer in any order.
 */
public class CommonCharacterStrings {
    public List<String> commonChars(String[] A) {
        List<String> ans = new ArrayList<>();
        // Common characters dictionary
        int[] dict = new int[26];
        for (int j = 0; j < A[0].length(); j++) {
            dict[A[0].charAt(j) - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            // Dictionary of the current word
            int[] curr = new int[26];
            for (int j = 0; j < A[i].length(); j++) {
                curr[A[i].charAt(j) - 'a']++;
            }
            // Update the common dictionary
            for (int j = 0; j < 26; j++) {
                if (curr[j] < dict[j]) dict[j] = curr[j];
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < dict[i]; j++) {
                ans.add(String.valueOf((char) ('a' + i)));
            }
        }
        return ans;
    }
}
