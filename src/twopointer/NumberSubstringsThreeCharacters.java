package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 */
public class NumberSubstringsThreeCharacters {
    public int numberOfSubstrings(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int left=0;
        int result=0;
        for(int right=0;right<s.length();right++) {
            map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)+1);
            while(map.size()>=3) {
                map.put(s.charAt(left),map.get(s.charAt(left))-1);
                if(map.get(s.charAt(left)) == 0){
                    map.remove(s.charAt(left));
                }
                left++;
            }
            result+=left;
        }
        return result;
    }
}
