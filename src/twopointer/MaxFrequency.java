package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 */
public class MaxFrequency {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<Character,Integer> map = new HashMap<>();
        Map<String,Integer> map2 = new HashMap<>();

        int left = 0;
        int count = 0;
        for(int i=0;i<s.length();i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
            if(i-left+1==minSize) {
                String subString = s.substring(left,i+1);
                map2.put(subString, map2.getOrDefault(subString,0)+1);
                if(map.size()<=maxLetters)
                    count = Math.max(count, map2.get(subString));
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
                if(map.get(s.charAt(left))==0)
                    map.remove(s.charAt(left));
                left++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        /*
        "aaaaacbc"
2
4
6
         */
        MaxFrequency solution = new MaxFrequency();
        solution.maxFreq("aaaaacbc",2,4,6);
    }
}
