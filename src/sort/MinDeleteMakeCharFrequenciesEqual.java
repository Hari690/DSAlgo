package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 * The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab",
 * the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 * Example 2:
 *
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 * Example 3:
 *
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 */
public class MinDeleteMakeCharFrequenciesEqual {
    /*
        Since we do only deletions here we can do it greedily by sorting as per frequency.
        And then starting with highest count go down and see unique counts that can be deleted and
        setting maxFrequency correspondingly.
     */
    public int minDeletions(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c :s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (a, b)->b.getValue()-a.getValue());

        int maxFrequency=list.get(0).getValue();
        int delete = 0;
        for(Map.Entry<Character,Integer> entry : list) {
            if(entry.getValue()>maxFrequency) {
                if(maxFrequency>0)
                    delete+=entry.getValue()-maxFrequency;
                else
                    delete+=entry.getValue();
            }
            maxFrequency=Math.min(maxFrequency-1,entry.getValue()-1);
        }

        return delete;
    }

    public static void main(String[] args) {
        MinDeleteMakeCharFrequenciesEqual minDeleteMakeCharFrequenciesEqual = new MinDeleteMakeCharFrequenciesEqual();

        minDeleteMakeCharFrequenciesEqual.minDeletions("bbcebab");
    }
}
