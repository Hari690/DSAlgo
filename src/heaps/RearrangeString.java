package heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: s = "aaab"
 * Output: ""
 */
public class RearrangeString {
    public String reorganizeString(String s) {

        PriorityQueue<Map.Entry<Character,Integer>> heap = new PriorityQueue<>((a, b)->b.getValue()-a.getValue());

        Map<Character,Integer> map = new HashMap<>();

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        heap.addAll(map.entrySet());

        StringBuilder output = new StringBuilder();
        while(heap.size()>1) {
            Map.Entry<Character,Integer> entry1 = heap.poll();
            Map.Entry<Character,Integer> entry2 = heap.poll();
            output.append(entry1.getKey());
            output.append(entry2.getKey());
            entry1.setValue(entry1.getValue()-1);
            entry2.setValue(entry2.getValue()-1);
            if(entry1.getValue()!=0)
                heap.add(entry1);
            if(entry2.getValue()!=0)
                heap.add(entry2);
        }
        if(heap.size()>0) {
            Map.Entry<Character,Integer> entry = heap.poll();
            if(entry.getValue()>1)
                return "";
            else
                output.append(entry.getKey());
        }
        return output.toString();
    }
}
