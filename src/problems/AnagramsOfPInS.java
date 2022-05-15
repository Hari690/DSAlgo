package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 */
public class AnagramsOfPInS {
    /*
    Another O(26n) approach is to add to two maps for both t and s upto length of t.
    Then keep adding removing character from hash according to t.
    Keep comparing both the maps.
     */
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;

        /*
        expand right until all characters are in the window.
        then check if window size is same as t.length(), if it is add.
        Else expand left till at-least one character in t is removed.
         */
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;

            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        AnagramsOfPInS anagramsOfPInS = new AnagramsOfPInS();
        anagramsOfPInS.findAnagrams("cnbaebabacd","abc");
    }
}