package heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * Example 1:
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for( char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }

        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue()-a.getValue());
        for(Map.Entry<Character,Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        StringBuilder output = new StringBuilder();

        while(maxHeap.size()>0) {
            Map.Entry<Character,Integer> entry = maxHeap.poll();
            for(int i=0;i<entry.getValue();i++)
                output.append(entry.getKey());
        }

        return output.toString();

    }
}
