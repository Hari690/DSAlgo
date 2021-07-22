package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {

    public static void main(String[] args) {
        new MinWindowSubstring().minWindow("ABDEAB","ABE");
    }

    public String minWindow(String s, String t) {
        if(t.length()>s.length())
            return "";

        Map<Character,Integer> counts = new HashMap<>();

        int count = 0;
        for( char c : t.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0)+1);
            count++;
        }

        int left = 0;
        int right = 0;
        int min=s.length()+1;
        int head = 0;

        while(right<s.length()) {
            if (counts.containsKey(s.charAt(right))) {
                counts.put(s.charAt(right), counts.get(s.charAt(right)) - 1);
                if(counts.get(s.charAt(right))>=0)
                    count--;
            }
            while (count == 0) {
                if((right-left+1) < min) {
                    min = right - left + 1;
                    head = left;
                }
                if (counts.containsKey(s.charAt(left))) {
                    counts.put(s.charAt(left), counts.get(s.charAt(left)) + 1);

                    if(counts.get(s.charAt(left))>0)
                        count++;
                }
                left++;
            }
            right++;
        }
        return (min==(s.length()+1))?"":s.substring(head,head+min);
    }
}
