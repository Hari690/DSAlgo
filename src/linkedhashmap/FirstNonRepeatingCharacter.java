package linkedhashmap;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FirstNonRepeatingCharacter {

    /*
        Here we return the index of first non-repeating character.å
        If we have to return the character itself the set is not required.

        Use HashMap<String, Integer>
        String is the word and Integer is the index. 1st time seeing a word, set its index as its real index. 2nd time seeing the same word, set its index as -1.
        After scanning the file, go through the map, and find the smallest index >= 0
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (map.get(s.charAt(i)) != null) {
                    map.remove(s.charAt(i));
                }
            } else {
                map.put(s.charAt(i), i);
                set.add(s.charAt(i));
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }
}
