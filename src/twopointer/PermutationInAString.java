package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 */
public class PermutationInAString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length())
            return false;

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for(int i=0;i<s1.length();i++) {
            map1.put(s1.charAt(i), map1.getOrDefault(s1.charAt(i),0)+1);
            map2.put(s2.charAt(i), map2.getOrDefault(s2.charAt(i),0)+1);
        }

        if(compare(map1,map2))
            return true;

        int left=0;
        for(int i=s1.length();i<s2.length();i++) {
            Integer val = map2.get(s2.charAt(left));
            if(val==1)
                map2.remove(s2.charAt(left));
            else
                map2.put(s2.charAt(left), val-1);
            map2.put(s2.charAt(i), map2.getOrDefault(s2.charAt(i),0)+1);
            left++;
            if(compare(map1,map2))
                return true;
        }
        return false;
    }

    private boolean compare(Map<Character,Integer> map1, Map<Character,Integer> map2) {
        if(map1.size()!=map2.size())
            return false;

        for(Map.Entry<Character,Integer> entry : map1.entrySet()) {
            if(!entry.getValue().equals(map2.get(entry.getKey()))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PermutationInAString solution = new PermutationInAString();

        solution.checkInclusion("ab","eidbaooo");
    }
}
