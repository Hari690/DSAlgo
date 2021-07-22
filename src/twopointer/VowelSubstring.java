package twopointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VowelSubstring {

    public int vowelsubstring(String s) {
        List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u');
        Set<Character> vowels = new HashSet<>(list);
        Map<Character,Integer> vowelFound = new HashMap<>();
        int numSubstrings = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if(!vowels.contains(s.charAt(j))) {
                    break;
                }
                vowelFound.put(s.charAt(j), vowelFound.getOrDefault(s.charAt(j), 0)+1);
                if(vowelFound.size()>=5) {
                    numSubstrings+=1;
                }
            }
            vowelFound.clear();
        }
        return numSubstrings;
    }

    public static void main(String[] args) {
        VowelSubstring vowelSubstring = new VowelSubstring();
        String s = "aeioaexaaeuiou";
        vowelSubstring.vowelsubstring(s);
    }
}
