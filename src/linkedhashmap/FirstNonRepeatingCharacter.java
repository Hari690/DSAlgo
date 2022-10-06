package linkedhashmap;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FirstNonRepeatingCharacter {

    /*
        Here we return the index of first non-repeating character.å
        If we have to return the character itself the set is not required.
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
